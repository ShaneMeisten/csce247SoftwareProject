package Code;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Facade {

    private static Facade facade;

    private ProjectCatalog projectCatalog;
    private UserCatalog userCatalog;
    private Project currentProject;
    private User currentUser;
    private Column column;
    private Column currentColumn;

    private Task currentTask;



    /*
     * Current Methods:
     * login
     * getUserCurrentProjects
     * getUserInvitedProjects
     * AcceptInvite -Accepts Current user invite
     * RemoveProject - Removes a Project from current user
     * setCurrentProject - Allows user if logged in to set a current project if in one
     * addCurrentUserPointsToCurrentProject
     * RemoveCurrentUserPointsToCurrentProject
     * seeUsersInCurrentProject
     * removeUserFromCurrentProject
     * getLeaderBoard
     * addUserToCurrentProject
     * RemovePointsFromUserInCurrentProject
     * CreateProject
     * viewColumn
     * viewColumns
     * viewUnassingedTask
     * 
     * Methods not implemented:
     * 
     * Methods to Add:
     * Comment on task
     * view task
     * 
     * 
     * 
     */

    private Facade() {
        projectCatalog = ProjectCatalog.getInstance();
        userCatalog = UserCatalog.getInstance();
        //DataLoader dataLoader= new DataLoader();
    }
    public static Facade getInstance(){
        if (facade == null) facade = new Facade();
        return facade;
    }


    public void createProject(String name) {
        projectCatalog.addProject(name);
    }


    public void setCurrentTask(Task task) {

        currentTask = currentColumn.viewTask(task);
    }

    public Task getCurrentTask() {
        return  currentTask;
    }
    public void printCurrentProject(String filename) {
         try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Projects:\n"+projectCatalog.getProjects()+"\nColumns:\n"+
                        currentProject.viewColumns()+"\nTasks:\n"+currentProject.getCompletedTasks()+currentProject.getOngoingTasks());
        }catch (IOException e){
            System.out.println("Error encountered!");
            e.printStackTrace();
        }
    }

    
    public boolean login(String username, String password) {
        currentUser = userCatalog.retrieveUser(username, password);
        if(currentUser != null) return true;
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<Project> getUserCurrentProjects() {
        if (currentUser == null) return null;
        if (currentUser.getCurrentProjects().size() == 0) return null;
        return projectCatalog.readUserProjectUUID(currentUser.getCurrentProjectsUUID());
    }

    public ArrayList<Project> getUserInvitedProjects() {
        if (currentUser == null) return null;
        return projectCatalog.readUserProjectUUID(currentUser.getInvitedProjects());
    }

    public boolean AcceptInvite(UUID projectUUID) {
        if (currentUser == null) return false;
        currentUser.AcceptInvite(projectUUID);
        return true;
    }

    //Will implement later
    /* 
    public void createProject(Project project){  
        projectCatalog.addProject(project.getName(), project.getType());
      
    }
    */

    public boolean RemoveProject(UUID invite) {
        if (currentUser == null) return false;
        currentUser.RemoveProject(invite);
        return true;
    }

    public boolean setCurrentProject(UUID projectID) {
        if(currentUser == null) return false;
        currentProject  = projectCatalog.getProject(projectID);
        return true;
    }

    public boolean addCurrentUserPointsToCurrentProject(int points) {
        if (currentUser == null || currentProject == null) return false;
        currentUser.addPoints(currentProject.getUUID(), points);
        return true;
    }
    
    public boolean RemoveCurrentUserPointsToCurrentProject(int points) {
        if (currentUser == null || currentProject == null) return false;
        currentUser.removePoints(currentProject.getUUID(), points);
        return true;
    }

    public ArrayList<User> seeUsersInCurrentProject() {
        if(currentUser == null || currentProject == null) return null;
        ArrayList<User> unorganized = userCatalog.getUsersInProjectUUID(currentProject.getUUID());

        return userCatalog.getUsersInProjectUUID(currentProject.getUUID());
    }

    public boolean removeUserFromCurrentProject (String username) {
        if(currentUser == null || currentProject == null) return false;
        for (User toRemove: seeUsersInCurrentProject()) {
            if (toRemove.checkUsername(username)) {
                if (userCatalog.removeUser(currentUser, toRemove.getUUID())) {
                    String changelog = currentUser.getUsername() + "has removed " + toRemove.getUsername()
                            + "[" + toRemove.getUUID() + "] from the project: " + currentProject.getName();
                    currentProject.addHistory(currentUser.getUUID(), currentProject.getUUID(), changelog);
                    return true;
                }
                return  false;
            }
        }
        return  false;
    }

    public ArrayList<User> getLeaderBoard() {
        if(currentProject == null) return null;
        return userCatalog.getLeaderboard(currentProject.getUUID());
    }

    //Either user has pending invite or not invited
    public boolean addUserToCurrentProject(String username) {
        if(currentProject == null || currentUser == null) return false;
        for (User user: seeUsersInCurrentProject()) {
            if (user.checkUsername(username)) {
                return userCatalog.inviteUserToProject(user.getUUID(), currentProject.getUUID());
            }
        }
        return false;
    }

    public void InviteUserToProject(UUID user, UUID project) {
        userCatalog.inviteUserToProject(user, project);
    }

    public User getUser(String username) {
        for(User user: userCatalog.getUsers()) {
            if (user.checkUsername(username))
                return user;
        }
        return  null;
    }

    public History getCurrentProjectHistory() {
        return  currentProject.getHistory();
    }

    public boolean RemovePointsFromUserInCurrentProject(int user, int points) {
        if(currentUser.getAdminPerms() == false || currentProject == null) return false;
        if(user < 0 || user >= seeUsersInCurrentProject().size()) return false;
        return seeUsersInCurrentProject().get(user).removePoints(currentProject.getUUID(), points);
    }

    public boolean AddPointsToUserInCurrentProject(int user, int points) {
        if(currentUser.getAdminPerms() == false || currentProject == null) return false;
        if(user < 0 || user >= seeUsersInCurrentProject().size()) return false;
        return seeUsersInCurrentProject().get(user).addPoints(currentProject.getUUID(), points);
    }

    public boolean createUser(User user) {
        return userCatalog.addUser(user);
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public Project getProject(int project) {
        return projectCatalog.getProjects().get(project);
    }

      public Column viewColumn(String title) {
        if(currentProject == null) return null;
        return currentProject.viewColumn(title);
    }

    public ArrayList<Column> viewColumns() {
        if(currentProject == null) return null;
        return currentProject.viewColumns();
    }

    public Task viewUnassingedTask(String title) {
        if(currentProject == null) return null;
        return currentProject.viewUnassignedTask(title);
    }

    public boolean addColumnToCurrentProject(String columnTitle){
        Column newColumn = new Column(columnTitle);
        if(currentProject.addColumn(newColumn)){
            return true;
        }
        return false;
    }

    public boolean setCurrentColumn(String columnName) {
        for(Column column: currentProject.viewColumns()) {
            if (column.getTitle().equals(columnName)) {
                currentColumn = column;
                return  true;
            }
        }
        return false;
    }

    public Column getCurrentColumn() {
        return  currentColumn;
    }

    public boolean addTaskToTaskList(Task task){
        if(column.addTask(task)){
            return true;
        }
        return false;
    }

}
