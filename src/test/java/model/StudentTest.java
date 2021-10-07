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

class StudentTest {

    private String VALID_NAME = "John";
    private long VALID_STUDENTNUMBER = 3178692;
    private Image VALID_IMAGE = null;

    /**
     * @verifies throw NullPointerException if name is null
     * @see Student#Student(String, long, java.awt.Image)
     */
    @Test
    public void Student_shouldThrowNullPointerExceptionIfNameIsNull() throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
           new Student(null, VALID_STUDENTNUMBER, VALID_IMAGE);
        });
    }

    /**
     * @verifies throw IllegalArgumentException if studentnumber is invalid
     * @see Student#Student(String, long, Image)
     */
    @ParameterizedTest
    @MethodSource("generateStudentNumberWithInvalidSize")
    public void Student_shouldThrowIllegalArgumentExceptionIfStudentnumberIsInvalidSize(Integer studentNumber) throws Exception {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Student(VALID_NAME, (long)studentNumber, VALID_IMAGE);
        });
    }

    private static Stream<Integer> generateStudentNumberWithInvalidSize() {
        return Stream.of(1, 12, 123, 1234, 12345, 123456, 12435678);
    }

    /**
     * @verifies show if student object already exists
     * @see Student#Student(String, long, java.awt.Image)
     */
    @Test
    public void Student_shouldShowIfStudentObjectAlreadyExists() throws Exception {
        // arrange // act
        Student A = new Student(VALID_NAME, VALID_STUDENTNUMBER, VALID_IMAGE);
        Student B = new Student(VALID_NAME, VALID_STUDENTNUMBER, VALID_IMAGE);

        // assert
        assertThat(A).isEqualTo(B);
        assertThat(A).hasSameHashCodeAs(B);
    }
}