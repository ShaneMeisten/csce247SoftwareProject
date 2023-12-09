package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import Code.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class projectPageController implements Initializable {

    Facade facade = Facade.getInstance();

    @FXML
    private ListView invitedList, currentList;
    private UUID currentp, invite;

    @FXML
    private TextField tf_newC;

    public void initialize(URL arg0, ResourceBundle arg1) {
        loadData();
        String[] ok = {"Apple", "Orange", "GigaGrapes"};
        loadProjects();

        currentList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                String current = (String) currentList.getSelectionModel().getSelectedItem();
                for(Project project: facade.getUserCurrentProjects()) {
                    if(project.getName().equals(current))
                    {
                        currentp = project.getUUID();
                        break;
                    }
                }
            }
        });
        invitedList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                String current = (String) invitedList.getSelectionModel().getSelectedItem();
                for(Project project: facade.getUserInvitedProjects()) {
                    if(project.getName().equals(current))
                    {
                        invite = project.getUUID();
                        break;
                    }
                }
            }
        });


    }

    @FXML
    private void openUsers() throws IOException{
        if(currentp == null) return;
        System.out.println("LOADING");
        facade.setCurrentProject(currentp);
        for (Column column: facade.viewColumns()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("userPage.fxml"));
                Parent root1 = (Parent) loader.load();
                userPageController controller = loader.<userPageController>getController();
                Stage stage = new Stage();
                stage.setTitle("Users in : " + facade.getCurrentProject().getName());
                stage.setScene(new Scene(root1));
                stage.show();

            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void AcceptInvite() throws IOException{
        if(invite == null) return;
        facade.AcceptInvite(invite);
        loadProjects();
    }

    @FXML
    private void CreateCol() throws IOException{
        if(facade.getCurrentProject() == null) return;
        String text = tf_newC.getText();
        System.out.println("FOUND");
        facade.addColumnToCurrentProject(text);
        //.getCurrentProject().addColumn(text);
        //facade.getCurrentProject().getColumnList()
        for (Column column: facade.getCurrentProject().getColumnList()) {
            if(column.getTitle().equals(text)){
                System.out.println("MATCH");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("viewColumn.fxml"));
                    Parent root1 = (Parent) loader.load();
                    viewColumnController controller = loader.<viewColumnController>getController();
                    controller.setName(column);
                    Stage stage = new Stage();
                    stage.setTitle("Column: " + column.getTitle());
                    stage.setScene(new Scene(root1));
                    stage.show();

                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    @FXML
    private void removeProject() throws IOException{
        if(currentp == null) return;
        facade.RemoveProject(currentp);
        loadProjects();
    }

    @FXML
    private void logout() throws IOException{
        System.exit(0);
    }

    @FXML
    private void loadP() throws IOException{
        if(currentp == null) return;
        System.out.println("LOADING");
        facade.setCurrentProject(currentp);
        for (Column column: facade.viewColumns()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewColumn.fxml"));
                Parent root1 = (Parent) loader.load();
                viewColumnController controller = loader.<viewColumnController>getController();
                controller.setName(column);
                Stage stage = new Stage();
                stage.setTitle("Column: " + column.getTitle());
                stage.setScene(new Scene(root1));
                stage.show();

            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    private void loadProjects() {
        invitedList.getItems().clear();
        currentList.getItems().clear();
        for(Project project : facade.getUserInvitedProjects()) {
            invitedList.getItems().add(project.getName());
        }

        for(Project project: facade.getUserCurrentProjects()) {
            currentList.getItems().add(project.getName());
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
        facade.AcceptInvite(facade.getProject(0).getUUID());
        facade.AcceptInvite(facade.getProject(0).getUUID());
        facade.AcceptInvite(facade.getProject(0).getUUID());
        facade.login("Jeff", "Goldblum123");
        facade.AcceptInvite(facade.getProject(0).getUUID());
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
        facade.AcceptInvite(facade.getProject(0).getUUID());



        if (facade.login("Atticus Madden", "Madden123")) System.out.println("LOGGED ON");
        else System.out.println("ERROR");

        //if (facade.setCurrentProject(facade.getProject(0).getUUID())) System.out.println("project fine");
        //else System.out.println("ERROR PROJECT");




    }

}
