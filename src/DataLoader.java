import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Date;

/**
 * @author Aidan Godwin
 */
public class DataLoader extends DataConstants{
  public static ArrayList<User> getUsers() {
    ArrayList<User> userCatalog = new ArrayList<User>();

    try {
      FileReader reader = new FileReader(USER_FILE_NAME);
      JSONParser parser = new JSONParser();
      JSONArray userCatalogJSON = (JSONArray)parser.parse(reader);
      ArrayList<Project> currentProjects = new ArrayList<Project>();
      ArrayList<Project> invitedProjects = new ArrayList<Project>();
      for (int i=0; i<userCatalogJSON.size();i++) {
        JSONObject userJSON = (JSONObject)userCatalogJSON.get(i);
        UUID id = UUID.fromString((String)userJSON.get(USER_ID));
        String name = (String)userJSON.get(USER_NAME);
        String role = (String)userJSON.get(USER_ROLE);
        boolean adminPerms = (boolean)userJSON.get(USER_PERMS);
        String team = (String)userJSON.get(USER_TEAM);
        String userName = (String)userJSON.get(USER_TEAM);
        String password = (String)userJSON.get(USER_PASSWORD);
        String phone = (String)userJSON.get(USER_PHONE);
        double point = (Double)userJSON.get(USER_POINT);
        String email = (String)userJSON.get(USER_EMAIL);
        JSONArray currentProjectsJSON = (JSONArray)userJSON.get(USER_CURRENT_PROJECTS);
        for (Object o : currentProjectsJSON) {
          JSONObject currentProjectJSON = (JSONObject) o;
          Project currentProject = toProject(currentProjectJSON);
          currentProjects.add(currentProject);
        }

        userCatalog.add(new User(id, name, role, adminPerms, team, userName, password, phone, point, email, currentProjects, invitedProjects));
      }
      return userCatalog;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ArrayList<Project> getProjects() {
    ArrayList<Project> projects = new ArrayList<Project>();

    try {
      FileReader reader = new FileReader(PROJECT_FILE_NAME);
      JSONParser parser = new JSONParser();
      JSONArray projectCatalog = (JSONArray)parser.parse(reader);

      for (Object o : projectCatalog) {
        JSONObject projectJSON = (JSONObject) o;
        Project project = toProject(projectJSON);
        projects.add(project);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static User toUser(JSONObject userJSON) {

  }


  private static Project toProject(JSONObject projectJSON) {
    UUID projectId = UUID.fromString((String)projectJSON.get("projectId"));
    String name = (String)projectJSON.get("name");
    String type = (String)projectJSON.get("type");
    Layout layout = (Layout)projectJSON.get("layout");
    // Users
    ArrayList<User> users = new ArrayList<User>();
    JSONArray usersJSON = (JSONArray)projectJSON.get("users");
    for (Object u : usersJSON) {
      JSONObject userJSON = (JSONObject) u;
      User user = toUser(userJSON);
      users.add(user);
    }

    // Completed Tasks
    ArrayList<Task> completedTasks = new ArrayList<Task>();
    JSONArray completedTasksJSON = (JSONArray)projectJSON.get("completed-tasks");
    for (Object t : completedTasksJSON) {
      JSONObject taskJSON = (JSONObject)t;
      Task task = toTask(taskJSON);
      completedTasks.add(task);
    }

    // Ongoing Tasks
    ArrayList<Task> ongoingTasks = new ArrayList<Task>();
    JSONArray ongoingTasksJSON = (JSONArray)projectJSON.get("ongoing-tasks");
    for (Object t : ongoingTasksJSON) {
      JSONObject taskJSON = (JSONObject)t;
      Task task = toTask(taskJSON);
      ongoingTasks.add(task);
    }

    // Column List
    ArrayList<Column> columnList = new ArrayList<Column>();
    JSONArray columnListJSON = (JSONArray)projectJSON.get("columnList");
    for (Object c : columnListJSON) {
      JSONObject columnJSON = (JSONObject)c;
      UUID columnId = UUID.fromString((String)columnJSON.get("id"));
      String columnTitle = (String)columnJSON.get("title");
      boolean columnStatus = (boolean)columnJSON.get("status");
      
      // Date implementation ? 
      String columnCompletionTimeString = (String)columnJSON.get("completionTime");
      String columnCreatedTimeString = (String)columnJSON.get("createdTime");
      Date columnCompletionTime = new Date(columnCompletionTimeString);
      Date columnCreatedTime = new Date(columnCreatedTimeString);

      UUID authorId = UUID.fromString((String)columnJSON.get("author"));
      User author = UserCatalog.getInstance().getUser(authorId);
      // Column Tasks
      ArrayList<Task> columnTasks = new ArrayList<Task>();
      JSONArray columnTasksJSON = (JSONArray)columnJSON.get("tasks");
      for (Object t : columnTasksJSON) {
        JSONObject columnTaskJSON = (JSONObject)t;
        Task columnTask = toTask(columnTaskJSON);
        columnTasks.add(columnTask);
      }

      Column column = new Column(columnId, columnTitle, columnStatus, columnCompletionTime, columnCreatedTime, author, columnTasks);
      columnList.add(column);
    }

    // History -> List of Updates
    
    
  }

  private static Task toTask(JSONObject taskJSON) {

  }
}