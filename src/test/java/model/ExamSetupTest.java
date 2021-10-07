package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import model.Course;

import java.awt.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ExamSetupTest {

    /**
     * @verifies throw an IllegalDateException if begintime is not before endtime
     * @see ExamSetup#ExamSetup(Course, java.util.Date, Integer, String, String, String, ExamID)
     */
    @Test
    public void ExamSetup_shouldThrowAnIllegalDateExceptionIfBegintimeIsNotBeforeEndtime() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies throw an IllegalDateException if endtime is not after begintime
     * @see ExamSetup#ExamSetup(Course, java.util.Date, Integer, String, String, String, ExamID)
     */
    @Test
    public void ExamSetup_shouldThrowAnIllegalDateExceptionIfEndtimeIsNotAfterBegintime() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies set the examname to course name if it is empty
     * @see ExamSetup#ExamSetup(Course, java.util.Date, Integer, String, String, String, ExamID)
     */
    @Test
    public void ExamSetup_shouldSetTheExamnameToCourseNameIfItIsEmpty() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies not allow to change examname after exam has started
     * @see ExamSetup#ExamSetup(Course, java.util.Date, Integer, String, String, String, ExamID)
     */
    @Test
    public void ExamSetup_shouldNotAllowToChangeExamnameAfterExamHasStarted() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies generate classcode with valid format
     * @see ExamSetup#ExamSetup(Course, java.util.Date, Integer, String, String, String, ExamID)
     */
    @Test
    public void ExamSetup_shouldGenerateClasscodeWithValidFormat() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies generate extra classcodes with valid format
     * @see ExamSetup#ExamSetup(Course, java.util.Date, Integer, String, String, String, ExamID)
     */
    @Test
    public void ExamSetup_shouldGenerateExtraClasscodesWithValidFormat() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}