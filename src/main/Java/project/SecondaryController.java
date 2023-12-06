package project;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToMain() throws IOException{
        //Will add the code later
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}