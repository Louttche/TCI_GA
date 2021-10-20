package model;

import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import services.exposed.ExamNotFoundException;
import services.exposed.teacher.ExamStartedException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

class ExamSetupTest {

    /**
     * @verifies throw an IllegalDateException if begintime is not before endtime
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldThrowAnIllegalDateExceptionIfBegintimeIsNotBeforeEndtime() throws Exception {
        // arrange
        Course course = mock(Course.class);
        LocalDateTime startDate = LocalDateTime.now(); //mock(Date.class);
        int nrOfClasscodes = 2;
        String examName = "exam name example";
        LocalTime beginTime = LocalTime.now();
        LocalTime endTime = beginTime.plus(2, ChronoUnit.HOURS);
        ExamID examID = mock(ExamID.class);

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new ExamSetup(course, startDate, nrOfClasscodes, examName, beginTime, endTime, examID);
        });
    }

    /**
     * @verifies set the examname to course name if it is empty
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldSetTheExamnameToCourseNameIfItIsEmpty() throws Exception {
        // arrange
        Course course = mock(Course.class);
        String examName = "";
        LocalDateTime startDate = LocalDateTime.now();
        LocalTime beginTime = LocalTime.now();
        LocalTime endTime = beginTime.plus(2, ChronoUnit.HOURS);
        int nrOfClasscodes = 2;
        ExamID examID = mock(ExamID.class);

        ExamSetup es = new ExamSetup(course, startDate, nrOfClasscodes, examName, beginTime, endTime, examID);

        // act
        when(es.getCourse().getName()).thenReturn("courseName");

        // assert
        assertThat(es.getExamname()).isEqualTo("courseName");
    }

    /**
     * @verifies not allow to change examname after exam has started
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldNotAllowToChangeExamnameAfterExamHasStarted() throws Exception {
        // arrange
        Course course = mock(Course.class);
        String examName = "name";
        LocalDateTime startDate = LocalDateTime.now();
        LocalTime beginTime = LocalTime.now();
        LocalTime endTime = beginTime.plus(2, ChronoUnit.HOURS);
        int nrOfClasscodes = 2;
        ExamID examID = mock(ExamID.class);

        ExamSetup es = new ExamSetup(course, startDate, nrOfClasscodes, examName, beginTime, endTime, examID);

        // act
        String new_examName = "newname";
        es.setExamname(new_examName);

        // assert
        assertThat(es.getExamname()).isEqualTo(examName);
    }

    /**
     * @verifies generate classcodes with valid size
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     *
     * the classcodes consists of the name of the exam, followed by a '-', followed by a random string of 6 characters.
     */
    @Test
    public void ExamSetup_shouldGenerateClasscodesWithValidSize() throws Exception {
        // arrange
        Course course = mock(Course.class);
        String examName = "name";
        LocalDateTime startDate = LocalDateTime.now();
        LocalTime beginTime = LocalTime.now();
        LocalTime endTime = beginTime.plus(2, ChronoUnit.HOURS);
        int nrOfClasscodes = 4;
        ExamID examID = mock(ExamID.class);

        ExamSetup es = new ExamSetup(course, startDate, nrOfClasscodes, examName, beginTime, endTime, examID);

        // assert
        assertNotNull(es.getClasscodes());

        Boolean areValid = false;
        if (!es.getClasscodes().isEmpty())
            areValid = es.getClasscodes().stream().anyMatch((String c) -> c.substring(c.lastIndexOf('-') + 1).length() != 6);

        assertThat(areValid == true);
    }

    /**
     * @verifies always generate at least 2 classcodes when no nrOfClasscodes are given or null
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldAlwaysGenerateAtLeast2ClasscodesWhenNoNrOfClasscodesAreGivenOrNull() throws Exception {
        // arrange
        Course course = mock(Course.class);
        String examName = "name";
        LocalDateTime startDate = LocalDateTime.now();
        LocalTime beginTime = LocalTime.now();
        LocalTime endTime = beginTime.plus(2, ChronoUnit.HOURS);
        ExamID examID = mock(ExamID.class);

        ExamSetup es_A = new ExamSetup(course, startDate, 0, examName, beginTime, endTime, examID);
        ExamSetup es_B = new ExamSetup(course, startDate, null, examName, beginTime, endTime, examID);

        // assert
        assertNotNull(es_A.getClasscodes());
        assertThat(es_A.getClasscodes().size() == 2);

        assertNotNull(es_B.getClasscodes());
        assertThat(es_B.getClasscodes().size() == 2);
    }

    /**
     * @verifies throw an ExamNotFoundException when exam materials are added before exam gets created
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldThrowAnExamNotFoundExceptionWhenExamMaterialsAreAddedBeforeExamGetsCreated() throws Exception {
        // arrange
        Course course = mock(Course.class);
        String examName = "name";
        int nrOfClasscodes = 4;

        // Set it so exam will not start yet
        LocalDateTime startDate = LocalDateTime.now().plus(1, ChronoUnit.HOURS);
        LocalTime beginTime = LocalTime.now().plus(1, ChronoUnit.HOURS);
        LocalTime endTime = beginTime.plus(2, ChronoUnit.HOURS);

        // act
        // pass some parameters as null to act like the exam has not been fully set up yet
        ExamSetup es = new ExamSetup(course, startDate, nrOfClasscodes, examName, beginTime, null, null);

        // assert
        assertThatExceptionOfType(ExamNotFoundException.class).isThrownBy(() -> {
           es.setMaterial("material");
        });
    }

    /**
     * @verifies throw an ExamStartedException when exam materials are added after exam starts
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldThrowAnExamStartedExceptionWhenExamMaterialsAreAddedAfterExamStarts() throws Exception {
        // arrange
        Course course = mock(Course.class);
        String examName = "name";
        LocalDateTime startDate = LocalDateTime.now();
        LocalTime beginTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plus(2, ChronoUnit.HOURS);
        int nrOfClasscodes = 4;
        ExamID examID = mock(ExamID.class);

        // act
        // starts exam now
        ExamSetup es = new ExamSetup(course, startDate, nrOfClasscodes, examName, beginTime, endTime, examID);

        // assert
        assertThatExceptionOfType(ExamStartedException.class).isThrownBy(() -> {
            es.setMaterial("material");
        });
    }
}