package project;

import java.io.IOException;

import Code.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;

    @FXML
    private Label lb_e;

    @FXML
    private void switchToMain() throws IOException {
        //Login ability
        String username = txt_username.getText();
        String password = txt_password.getText();
        Facade facade = Facade.getInstance();

        if(username.length() < 8) {
            lb_e.setText("Username must be 8 or more characters");
            return;
        }
        if(password.length() < 8) {
            lb_e.setText("Password must be 8 or more characters");
            return;
        }
        if(facade.login(username,password)) {
            App.setRoot("projectPage");
        }
        else {
            lb_e.setText("Incorrect Username or Password");
            return;
        }

    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("secondary");
    }
}
