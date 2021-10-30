package model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import services.exposed.ExamNotFoundException;
import services.exposed.teacher.ExamStartedException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class ExamSetupTest {

    private Course VALID_COURSE = mock(Course.class);
    private String VALID_COURSE_NAME = "courseName";

    private LocalDateTime VALID_START_DATE_TODAY = LocalDateTime.now();

    private LocalTime VALID_BEGIN_TIME_NOW = LocalTime.now();
    private LocalTime VALID_END_TIME_NOW = VALID_BEGIN_TIME_NOW.plus(2, ChronoUnit.HOURS);

    private LocalTime VALID_BEGIN_TIME_BEFORE = LocalTime.now().minus(1, ChronoUnit.HOURS);
    private LocalTime VALID_END_TIME_BEFORE = VALID_BEGIN_TIME_BEFORE.plus(2, ChronoUnit.HOURS);

    private int VALID_NUMBER_OF_CLASSCODES = 2;

    private ExamID VALID_EXAMID = mock(ExamID.class);
    private String VALID_EXAM_NAME = "examName";

    /**
     * @verifies throw an IllegalArgumentException if begintime is not before endtime
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldThrowAnIllegalArgumentExceptionIfBegintimeIsNotBeforeEndtime() throws Exception {
        // arrange / act
        LocalTime beginTime = VALID_BEGIN_TIME_NOW;
        LocalTime endTime = beginTime.minus(1, ChronoUnit.HOURS);

        // assert
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, VALID_NUMBER_OF_CLASSCODES, VALID_EXAM_NAME, beginTime, endTime, VALID_EXAMID);
        });
    }

    /**
     * @verifies set the examname to course name if it is empty
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldSetTheExamnameToCourseNameIfItIsEmpty() throws Exception {
        // arrange / act
        when(VALID_COURSE.getName()).thenReturn("courseName");
        ExamSetup es = new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, VALID_NUMBER_OF_CLASSCODES, "", VALID_BEGIN_TIME_NOW, VALID_END_TIME_NOW, VALID_EXAMID);

        // assert
        assertThat(es.getExamname()).isEqualTo(VALID_COURSE_NAME);
    }

    /**
     * @verifies not allow to change examname after exam has started
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldNotAllowToChangeExamnameAfterExamHasStarted() throws Exception {
        // arrange
        ExamSetup es = new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, VALID_NUMBER_OF_CLASSCODES, VALID_EXAM_NAME, VALID_BEGIN_TIME_BEFORE, VALID_END_TIME_BEFORE, VALID_EXAMID);

        // act
        String new_examName = "newName";
        es.setExamname(new_examName);

        // assert
        assertThat(es.getExamname()).isEqualTo(VALID_EXAM_NAME);
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
        int nrOfClasscodes = 4;

        ExamSetup es = new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, nrOfClasscodes, VALID_EXAM_NAME, VALID_BEGIN_TIME_NOW, VALID_END_TIME_NOW, VALID_EXAMID);

        // assert
        assertNotNull(es.getClasscodes());

        if (!es.getClasscodes().isEmpty()){
            // Check if the string after the exam name in the main classcodes is of size 6
            for (int i = 0; i < nrOfClasscodes; i++){
                String code = es.getClasscodes().get(i);
                if (code.substring(code.lastIndexOf('-') + 1).length() != 6)
                    fail("Main Classcodes are not of valid size.");
            }

            // Check if the string after the exam name in the extra classcodes is of size 4
            for (int i = nrOfClasscodes; i < es.getClasscodes().size(); i++){
                String code = es.getClasscodes().get(i);
                if (code.substring(code.lastIndexOf('-') + 1).length() != 4)
                    fail("Extra Classcodes are not of valid size.");
            }
        }
    }

    /**
     * @verifies always generate at least 2 classcodes when no nrOfClasscodes are given or null
     * @see ExamSetup#ExamSetup(Course, LocalDateTime, Integer, String, LocalTime, LocalTime, ExamID)
     */
    @Test
    public void ExamSetup_shouldAlwaysGenerateAtLeast2ClasscodesWhenNoNrOfClasscodesAreGivenOrNull() throws Exception {
        // arrange
        ExamSetup es_A = new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, 0, VALID_EXAM_NAME, VALID_BEGIN_TIME_NOW, VALID_END_TIME_NOW, VALID_EXAMID);
        ExamSetup es_B = new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, null, VALID_EXAM_NAME, VALID_BEGIN_TIME_NOW, VALID_END_TIME_NOW, VALID_EXAMID);

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
        // set it so exam will not start yet
        LocalTime beginTime = LocalTime.now().plus(2, ChronoUnit.HOURS);
        LocalTime endTime = beginTime.plus(2, ChronoUnit.HOURS);

        // act
        // pass last parameter as null to act like the exam has not been fully set up yet
        ExamSetup es = new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, VALID_NUMBER_OF_CLASSCODES, VALID_EXAM_NAME, beginTime, endTime, null);

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
        //  arrange / act
        // starts exam now
        ExamSetup es = new ExamSetup(VALID_COURSE, VALID_START_DATE_TODAY, VALID_NUMBER_OF_CLASSCODES, VALID_EXAM_NAME, VALID_BEGIN_TIME_NOW, VALID_END_TIME_NOW, VALID_EXAMID);

        // assert
        assertThatExceptionOfType(ExamStartedException.class).isThrownBy(() -> {
            es.setMaterial("material");
        });
    }
}