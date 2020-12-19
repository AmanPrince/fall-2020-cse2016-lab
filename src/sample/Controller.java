package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public CheckBox cb_option1;
    public CheckBox cb_option2;
    public CheckBox cb_option3;
    public CheckBox cb_option4;
    public RadioButton rb_1;
    public RadioButton rb_2;
    public RadioButton rb_3;
    public RadioButton rb_4;
    public ComboBox<String> cb_gender;
    public ListView<Integer> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ToggleGroup toggleGroup = new ToggleGroup();

        rb_1.setToggleGroup(toggleGroup);
        rb_2.setToggleGroup(toggleGroup);
        rb_3.setToggleGroup(toggleGroup);
        rb_4.setToggleGroup(toggleGroup);

        String[] gender = {"Male","Female","Others"};
        cb_gender.getItems().addAll(gender);

        /*cb_gender.getItems().add("Male");
        cb_gender.getItems().add("Female");
        cb_gender.getItems().add("Others");*/

        cb_gender.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Old Value: "+oldValue);
                System.out.println("New Value: "+newValue);
            }
        });

        // ListView
        listView.setOrientation(Orientation.VERTICAL);

        // listView data add
        Random random = new Random();
        for(int i=0;i<100;i++){
            listView.getItems().add(random.nextInt(1000));
        }


    }

    public void doPerformCheckBox(ActionEvent actionEvent) {
        /**
         * Below of the code is for the CheckBox selected options
         */
        if(cb_option1.isSelected()){
            System.out.println(cb_option1.getText()+" is selected");
        }

        if(cb_option2.isSelected()){
            System.out.println(cb_option2.getText()+" is selected");
        }

        if(cb_option3.isSelected()){
            System.out.println(cb_option3.getText()+" is selected");
        }

        if(cb_option4.isSelected()){
            System.out.println(cb_option4.getText()+" is selected");
        }

        /**
         * Below of the code is for RadioButton selected options
         */
        if(rb_1.isSelected()){
            System.out.println(rb_1.getText()+" is selected");
        }
        if(rb_2.isSelected()){
            System.out.println(rb_2.getText()+" is selected");
        }
        if(rb_3.isSelected()){
            System.out.println(rb_3.getText()+" is selected");
        }
        if(rb_4.isSelected()){
            System.out.println(rb_4.getText()+" is selected");
        }

        /**
         * Below of the code is for ComboBox selected options
         */
        System.out.println(cb_gender.getSelectionModel().getSelectedItem());

        /**
         * Below of the code for the ListView selected options
         */

        System.out.println(listView.getSelectionModel().getSelectedItem());

    }
}
