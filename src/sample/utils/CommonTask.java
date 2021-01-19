package sample.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import sample.Main;


public class CommonTask {


    static CommonTask commonTask;

    public void navigationTo(String destination){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(destination));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Main.getStage().setScene(scene);
            Main.getStage().show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void showMessage(Alert.AlertType type, String msg){
        Alert alert = new Alert(type);
        alert.setTitle("Pre Registration System");
        alert.setContentText(msg);
        alert.show();
    }

    public static CommonTask getInstance(){
        if(commonTask == null){
            commonTask = new CommonTask();
        }
        return commonTask;
    }
}
