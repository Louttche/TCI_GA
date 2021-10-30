package model;

import java.awt.*;
import java.util.Objects;

/**
 * student is an immutable object. it's used for passing around information on students.
 * it is uniquely identified by the studentnumber and name combination.
 */
public class Student {
    private String name;
    private long studentnumber;
    private Image photo;

    /**
     *
     * @param name
     * @param studentnumber
     * @param photo
     *
     * @should show if student object already exists
     * @should throw NullPointerException if name is null
     * @should throw IllegalArgumentException if studentnumber is invalid size
     */
    public Student(String name, long studentnumber, Image photo){
        if (name == null)
            throw new NullPointerException("Student name cannot be null.");
        if (Long.toString(studentnumber).length() != 7)
            throw new IllegalArgumentException("Student number needs to be 7 digits long");

        this.name = name;
        this.studentnumber = studentnumber;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(long studentnumber) {
        this.studentnumber = studentnumber;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;

        return this.name.equals(student.name) && this.studentnumber == student.studentnumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentnumber);
    }
}
