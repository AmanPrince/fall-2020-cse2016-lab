package sample.controllers;

import javafx.fxml.Initializable;
import sample.db_access.DBConnectionImpl;
import sample.models.Course;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CourseOperation {
    private DBConnectionImpl dbConnection;

    public CourseOperation() {
        dbConnection = new DBConnectionImpl();
    }

    public boolean addNewData(Course course){
        Connection connection = null;
        try{
            connection = dbConnection.openConnection();
            String sql = "INSERT INTO course(course_code,course_title,credit_hour,status) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,course.getCourseCode());
            statement.setString(2, course.getCourseTitle());
            statement.setInt(3,course.getCourseCreditHour());
            statement.setString(4,course.getCourseStatus());

            int result = statement.executeUpdate();
            if(result>0)
                return Boolean.TRUE;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(connection != null){
                dbConnection.closeConnection(connection);
            }
        }
        return Boolean.FALSE;
    }

    public ArrayList<Course> getAllCourse(){
        Connection connection = null;
        ArrayList<Course> courses;
        try{
            courses = new ArrayList<>();
            connection = dbConnection.openConnection();
            String sql = "SELECT * FROM course";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setCourseCode(resultSet.getString("course_code"));
                course.setCourseTitle(resultSet.getString("course_title"));
                course.setCourseCreditHour(resultSet.getInt("credit_hour"));
                course.setCourseStatus(resultSet.getString("status"));
                course.setCreateAt(resultSet.getTimestamp("createAt"));
                course.setUpdateAt(resultSet.getTimestamp("updateAt"));

                courses.add(course);
            }

            return courses;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(connection != null){
                dbConnection.closeConnection(connection);
            }
        }


        return null;
    }

    public boolean updateCourse(Course course){
        Connection connection = null;
        try{
            connection = dbConnection.openConnection();

            String sql = "UPDATE course SET course_code=?,course_title=?,credit_hour=?,status=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, course.getCourseCode());
            statement.setString(2,course.getCourseTitle());
            statement.setInt(3,course.getCourseCreditHour());
            statement.setString(4,course.getCourseStatus());
            statement.setInt(5,course.getId());

            int updateResult = statement.executeUpdate();
            if(updateResult>0){
                return Boolean.TRUE;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(connection!=null)
                dbConnection.closeConnection(connection);
        }
        return Boolean.FALSE;
    }

    public boolean deleteCourse(Course course){
        Connection connection = null;
        try{
            connection = dbConnection.openConnection();

            String sql = "DELETE FROM course WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,course.getId());

            return statement.executeUpdate()>0;

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(connection != null)
                dbConnection.closeConnection(connection);
        }
        return Boolean.FALSE;
    }
}
