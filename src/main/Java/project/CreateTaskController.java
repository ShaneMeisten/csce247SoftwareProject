package project;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Code.Facade;
import Code.Task;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CreateTaskController {

    @FXML
    private TextField txt_title;
    @FXML
    private TextField txt_weight;
    @FXML
    private TextField txt_compTime;
    @FXML
    private TextField txt_categories;
    @FXML
    private TextField txt_assignedUser;
    @FXML
    private TextField txt_dueDate;
    @FXML
    private TextField txt_description;


    @FXML
    private AnchorPane ap;
    @FXML
    private void switchToMain() throws IOException, ParseException {
        String title = txt_title.getText();
        String assignedUser = txt_assignedUser.getText();
        String description = txt_description.getText();

        Task task = new Task(title, description, assignedUser);
        Facade facade = Facade.getInstance();
        facade.getCurrentColumn().addTask(task);
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();


    }

    @FXML
    private void switchBack() throws IOException{
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

}
