package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import sample.utils.CommonTask;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void doLogin(ActionEvent actionEvent) {
        CommonTask.getInstance().navigationTo("/sample/fxmls/home.fxml");
    }

    public void goToRegistrationPage(ActionEvent actionEvent) {
        CommonTask.getInstance().navigationTo("/sample/fxmls/registration.fxml");
    }
}
