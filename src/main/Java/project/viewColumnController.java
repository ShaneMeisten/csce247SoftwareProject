package project;

import Code.Column;
import Code.Facade;
import Code.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class viewColumnController implements Initializable {

    @FXML
    private Text txt_name;

    @FXML
    private BorderPane bp;

    @FXML
    private ListView viewTaskList;
    private Column column;

    Facade facade = Facade.getInstance();

    public void initialize(URL arg0, ResourceBundle arg1){
        viewTaskList.getItems().clear();

    }

    public void setName(Column column) {
        txt_name.setText(column.getTitle());
        for(Task task: column.getTasks()) {
            viewTaskList.getItems().add(task.getTitle());
        }
        this.column = column;
    }

    @FXML
    private void createTask() throws IOException {
        facade.setCurrentColumn(column.getTitle());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createTask.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("NewTask: " + column.getTitle());
            stage.setScene(new Scene(root1));
            stage.show();
            facade.setCurrentColumn(column.getTitle());

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void viewColumnInsides() throws IOException {
        facade.setCurrentColumn(column.getTitle());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewTask.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Viewing Column: " + column.getTitle());
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void removeColumn() throws IOException {

        int counter = 0;
        facade.getCurrentProject().viewColumns().remove(column);

            counter++;
        Stage stage = (Stage) bp.getScene().getWindow();
        stage.close();
    }
}
