package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.db_access.DBConnectionImpl;
import sample.utils.CommonTask;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable, EventHandler<ActionEvent> {

    public TextField tf_fullname;
    public TextField tf_id;
    public TextField tf_batch;
    public TextField tf_dept;
    public PasswordField tf_password;
    public ComboBox cb_status;
    public TextArea ta_address;
    private String status="";

    private DBConnectionImpl dbConnection;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] studentsStatus = {"Active Student","Inactive Student","Former Student"};
        cb_status.getItems().addAll(studentsStatus);

        cb_status.setOnAction(this);

        dbConnection = new DBConnectionImpl();
    }

    public void doRegistration(ActionEvent actionEvent) {

        try{

            // get all data from user
            String full_name = tf_fullname.getText().trim();
            String student_id = tf_id.getText().trim();
            String batch = tf_batch.getText().trim();
            String dept = tf_dept.getText().trim();
            String pass = tf_password.getText().trim();
            String address = ta_address.getText().trim();


            // validation check
            if(full_name.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Full name cannot be empty!!");
                return;
            }
            if(student_id.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"ID cannot be empty!!");
                return;
            }
            if(batch.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Batch cannot be empty!!");
                return;
            }
            if(dept.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Department cannot be empty!!");
                return;
            }
            if(pass.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Password cannot be empty!!");
                return;
            }
            if(address.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Address cannot be empty!!");
                return;
            }
            if(status.isEmpty()){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Status cannot be empty!!");
                return;
            }


            // db connection
            connection = dbConnection.openConnection();

            if(connection==null){
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Cannot connect with database!");
                return;
            }

            // save data into database
            String sql = "INSERT INTO student(name, dept, batch, s_id, password, address, status) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, full_name);
            statement.setString(2, dept);
            statement.setInt(3, Integer.parseInt(batch));
            statement.setString(4, student_id);
            statement.setString(5,pass);
            statement.setString(6,address);
            statement.setString(7,status);

            // execute statement

            int result = statement.executeUpdate();

            if(result>0){
                CommonTask.getInstance().showMessage(Alert.AlertType.CONFIRMATION,"Registration completed!!");
                // back to the login page
                CommonTask.getInstance().navigationTo("/sample/fxmls/login.fxml");
            }else{
                CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Registration failed! Please try again.");
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(connection != null){
                dbConnection.closeConnection(connection);
            }
        }
    }


    @Override
    public void handle(ActionEvent event) {
        String value = (String) cb_status.getValue();
        if(value.equals("Active Student")){
            status = "A";
        }else if(value.equals("Inactive Student")){
            status = "I";
        }else{
            status = "F";
        }
    }
}
