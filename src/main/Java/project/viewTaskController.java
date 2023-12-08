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
        loadData();
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
                lbl_asignee.setText("Assignee: " + currentTask.getAsignee());
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


    public void loadData() {
        User AtticusM = new User("Atticus Madden", "Atticus Madden", "Madden123", "111-111-1111", "AtticusM@gmail.com");
        User Jeff = new User("Jeff Goldblum", "Jeff", "Goldblum123", "111-111-1112", "Jeff@gmail.com");
        User AtticusF = new User("Atticus Finch", "Atticus Finch", "Finch123", "111-111-1113", "AtticusF@gmail.com");
        facade.createUser(AtticusM);
        facade.createUser(Jeff);
        facade.createUser(AtticusF);

        facade.createProject("Electric Missiles");
        //In Electric Missles
        //Create Doing Column
        //Create task "Curve the metal to make a cylindrical shape" to the 'Doing' column.
        //Create task "Make impossible burger possible"
        facade.createProject("Soap Free Washers");
        facade.createProject("Air Computers");

        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(0).getUUID());
        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(1).getUUID());
        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(2).getUUID());
        facade.InviteUserToProject(Jeff.getUUID(), facade.getProject(0).getUUID());
        facade.InviteUserToProject(AtticusF.getUUID(), facade.getProject(0).getUUID());
        facade.login("Atticus Madden", "Madden123");
        facade.AcceptInvite(0);
        facade.AcceptInvite(0);
        facade.AcceptInvite(0);
        facade.login("Jeff", "Goldblum123");
        facade.AcceptInvite(0);
        facade.setCurrentProject(facade.getProject(0).getUUID());
        facade.addColumnToCurrentProject("Doing");
        Task newtask = new Task("Curve the metal to make a cylindrical shape", "none", "none", Jeff);
        Task newtask2 = new Task("Make impossible burger possible", "none", "none", Jeff);
        facade.getCurrentProject().getColumnList().get(0).addTask(newtask);
        facade.getCurrentProject().getColumnList().get(0).addTask(newtask2);
        facade.getCurrentProject().getColumnList().get(0).getTasks().get(0).addComment("Not cylindrical enough", "Jeff", Jeff.getUUID());
        // Create exisiting Comment "Not cylindrical enough" - by Jeff for task  "Curve the metal to make a cylindrical shape"
        facade.login("Atticus Finch", "Finch123");
        facade.getCurrentProject().getColumnList().get(0).getTasks().get(0).addComment("What's a cylinder", "Atticus Finch", AtticusF.getUUID());
        //// Create exisiting Comment  "What's a cylinder" by Atticus Finch for task  "Curve the metal to make a cylindrical shape"
        facade.AcceptInvite(0);



        if (facade.login("Atticus Madden", "Madden123")) System.out.println("LOGGED ON");
        else System.out.println("ERROR");

        if (facade.setCurrentProject(facade.getProject(0).getUUID())) System.out.println("project fine");
        else System.out.println("ERROR PROJECT");
        facade.setCurrentProject(facade.getProject(0).getUUID());
        facade.setCurrentColumn("Doing");



    }
}