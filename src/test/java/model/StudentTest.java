package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private String VALID_NAME = "John";
    private long VALID_STUDENTNUMBER = 3178692;

    /**
     * @verifies throw IllegalArgumentException if studentnumber and name combination exists
     * @see Student#Student(String, long, java.awt.Image)
     */
    @Test
    public void Student_shouldThrowIllegalArgumentExceptionIfStudentnumberAndNameCombinationExists() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies throw NullPointerException if name is null
     * @see Student#Student(String, long, java.awt.Image)
     */
    @Test
    public void Student_shouldThrowNullPointerExceptionIfNameIsNull() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies throw NullPointerException if studentnumber is null
     * @see Student#Student(String, long, java.awt.Image)
     */
    @Test
    public void Student_shouldThrowNullPointerExceptionIfStudentnumberIsNull() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
    //private String VALID_IMAGE = "";

}