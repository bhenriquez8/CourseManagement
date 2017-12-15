package bean;

import database.CourseDAO;

import java.sql.SQLException;
import java.util.List;

public class Course {
    private int id;
    private String name;
    private int credits;
    private Teacher teacher;
    private int teacherID;

    public int getTeacherID() {
        return teacherID;
    }

    private CourseDAO courseDAO = new CourseDAO();

    public void addCourse() throws SQLException {
        courseDAO.addCourse(this);
    }

    public List<Course> getCourses() throws SQLException {
        return courseDAO.getCourses();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        setTeacherID(teacher.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isValidCourse() {
        return name != null && credits != 0;
    }
}
