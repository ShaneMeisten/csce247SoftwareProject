package project;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import Code.Facade;
import Code.User;

public class SecondaryController {
    @FXML
    private TextField txt_firstName;
    @FXML
    private TextField txt_lastName;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_phoneNumber;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private String name;

    @FXML
    private void switchToMain() throws IOException{
        String firstName = txt_firstName.getText();
        String lastName = txt_lastName.getText();
        String email = txt_email.getText();
        String phoneNumber = txt_phoneNumber.getText();
        String username = txt_username.getText();
        String password = txt_password.getText();
        name = firstName + " " + lastName;
        User user = new User(name, username, password, phoneNumber, email);
        Facade facade = Facade.getInstance();

        if(facade.createUser(user))
            App.setRoot("projectPage");

    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}