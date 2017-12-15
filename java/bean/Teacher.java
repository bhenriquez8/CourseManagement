package bean;

import bean.Person;
import database.TeacherDAO;

import java.sql.SQLException;
import java.util.List;

public class Teacher extends Person {
    private String designation;

    private TeacherDAO teacherDAO = new TeacherDAO();

    public void addTeacher() throws SQLException {
        teacherDAO.addTeacher(this);
    }

    public List<Teacher> getTeachers() throws SQLException {
        return teacherDAO.getTeachers();
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
