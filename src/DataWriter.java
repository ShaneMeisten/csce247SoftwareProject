import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The Data Writer accesses the JSON Database files to write information from working memory into a saved file. 
 * @author Aidan Godwin
 */
public class DataWriter extends DataConstants{
  /**
   * Takes the information found within the User Catalog and transfers it to a JSON file. 
   */
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

  /**
   * Takes the information found within the Project Catalog and transfers it to a JSON file. 
   */
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

  /**
   * Creates a JSONObject based on the information of a given User object
   * @param user The User object whose information is to be used to make the JSONObject
   * @return A JSONObject representation of the given User
   */
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
    Map<UUID, Double> currentProjects = user.getCurrentProjects();
    for (Map.Entry<UUID,Double> entry : currentProjects.entrySet()) {
      JSONObject currentProjectJSON = new JSONObject();
      currentProjectJSON.put(PROJECT_ID, entry.getKey().toString());
      currentProjectJSON.put(USER_PROJECT_POINTS, entry.getValue());
      currentProjectsJSON.add(currentProjectJSON);
    }
    userDetails.put(USER_CURRENT_PROJECTS, currentProjectsJSON);

    // Invited Projects
    JSONArray invitedProjectsJSON = new JSONArray();
    ArrayList<UUID> invitedProjects = user.getInvitedProjects();
    for (UUID projectId : invitedProjects) {
      invitedProjectsJSON.add(projectId.toString());
    }
    userDetails.put(USER_INVITED_PROJECTS, invitedProjectsJSON);

    return userDetails;
  }

  /**
   * Creates a JSONObject based on the information of a given Project object
   * @param project The Project object whose information is to be used to make the JSONObject
   * @return A JSONObject representation of the given Project
   */
  private static JSONObject getProjectJSON(Project project) {
    JSONObject projectDetails = new JSONObject();
    projectDetails.put(PROJECT_ID,project.getUUID().toString());
    projectDetails.put(PROJECT_NAME,project.getName());
    projectDetails.put(PROJECT_TYPE,project.getType());
    projectDetails.put(PROJECT_LAYOUT,project.getLayout().name().toString());

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

    return projectDetails;
  }

  /**
   * Creates a JSONObject based on the information of a given Task object
   * @param task The Task object whose information is to be used to make the JSONObject
   * @return A JSONObject representation of the given Task
   */
  private static JSONObject getTaskJSON(Task task) {
    JSONObject taskDetails = new JSONObject();
    taskDetails.put(TASK_ID, task.getID().toString());
    taskDetails.put(TASK_TITLE, task.getTitle());
    taskDetails.put(TASK_DESCRIPTION, task.getDescription());
    taskDetails.put(TASK_WEIGHT, task.getWeight());
    taskDetails.put(TASK_DUE_DATE, task.getDueDate().toString());
    taskDetails.put(TASK_STATUS, task.getStatus());
    taskDetails.put(TASK_COMPLETION_TIME, task.getCompletionTime());
    taskDetails.put(TASK_AUTHOR, task.getAuthor().getUUID().toString());
    taskDetails.put(TASK_ASSIGNED_USER, task.getAssignedUser().getUUID().toString());

    // Categories
    JSONArray categoriesJSON = new JSONArray();
    ArrayList<String> categories = task.getCategories();
    for (String category : categories) {
      categoriesJSON.add(category);
    }
    taskDetails.put(TASK_CATEGORIES, categoriesJSON);

    // ToDoList
    JSONArray todoListJSON = new JSONArray();
    ArrayList<ToDo> todoList = task.getToDoList();
    for (ToDo todo : todoList) {
      JSONObject todoJSON = getToDoJSON(todo);
      todoListJSON.add(todoJSON);
    }
    taskDetails.put(TASK_TODO_LIST, todoListJSON);

    // Comments
    JSONArray commentsJSON = new JSONArray();
    ArrayList<Comment> comments = task.getCommentThread();
    for (Comment comment : comments) {
      JSONObject commentJSON = getCommentJSON(comment);
      commentsJSON.add(commentJSON);
    }
    taskDetails.put(TASK_COMMENT_THREAD, commentsJSON);

    return taskDetails;
  }

  /**
   * Creates a JSONObject based on the information of a given Column object
   * @param column The Column object whose information is to be used to make the JSONObject
   * @return A JSONObject representation of the given Column
   */
  private static JSONObject getColumnJSON(Column column) {
    JSONObject columnDetails = new JSONObject();
    columnDetails.put(COLUMN_ID, column.getUUID().toString());
    columnDetails.put(COLUMN_TITLE, column.getTitle());
    columnDetails.put(COLUMN_WEIGHT, column.getWeight());
    columnDetails.put(COLUMN_STATUS, column.getStatus());
    columnDetails.put(COLUMN_COMPLETION_TIME, column.getCompletionTime());
    columnDetails.put(COLUMN_CREATED_TIME, column.getCreatedTime().toString());
    columnDetails.put(COLUMN_AUTHOR, column.getAuthor().getUUID().toString());

    // Task List
    JSONArray tasksJSON = new JSONArray();
    ArrayList<Task> tasks = column.getTasks();
    for (Task task : tasks) {
      JSONObject taskJSON = getTaskJSON(task);
      tasksJSON.add(taskJSON);
    }
    columnDetails.put(COLUMN_TASKS, tasksJSON);

    return columnDetails;
  }

  /**
   * Creates a JSONObject based on the information of a given Update object
   * @param update The Update object whose information is to be used to make the JSONObject
   * @return A JSONObject representation of the given Update
   */
  private static JSONObject getUpdateJSON(Update update) {
    JSONObject updateDetails = new JSONObject();
    updateDetails.put(UPDATE_ID, update.getChangedID().toString()); 
    updateDetails.put(UPDATE_TIMESTAMP, update.getDate().toString());
    updateDetails.put(UPDATE_USER, update.getUserUUID().toString());
    updateDetails.put(UPDATE_CHANGE_LOG, update.getChangedLog());
    return updateDetails;
  }

  /**
   * Creates a JSONObject based on the information of a given Comment object
   * @param comment The Comment object whose information is to be used to make the JSONObject
   * @return A JSONObject representation of the given Comment
   */
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

  /**
   * Creates a JSONObject based on the information of a given ToDo object
   * @param todo The ToDo object whose information is to be used to make the JSONObject
   * @return A JSONObject representation of the given ToDo
   */
  private static JSONObject getToDoJSON(ToDo todo) {
    JSONObject todoDetails = new JSONObject();
    todoDetails.put(TODO_ID, todo.getID().toString());
    todoDetails.put(TODO_DONE, todo.getDone());
    todoDetails.put(TODO_DESCRIPTION, todo.getDescription());
    return todoDetails;
  }
}