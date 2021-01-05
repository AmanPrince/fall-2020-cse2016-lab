package sample.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public static CommonTask getInstance(){
        if(commonTask == null){
            commonTask = new CommonTask();
        }
        return commonTask;
    }
}
