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






    String currentUser;
    String currentUserPoints;
    Facade facade = Facade.getInstance();
    User selected;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        loadData();
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
        // Create exisiting Comment "Not cylindrical enough" - by Jeff for task  "Curve the metal to make a cylindrical shape"
        facade.login("Atticus Finch", "Finch123");
        //// Create exisiting Comment  "What's a cylinder" by Atticus Finch for task  "Curve the metal to make a cylindrical shape"
        facade.AcceptInvite(0);

        if (facade.login("Atticus Madden", "Madden123")) System.out.println("LOGGED ON");
        else System.out.println("ERROR");

        if (facade.setCurrentProject(facade.getProject(0).getUUID())) System.out.println("project fine");
        else System.out.println("ERROR PROJECT");
    }

    @FXML
    private void removeUser()  throws IOException{
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
        App.setRoot("projectPage");
    }
}
