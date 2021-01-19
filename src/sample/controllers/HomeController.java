package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.Course;
import sample.utils.CommonTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    // course related variables
    public TextField tf_course_code;
    public TextField tf_course_title;
    public ComboBox cb_credit_hour;
    public ComboBox cb_course_status;
    public TableView<Course> course_tableView;
    public TableColumn<Course,Integer> col_sl_no;
    public TableColumn<Course,String> col_course_code;
    public TableColumn<Course,String> col_course_title;
    public TableColumn<Course,Integer> col_credit_hour;
    public TableColumn<Course,String> col_course_status;
    public TableColumn col_course_action;
    private int creditHour;
    private String courseStatus;
    private CourseOperation courseOperation;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] creditHourArray = {"Select course type", "Lab", "Theory"};
        String[] courseStatusArray = {"select course status","Active","Inactive"};

        cb_credit_hour.getItems().addAll(creditHourArray);
        cb_course_status.getItems().addAll(courseStatusArray);

        cb_credit_hour.getSelectionModel().select(0);
        cb_course_status.getSelectionModel().select(0);

        cb_credit_hour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String value = (String) cb_credit_hour.getValue();
                if(value.equals("Lab")){
                    creditHour = 1;
                }else if(value.equals("Theory")){
                    creditHour = 3;
                }else{
                    creditHour = -1;
                }
            }
        });

        cb_course_status.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String value = (String) cb_course_status.getValue();
                if(value.equals("Active")){
                    courseStatus = "A";
                }else if(value.equals("Inactive")){
                    courseStatus = "I";
                }else{
                    courseStatus = "";
                }
            }
        });

        courseOperation = new CourseOperation();

        // map table col with model class variable
        col_sl_no.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_course_code.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        col_course_title.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        col_credit_hour.setCellValueFactory(new PropertyValueFactory<>("courseCreditHour"));
        col_course_status.setCellValueFactory(new PropertyValueFactory<>("courseStatus"));

        // get all data from database
        setCourseDataIntoTableView();
    }

    public void addANewCourse(ActionEvent actionEvent) {
        // validation

        Course course = new Course();

        course.setCourseCode(tf_course_code.getText());
        course.setCourseTitle(tf_course_title.getText());
        course.setCourseCreditHour(creditHour);
        course.setCourseStatus(courseStatus);


        boolean output = courseOperation.addNewData(course);
        if(output){
            CommonTask.getInstance().showMessage(Alert.AlertType.CONFIRMATION,"Course added successfully");
            setCourseDataIntoTableView();
            clearFields();
        }else{
            CommonTask.getInstance().showMessage(Alert.AlertType.ERROR,"Failed to add course into database.");
        }
    }

    private void clearFields() {
        tf_course_code.setText("");
        tf_course_title.setText("");
        creditHour=-1;
        courseStatus="";
        cb_course_status.getSelectionModel().select(0);
        cb_credit_hour.getSelectionModel().select(0);
    }

    private void setCourseDataIntoTableView(){
        if(course_tableView!= null)
            course_tableView.getItems().clear();
        ArrayList<Course> allCourse = courseOperation.getAllCourse();
        if(allCourse != null)
            course_tableView.getItems().addAll(allCourse);
    }
}
