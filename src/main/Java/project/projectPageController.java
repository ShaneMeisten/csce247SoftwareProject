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
    private TextField tf_newC, tf_newC2;

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
    private void newPro() throws IOException{
        facade.createProject(tf_newC2.getText(), true);
        loadProjects();
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
        facade.logout();
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


    }

}
