package project;

import java.io.IOException;

import Code.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;

    @FXML
    private void switchToMain() throws IOException {

        //Login ability
        String username = txt_username.getText();
        String password = txt_password.getText();
        Facade facade = Facade.getInstance();

        if(username.length() < 8) {
            //Add error message
            return;
        }
        if(password.length() < 8) {
            //Add error message
            return;
        }
        if(facade.login(username,password)) {
            //App.setRoot("secondary");
        }
        else {
            //Add error message
            return;
        }

    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("secondary");
    }
}
