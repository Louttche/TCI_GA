package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * An examsetup is related to a specific course. it is identified by the course it is related to, and the startdate.
 *
 * The number of classcodes to be generated can be set.
 * unique classcodes are needed for each needed classroom during the exam. the classcode consists of the name of the exam, followed by a '-', followed by a random string of 6 characters.
 * it is assumed the randomness creates unique codes.
 * two extra classcodes are always generated for extra safety (this means extra classcodes are always added).
 * the name of the extra classcodes are the name of the exam, followed by '-extra-', followed by a random string of 4 characters.
 * a list of all examcodes can be generated.
 *
 * an examname can be given during creation of an exam. when no name is provided, the examname is taken from the course the exam is for.
 * it can always be changed afterwards. an empty examname is not allowed, when this is entered, the name of the course is used instead.
 * it is not allowed to change the name, after the exam has started.
 *  *
 * the begintime of the exam (when it is being set), cannot be after the endtime of the exam. otherwise an IllegalDateException is thrown.
 * the reverse is true for the endtime.
 * instead of an endtime, a begintime + duration in seconds can be given, the endtime is then calculated.
 *
 * the examID of the examSetup can be returned.
 *
 * Exam materials can be added to the exam setup after creation. after the exam has started, no materials can be added.
 *
 *
 */
public class ExamSetup {

    // Identifiers:
    private Course course;
    private LocalDateTime startdate;

    private Integer nrOfClasscodesToGenerate;

    private List<String> classcodes;
    private List<Integer> examcodes;

    private String examname;

    private LocalTime begintime;
    private LocalTime endtime;

    private ExamID examID;

    private String material;

    /**
     *
     * @param course
     * @param startdate
     * @param nrOfClasscodes
     * @param examname
     * @param begintime
     * @param endtime
     * @param examID
     *
     * @should throw an IllegalDateException if begintime is not before endtime
     * @should set the examname to course name if it is empty
     * @should not allow to change examname after exam has started
     * @should generate classcodes with valid size
     * @should always generate at least 2 classcodes when no nrOfClasscodes are given or null
     * @should throw an ExamNotFoundException when exam materials are added before exam gets created
     * @should throw an ExamStartedException when exam materials are added after exam starts
     */
    public ExamSetup(Course course, LocalDateTime startdate, Integer nrOfClasscodes, String examname, LocalTime begintime, LocalTime endtime, ExamID examID){
        this.course = course;
        this.startdate = startdate;
        this.nrOfClasscodesToGenerate = nrOfClasscodes;
        this.examname = examname;
        this.begintime = begintime;
        this.endtime = endtime;
        this.examID = examID;
    }

    /*  it is assumed the randomness creates unique codes.
    *   two extra classcodes are always generated for extra safety (this means extra classcodes are always added).
    *   the name of the extra classcodes are the name of the exam, followed by '-extra-', followed by a random string of 4 characters.
    */
    private List<String> GenerateClassCodes(int amount){
        return null;
    }

    /*
     * a list of all examcodes can be generated.
     */
    private List<String> GenerateExamCodes(){
        return null;
    }

    public ExamID getExamID() {
        return examID;
    }

    public void setNrOfClasscodesToGenerate(Integer nrOfClasscodesToGenerate) {
        this.nrOfClasscodesToGenerate = nrOfClasscodesToGenerate;
    }

    public String getExamname() {
        return examname;
    }

    // it is not allowed to change the name, after the exam has started.
    public void setExamname(String examname) {
        this.examname = examname;
    }

    public LocalTime getBegintime() {
        return begintime;
    }

    public void setBegintime(LocalTime begintime) {
        this.begintime = begintime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public List<String> getClasscodes() {
        return classcodes;
    }

    public void setClasscodes(List<String> classcodes) {
        this.classcodes = classcodes;
    }

    public List<Integer> getExamcodes() {
        return examcodes;
    }

    public void setExamcodes(List<Integer> examcodes) {
        this.examcodes = examcodes;
    }

    public void setExamID(ExamID examID) {
        this.examID = examID;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        ExamSetup examSetup = (ExamSetup) o;

        return this.getCourse().getName().equals(examSetup.getCourse().getName()) && this.getStartdate().equals(examSetup.getStartdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCourse().getName());
    }
}
