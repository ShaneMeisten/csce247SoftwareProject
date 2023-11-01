import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Facade {
    private ProjectCatalog projectCatalog;
    private UserCatalog userCatalog;
    private Project currentProject;
    private User currentUser;
    private Column column;

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

    public Facade() {
        projectCatalog = ProjectCatalog.getInstance();
        userCatalog = UserCatalog.getInstance();
    }

    public void createProject(String name) {
        projectCatalog.addProject(name);
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
        return projectCatalog.readUserProjectUUID(currentUser.getCurrentProjects());
    }

    public ArrayList<Project> getUserInvitedProjects() {
        if (currentUser == null) return null;
        if (currentUser.getInvitedProjects().size() == 0) return null;
        return projectCatalog.readUserProjectUUID(currentUser.getInvitedProjects());
    }

    public boolean AcceptInvite(int invite) {
        if (currentUser == null) return false;
        currentUser.AcceptInvite(invite);
        return true;
    }
    /* 
    public void createProject(Project project){  
        projectCatalog.addProject(project.getName(), project.getType());
      
    }
    */

    public boolean RemoveProject(int invite) {
        if (currentUser == null) return false;
        currentUser.RemoveProject(invite);
        return true;
    }

    public boolean setCurrentProject(int projectNumber) { 
        if(currentUser == null) return false;
        if(projectNumber >= 0 && projectNumber <= currentUser.getCurrentProjects().size()){
            currentProject  = projectCatalog.getProject(currentUser.getCurrentProjects().get(projectNumber));
            return true;
        }
        return false;
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
        return userCatalog.getUsersInProjectUUID(currentProject.getUUID());
    }

    public boolean removeUserFromCurrentProject (int user) {
        if(currentUser == null || currentProject == null) return false;
        if(user < 0 || user >= seeUsersInCurrentProject().size()) return false;
        User toRemove = seeUsersInCurrentProject().get(user);
        String changelog = currentUser.getUsername() + "has removed " + toRemove.getUsername()
        + "[" + toRemove.getUUID() + "] from the project: " + currentProject.getName();
        currentProject.addHistory(currentUser.getUUID(), currentProject.getUUID(), changelog);
        return userCatalog.removeUser(currentUser, toRemove.getUUID());
    }

    public ArrayList<User> getLeaderBoard() {
        if(currentProject == null) return null;
        return userCatalog.getLeaderboard(currentProject.getUUID());
    }

    //Either user has pending invite or not invited
    public boolean addUserToCurrentProject(int user) {
        if(currentProject == null || currentUser == null) return false;
        if(user < 0 || user >= userCatalog.getUsers().size()) return false;
        return userCatalog.inviteUserToProject(userCatalog.getUsers().get(user).getUUID(), currentProject.getUUID());
    }

    public void InviteUserToProject(UUID user, UUID project) {
        userCatalog.inviteUserToProject(user, project);
    }

    public boolean RemovePointsFromUserInCurrentProject(int user, int points) {
        if(currentUser.isAdmin() == false || currentProject == null) return false;
        if(user < 0 || user >= seeUsersInCurrentProject().size()) return false;
        return seeUsersInCurrentProject().get(user).removePoints(currentProject.getUUID(), points);
    }

    public boolean AddPointsToUserInCurrentProject(int user, int points) {
        if(currentUser.isAdmin() == false || currentProject == null) return false;
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
        if(currentProject.addColumn(columnTitle)){
            return true;
        }
        return false;
    }

    public boolean addTaskToTaskList(Task task){
        if(column.addTask(task)){
            return true;
        }
        return false;
    }
}
