import java.util.ArrayList;

public class Facade {
    private ProjectCatalog projectCatalog;
    private UserCatalog userCatalog;
    private Project currentProject;
    private User currentUser;

    public Facade() {
        projectCatalog.getInstance();
        userCatalog.getInstance();
    }

    public boolean login(String username, String password) {
        currentUser = userCatalog.retrieveUser(username, password);
        if(currentUser != null) return true;
        return false;
    }

    public ArrayList<String> getUserCurrentProjects() {
        if (currentUser == null) return null;
        return projectCatalog.readUserProjectUUID(currentUser.getCurrentProjects());
    }

    public ArrayList<String> getUserInvitedProjects() {
        if (currentUser == null) return null;
        return projectCatalog.readUserProjectUUID(currentUser.getInvitedProjects());
    }

    public ArrayList<User> getLeaderBoard() {
        if(currentProject == null) return null;
        return userCatalog.getLeaderboard(currentProject.getUUID());
    }
}
