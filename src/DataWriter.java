import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Aidan Godwin
 */
public class DataWriter extends DataConstants{
  public static void saveUsers() {
    UserCatalog userCatalog = UserCatalog.getInstance();
    ArrayList<User> users = userCatalog.getUsers();
    JSONArray jsonUsers = new JSONArray();

    // Creating all JSON objects
    for (int i=0;i<users.size();i++) 
      jsonUsers.add(getUserJSON(users.get(i)));

    // Write JSON
    try (FileWriter file = new FileWriter(USER_FILE_NAME)){
      file.write(jsonUsers.toJSONString());
      file.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void saveProjects() {
    ProjectCatalog projectCatalog = ProjectCatalog.getInstance();
    ArrayList<Project> projects = projectCatalog.getProjects();
    JSONArray jsonProjects = new JSONArray();

    // Creating all JSON Objects
    for (int i=0;i<projects.size();i++) {
      jsonProjects.add(getProjectJSON(projects.get(i)));
    }

    // Write JSON
    try (FileWriter file = new FileWriter(PROJECT_FILE_NAME)) {
      file.write(jsonProjects.toJSONString());
      file.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static JSONObject getUserJSON(User user) {
    JSONObject userDetails = new JSONObject();
    userDetails.put(USER_NAME, user.getName());
    userDetails.put(USER_ID, user.getUUID());
    userDetails.put(USER_ROLE, user.getRole());
    userDetails.put(USER_PERMS, user.getAdminPerms());
    userDetails.put(USER_TEAM, user.getTeam());
    userDetails.put(USER_USERNAME, user.getUsername());
    userDetails.put(USER_PASSWORD, user.getPassword());
    userDetails.put(USER_PHONE, user.getPhone());
    userDetails.put(USER_EMAIL, user.getEmail());
    userDetails.put(USER_CURRENT_PROJECTS, user.getCurrentProjects());
    userDetails.put(USER_INVITED_PROJECTS, user.getInvitedProjects());

    return userDetails;
  }

  private static JSONObject getProjectJSON(Project project) {
    JSONObject projectDetails = new JSONObject();
    projectDetails.put(PROJECT_ID,project.getUUID());
    projectDetails.put(PROJECT_NAME,project.getName());
    projectDetails.put(PROJECT_TYPE,project.getType());
    projectDetails.put(PROJECT_LAYOUT,project.getLayout());
    projectDetails.put(PROJECT_USERS,project.getUsers());
    projectDetails.put(PROJECT_COMPLETED_TASKS,project.getCompletedTasks());
    projectDetails.put(PROJECT_ONGOING_TASKS,project.getOngoingTasks());
    projectDetails.put(PROJECT_COLUMNS,project.getColumns());
    projectDetails.put(PROJECT_HISTORY,project.getHistory());
  }
}
