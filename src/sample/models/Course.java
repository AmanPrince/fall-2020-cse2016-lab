package sample.models;

import java.sql.Timestamp;

// entity or model class
public class Course {
    private int id;
    private String courseCode;
    private String courseTitle;
    private int courseCreditHour;
    private String courseStatus;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Course() {
    }

    public Course(int id, String courseCode, String courseTitle, int courseCreditHour, String courseStatus, Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseCreditHour = courseCreditHour;
        this.courseStatus = courseStatus;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCourseCreditHour() {
        return courseCreditHour;
    }

    public void setCourseCreditHour(int courseCreditHour) {
        this.courseCreditHour = courseCreditHour;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
