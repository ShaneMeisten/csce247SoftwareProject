package project;

import Code.Facade;
import Code.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;





public class editTaskController implements Initializable {

    @FXML
    private TextField tf_title, tf_ass;

    @FXML
    private TextArea ta_des;

    @FXML
    private Text lbl_title;

    @FXML
    private BorderPane bp;
    Facade facade = Facade.getInstance();

    private Task currentTask = facade.getCurrentTask();

    public void initialize(URL arg0, ResourceBundle arg1) {
        tf_title.setText(currentTask.getTitle());
        tf_ass.setText(currentTask.getAsignee());
        ta_des.setText(currentTask.getDescription());
        lbl_title.setText("Currently Editing: " + currentTask.getTitle());
    }

    @FXML
    private void confirmC()  throws IOException {
        facade.getCurrentTask().setTitle(tf_title.getText());
        facade.getCurrentTask().setAssignee(tf_ass.getText());
        facade.getCurrentTask().setDescription(ta_des.getText());
        Stage stage = (Stage) bp.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelC()  throws IOException {
        Stage stage = (Stage) bp.getScene().getWindow();
        stage.close();
    }
}
