package project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import Code.Facade;
import Code.History;
import Code.Update;
import Code.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class userPageController implements Initializable {

    @FXML
    private ListView<String> myListView;
    @FXML
    private ListView<String> HistoryListView;
    @FXML
    private Label txt_points;

    @FXML
    private Label txt_username;

    @FXML
    private Label add_error;

    @FXML
    private Label lbl_removeError;
    @FXML
    private TextField txt_locate;

    @FXML
    private BorderPane bp;






    String currentUser;
    String currentUserPoints;
    Facade facade = Facade.getInstance();
    User selected;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        loadUsers();



    }

    private void loadUsers() {
        ArrayList<String> usernames = new ArrayList<>();
        System.out.println(facade.seeUsersInCurrentProject().size());
        for (User user: facade.seeUsersInCurrentProject()) {
            usernames.add(user.getUsername());
        }
        Collections.sort(usernames);
        myListView.getItems().addAll(usernames);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                currentUser = myListView.getSelectionModel().getSelectedItem();
                System.out.println(currentUser);
                selected = facade.getUser(currentUser);
                currentUserPoints = "Points: " + selected.getProjectPoints(facade.getCurrentProject().getUUID());
                txt_username.setText(currentUser);
                txt_points.setText(currentUserPoints);
                changeHistory();

            }
        });
    }

    private void changeHistory() {
        HistoryListView.getItems().clear();
        ArrayList<Update> selectedHistory = facade.getCurrentProjectHistory().accessHistoryChangedByUUID(selected.getUUID());
        if (selectedHistory == null || selectedHistory.size() == 0) {
            HistoryListView.getItems().add("No Changes Made by User");
        }
        else {
            for (Update update : selectedHistory) {
                HistoryListView.getItems().add(update.toString());
            }
        }
    }


    @FXML
    private void removeUser()  throws IOException {
        if(currentUser.isBlank()) return;
        if(facade.removeUserFromCurrentProject(currentUser)) {
            loadUsers();
        }
        else {
            lbl_removeError.setText("Error: Role not moderator");
        }
    }

    @FXML
    private void inviteUser()  throws IOException{
        String invitedUser = txt_locate.getText();
        if(facade.addUserToCurrentProject(invitedUser)) {
            txt_locate.setText("");
            add_error.setText("User Invited to Project");
        }
        else {
            add_error.setText("Error: invited project");
        }
    }

    @FXML
    private void goBack()  throws IOException{
        Stage stage = (Stage) bp.getScene().getWindow();
        stage.close();
    }
}
