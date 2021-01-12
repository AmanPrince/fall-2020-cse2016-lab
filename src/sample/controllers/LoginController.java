package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.db_access.DBConnectionImpl;
import sample.utils.CommonTask;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public TextField tf_student_id;
    public PasswordField pf_student_pass;

    private DBConnectionImpl dbConnection;
    private Connection connection;
    private boolean isValidUser = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConnection = new DBConnectionImpl();
    }

    public void doLogin(ActionEvent actionEvent) {

        try {
            /**
             * Get data from user
             */

            String id = tf_student_id.getText().trim();
            String pass = pf_student_pass.getText().trim();

            /**
             * data validation
             */

            if(id.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR, "Student ID cannot be null or empty!");
                return;
            }
            if(pass.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR, "Password cannot be null or empty!");
                return;
            }

            /**
             * Connect with database
             */

            connection = dbConnection.openConnection();

            /**
             * SQL statement create
             */

            String sql = "SELECT * FROM student WHERE s_id=? AND password=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);
            statement.setString(2, pass);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet!=null){
                while (resultSet.next()){
                    isValidUser = true;
                    String status = resultSet.getString("status");
                    if(status.equals("A")){
                        CommonTask.getInstance().navigationTo("/sample/fxmls/home.fxml");
                    }else if(status.equals("I")){
                        CommonTask.getInstance().showMessage(Alert.AlertType.ERROR, "Inactive student cannot login to this system!");
                    }else{
                        CommonTask.getInstance().showMessage(Alert.AlertType.ERROR, "Former student cannot login to this system!");
                    }
                }
            }

            if(!isValidUser){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR, "Wrong username or password!!!");
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }finally {
            if(connection != null){
                dbConnection.closeConnection(connection);
            }
        }

        //CommonTask.getInstance().navigationTo("/sample/fxmls/home.fxml");
    }

    public void goToRegistrationPage(ActionEvent actionEvent) {
        CommonTask.getInstance().navigationTo("/sample/fxmls/registration.fxml");
    }
}
