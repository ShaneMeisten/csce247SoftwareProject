package project;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Code.Facade;
import Code.Task;
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
    private void switchToMain() throws IOException, ParseException {
        String title = txt_title.getText();
        double weight = Double.parseDouble(txt_weight.getText());
        double compTime = Double.parseDouble(txt_compTime.getText());
        String categories = txt_categories.getText();
        String assignedUser = txt_assignedUser.getText();
        String Duedate = txt_dueDate.getText();
        String description = txt_description.getText();
        Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(Duedate);

        Task task = new Task(title,weight,compTime,categories,assignedUser,dueDate,description);
        Facade facade = Facade.getInstance();
        if(facade.addTaskToTaskList(task))
            App.setRoot("projectPage");


    }
    @FXML
    private void switchBack() throws IOException{
        App.setRoot("projectPage");
    }

}
