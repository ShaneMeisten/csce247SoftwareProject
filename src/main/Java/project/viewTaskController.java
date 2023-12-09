package project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import Code.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class viewTaskController implements Initializable {

    @FXML
    private ListView<String> taskListView, commentsListView;

    private Task currentTask;

    private String currentTaskName;
    @FXML
    private Label lbl_removeError;

    @FXML
    private Label lbl_taskName, lbl_columnName, lbl_asignee, lbl_author;

    @FXML
    private Text txt_description;

    @FXML
    private TextArea tf_comment;
    @FXML
    private TextField tf_search;


    @FXML
    private BorderPane borderplane;



    Facade facade = Facade.getInstance();


    public void initialize(URL arg0, ResourceBundle arg1) {
        //loadData();
        loadTask();
    }

    private void loadTask() {
        taskListView.getItems().clear();
        for(Task task: facade.getCurrentColumn().getTasks()) {
            taskListView.getItems().add(task.getTitle());
        }

        taskListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currentTaskName = taskListView.getSelectionModel().getSelectedItem();
                for(Task task: facade.getCurrentColumn().getTasks()) {
                    if (task.getTitle().equals(currentTaskName)) currentTask = task;
                }
                lbl_taskName.setText(currentTaskName);
                lbl_columnName.setText("Viewing Task For " + facade.getCurrentColumn().getTitle());
                txt_description.setText(currentTask.getDescription());
                lbl_asignee.setText("Assignee: " + currentTask.getAssignee());
                lbl_author.setText("Author: " + currentTask.getAuthor().getUsername());
                loadTaskComments();
                facade.setCurrentTask(currentTask);
            }
        });
    }

    private void loadTaskComments() {
        commentsListView.getItems().clear();
        for(Comment comment: currentTask.getComment()) {
            commentsListView.getItems().add(comment.getName() + ": " + comment.getDescription() + "\n" + comment.getDate());
        }
        if(commentsListView.getItems().size() == 0) {
            commentsListView.getItems().add("No Comments");
        }
    }

    @FXML
    private void removeTask()  throws IOException{
        facade.getCurrentColumn().removeTask(currentTask);
        loadTask();
    }
    @FXML
    private void AddComment()  throws IOException{
        String text = tf_comment.getText();
        currentTask.addComment(text, facade.getCurrentUser().getUsername(), facade.getCurrentUser().getUUID());
        tf_comment.setText("");
        loadTaskComments();
    }

    //src\main\resources\project\viewTask.fxml
    //src\main\resources\project\createTask.fxml

    /*
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
     */

    @FXML
    private void exitThis()  throws IOException{
        Stage stage = (Stage) borderplane.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void addTask()  throws IOException{

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createTask.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Task");
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void editPop()  throws IOException{
        if(currentTask == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editTask.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Editing Task: " + currentTask.getTitle());
            stage.setScene(new Scene(root1));
            stage.show();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void searchItem()  throws IOException{
        String search = tf_search.getText();
        if(search.isBlank()) {
            loadTask();
            return;
        }
        tf_search.setText("");
        currentTaskName = taskListView.getSelectionModel().getSelectedItem();
        for(Task task: facade.getCurrentColumn().getTasks()) {
            if (task.getTitle().equals(currentTaskName)) currentTask = task;
        }
        taskListView.getItems().clear();
        for(Task task: facade.getCurrentColumn().getTasks()) {
            if(task.getTitle().substring(0, search.length()).toLowerCase().equals(search.toLowerCase())) {
                taskListView.getItems().add(task.getTitle());
            }
        }

    }



}