package model;

import java.awt.*;

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
     * @should throw IllegalArgumentException if studentnumber and name combination exists
     * @should throw NullPointerException if name is null
     * @should throw NullPointerException if studentnumber is null
     */
    public Student(String name, long studentnumber, Image photo){
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
}
