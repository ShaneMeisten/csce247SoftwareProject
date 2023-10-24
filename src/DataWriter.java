import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
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
    userDetails.put(USER_ID, user.getUUID().toString());
    userDetails.put(USER_ROLE, user.getRole());
    userDetails.put(USER_PERMS, user.getAdminPerms());
    userDetails.put(USER_TEAM, user.getTeam());
    userDetails.put(USER_USERNAME, user.getUsername());
    userDetails.put(USER_PASSWORD, user.getPassword());
    userDetails.put(USER_PHONE, user.getPhone());
    userDetails.put(USER_EMAIL, user.getEmail());

    // Current Projects
    JSONArray currentProjectsJSON = new JSONArray();
    ArrayList<UUID> currentProjects = user.getCurrentProjects();
    for (UUID projectId : currentProjects) {
      Project project = ProjectCatalog.getInstance().getProject(projectId);
      JSONObject projectJSON = getProjectJSON(project);
      currentProjectsJSON.add(projectJSON);
    }
    userDetails.put(USER_CURRENT_PROJECTS, currentProjectsJSON);

    // Invited Projects
    JSONArray invitedProjectsJSON = new JSONArray();
    ArrayList<UUID> invitedProjects = user.getInvitedProjects();
    for (UUID projectId : invitedProjects) {
      Project project = ProjectCatalog.getInstance().getProject(projectId);
      JSONObject projectJSON = getProjectJSON(project);
      invitedProjectsJSON.add(projectJSON);
    }
    userDetails.put(USER_INVITED_PROJECTS, invitedProjectsJSON);

    return userDetails;
  }

  private static JSONObject getProjectJSON(Project project) {
    JSONObject projectDetails = new JSONObject();
    projectDetails.put(PROJECT_ID,project.getUUID());
    projectDetails.put(PROJECT_NAME,project.getName());
    projectDetails.put(PROJECT_TYPE,project.getType());
    projectDetails.put(PROJECT_LAYOUT,project.getLayout());

    // Completed Tasks
    JSONArray completedTasksJSON = new JSONArray();
    ArrayList<Task> completedTasks = project.getCompletedTasks();
    for (Task task : completedTasks) {
      JSONObject taskJSON = getTaskJSON(task);
      completedTasksJSON.add(taskJSON);
    }
    projectDetails.put(PROJECT_COMPLETED_TASKS, completedTasksJSON);

    // Ongoing Tasks
    JSONArray ongoingTasksJSON = new JSONArray();
    ArrayList<Task> ongoingTasks = project.getOngoingTasks();
    for (Task task : ongoingTasks) {
      JSONObject taskJSON = getTaskJSON(task);
      ongoingTasksJSON.add(taskJSON);
    }
    projectDetails.put(PROJECT_ONGOING_TASKS, ongoingTasksJSON);

    // Columns
    JSONArray columnListJSON = new JSONArray();
    ArrayList<Column> columnList = project.getColumnList();
    for (Column column : columnList) {
      JSONObject columnJSON = getColumnJSON(column);
      columnListJSON.add(columnJSON);
    }
    projectDetails.put(PROJECT_COLUMNS, columnListJSON);

    // History
    JSONArray historyJSON = new JSONArray();
    History history = project.getHistory();
    for (Update update : history.getHistoryList()) {
      JSONObject updateJSON = getUpdateJSON(update);
      historyJSON.add(updateJSON);
    }
    projectDetails.put(PROJECT_HISTORY, historyJSON);
  }

  private static JSONObject getTaskJSON(Task task) {
    // TODO
  }

  private static JSONObject getColumnJSON(Column column) {
    // TODO
  }

  private static JSONObject getUpdateJSON(Update update) {
    JSONObject updateDetails = new JSONObject();
    updateDetails.put(UPDATE_ID, update.getChangedID().toString()); 
    updateDetails.put(UPDATE_TIMESTAMP, update.getDate().toString());
    updateDetails.put(UPDATE_USER, update.getUserUUID().toString());
    updateDetails.put(UPDATE_CHANGE_LOG, update.getChangedLog());
    return updateDetails;
  }

  private static JSONObject getCommentJSON(Comment comment) {
    JSONObject commentDetails = new JSONObject();
    commentDetails.put(COMMENT_ID, comment.getId().toString());
    commentDetails.put(COMMENT_NAME, comment.getName());
    commentDetails.put(COMMENT_DESCRIPTION, comment.getDescription());
    commentDetails.put(COMMENT_AUTHOR, comment.getAuthorUUID().toString());
    commentDetails.put(COMMENT_DATE, comment.getDate().toString());
    
    // Replies
    JSONArray repliesJSON = new JSONArray();
    ArrayList<Comment> replies = comment.getReplies();
    if (replies.isEmpty())
      commentDetails.put(COMMENT_REPLY, repliesJSON);
    else {
      for (Comment reply : replies) {
        JSONObject replyJSON = getCommentJSON(reply);
        repliesJSON.add(replyJSON);
      }
      commentDetails.put(COMMENT_REPLY, repliesJSON);
    }

    return commentDetails;
  }

  private static JSONObject getToDoJSON(ToDo todo) {
    JSONObject todoDetails = new JSONObject();
    todoDetails.put(TODO_ID, todo.getID().toString());
    todoDetails.put(TODO_DONE, todo.getDone());
    todoDetails.put(TODO_DESCRIPTION, todo.getDescription());
    return todoDetails;
  }
}
