package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.db_access.DBConnectionImpl;

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
    private String status;

    private DBConnectionImpl dbConnection;

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
                System.out.println("Full name cannot be empty");
                return;
            }
            if(student_id.isEmpty()){
                System.out.println("Student`s id cannot be empty");
                return;
            }
            if(batch.isEmpty()){
                System.out.println("Student`s batch cannot be empty");
                return;
            }
            if(dept.isEmpty()){
                System.out.println("Student`s department cannot be empty");
                return;
            }
            if(pass.isEmpty()){
                System.out.println("Password cannot be empty. Minimum length 8 character.");
                return;
            }
            if(address.isEmpty()){
                System.out.println("Address cannot be empty");
                return;
            }


            // db connection
            Connection connection = dbConnection.openConnection();

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
                System.out.println("Data successfully save");
            }else{
                System.out.println("Data failed to add into database");
            }

            // close connection
            dbConnection.closeConnection(connection);
        }catch (Exception ex){
            ex.printStackTrace();
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
