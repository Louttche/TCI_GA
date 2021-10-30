package model;

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

class StudentExamTest {

    Student VALID_STUDENT = mock(Student.class);
    ExamID VALID_EXAMID = mock(ExamID.class);
    String VALID_CLASSCODE = "examName-123456";

    /**
     * @verifies throw a NullPointerException if student is null
     * @see StudentExam#StudentExam(Student, ExamID, String)
     */
    @Test
    public void StudentExam_shouldThrowANullPointerExceptionIfStudentIsNull() throws Exception {
        // arrange / act
        // assert
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new StudentExam(null, VALID_EXAMID, VALID_CLASSCODE);
        });
    }

    /**
     * @verifies throw a NullPointerException if examID is null
     * @see StudentExam#StudentExam(Student, ExamID, String)
     */
    @Test
    public void StudentExam_shouldThrowANullPointerExceptionIfExamIDIsNull() throws Exception {
        // arrange / act
        // assert
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new StudentExam(VALID_STUDENT, null, VALID_CLASSCODE);
        });
    }

    /**
     * @verifies throw a NullPointerException if classcode is null
     * @see StudentExam#StudentExam(Student, ExamID, String)
     */
    @Test
    public void StudentExam_shouldThrowANullPointerExceptionIfClasscodeIsNull() throws Exception {
        // arrange / act
        // assert
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
            new StudentExam(VALID_STUDENT, VALID_EXAMID, null);
        });
    }
}