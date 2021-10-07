package model;

import java.util.Date;
import java.util.List;

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
    private Date startdate;

    private Integer nrOfClasscodesToGenerate;

    private List<String> classcodes; // the classcode consists of the name of the exam, followed by a '-', followed by a random string of 6 characters.
    private List<Integer> examcodes;

    /*  an examname can be given during creation of an exam. when no name is provided, the examname is taken from the course the exam is for.
     *  it can always be changed afterwards. an empty examname is not allowed, when this is entered, the name of the course is used instead.
     */
    private String examname;

    /*  the begintime of the exam (when it is being set), cannot be after the endtime of the exam. otherwise an IllegalDateException is thrown.
     *  the reverse is true for the endtime.
     *  instead of an endtime, a begintime + duration in seconds can be given, the endtime is then calculated.
     */
    private String begintime;
    private String endtime;

    private ExamID examID;

    //exam materials?

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
     * @should throw an IllegalDateException if endtime is not after begintime
     * @should set the examname to course name if it is empty
     * @should not allow to change examname after exam has started
     * @should generate classcode with valid format
     * @should generate extra classcodes with valid format
     *
     */
    //should only add exam materials after exam gets created and before it starts
    public ExamSetup(Course course, Date startdate, Integer nrOfClasscodes, String examname, String begintime, String endtime, ExamID examID){
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


    /*  the begintime of the exam (when it is being set), cannot be after the endtime of the exam. otherwise an IllegalDateException is thrown.
     *  the reverse is true for the endtime. */
    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
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
}
