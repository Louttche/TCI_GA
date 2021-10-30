package model;

import java.util.List;
import java.util.Objects;

/**
 * contains the student, the exam he/she took, classcode used and all screenshots, logging, uploaded examwork of a student which took an exam.
 *
 * methods need to be provided which enable adding screenshots, running processes on the student's computer, and several properties which
 * are sent by the EFIT client.
 *
 */
public class StudentExam {
    private Student student;
    private ExamID exam;
    private String classcode; //  are the ECs of the course, e.g., 3

    private List<Object> student_material;

    /**
     *
     * @param student
     * @param examID
     * @param classcode
     *
     * @should throw a NullPointerException if student is null
     * @should throw a NullPointerException if examID is null
     * @should throw a NullPointerException if classcode is null
     */
    public StudentExam(Student student, ExamID examID, String classcode){
        if (student == null)
            throw new NullPointerException("student cannot be null");
        if (examID == null)
            throw new NullPointerException("examID cannot be null");
        if (classcode == null)
            throw new NullPointerException("classcode cannot be null");

        this.student = student;
        this.exam = examID;
        this.classcode = classcode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ExamID getExam() {
        return exam;
    }

    public void setExam(ExamID exam) {
        this.exam = exam;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        StudentExam studentExam = (StudentExam) o;
        return this.student.equals(studentExam.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student);
    }
}
