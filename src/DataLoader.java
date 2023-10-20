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
      for (int i=0; i<userCatalogJSON.size();i++) {
        JSONObject userJSON = (JSONObject)userCatalogJSON.get(i);
        User user = toUser(userJSON);
        userCatalog.add(user);
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
      return projects;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static User toUser(JSONObject userJSON) {
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

    // Current Projects
    ArrayList<UUID> currentProjects = new ArrayList<UUID>();
    JSONArray currentProjectsJSON = (JSONArray)userJSON.get(USER_CURRENT_PROJECTS);
    for (Object o : currentProjectsJSON) {
      JSONObject currentProjectJSON = (JSONObject)o;
      Project currentProject = toProject(currentProjectJSON);
      currentProjects.add(currentProject.getUUID());
    }

    // Invited Projects
    ArrayList<UUID> invitedProjects = new ArrayList<UUID>();
    JSONArray invitedProjectsJSON = (JSONArray)userJSON.get(USER_INVITED_PROJECTS);
    for (Object o : invitedProjectsJSON) {
      JSONObject invitedProjectJSON = (JSONObject)o;
      Project invitedProject = toProject(invitedProjectJSON);
      invitedProjects.add(invitedProject.getUUID());
    }
    
    User user = new User(id, name, role, adminPerms, team, userName, password, phone, point, email, currentProjects, invitedProjects);
    return user;
  }


  private static Project toProject(JSONObject projectJSON) {
    UUID projectId = UUID.fromString((String)projectJSON.get(PROJECT_ID));
    String name = (String)projectJSON.get(PROJECT_NAME);
    String type = (String)projectJSON.get(PROJECT_TYPE);
    Layout layout = (Layout)projectJSON.get(PROJECT_LAYOUT);
    // Users
    ArrayList<User> users = new ArrayList<User>();
    JSONArray usersJSON = (JSONArray)projectJSON.get(PROJECT_USERS);
    for (Object u : usersJSON) {
      JSONObject userJSON = (JSONObject) u;
      User user = toUser(userJSON);
      users.add(user);
    }

    // Completed Tasks
    ArrayList<Task> completedTasks = new ArrayList<Task>();
    JSONArray completedTasksJSON = (JSONArray)projectJSON.get(PROJECT_COMPLETED_TASKS);
    for (Object t : completedTasksJSON) {
      JSONObject taskJSON = (JSONObject)t;
      Task task = toTask(taskJSON);
      completedTasks.add(task);
    }

    // Ongoing Tasks
    ArrayList<Task> ongoingTasks = new ArrayList<Task>();
    JSONArray ongoingTasksJSON = (JSONArray)projectJSON.get(PROJECT_ONGOING_TASKS);
    for (Object t : ongoingTasksJSON) {
      JSONObject taskJSON = (JSONObject)t;
      Task task = toTask(taskJSON);
      ongoingTasks.add(task);
    }

    // Column List
    ArrayList<Column> columnList = new ArrayList<Column>();
    JSONArray columnListJSON = (JSONArray)projectJSON.get(PROJECT_COLUMNS);
    for (Object c : columnListJSON) {
      JSONObject columnJSON = (JSONObject)c;
      UUID columnId = UUID.fromString((String)columnJSON.get(COLUMN_ID));
      String columnTitle = (String)columnJSON.get(COLUMN_TITLE);
      boolean columnStatus = (boolean)columnJSON.get(COLUMN_STATUS);
      
      // Date implementation ? 
      String columnCompletionTimeString = (String)columnJSON.get(COLUMN_COMPLETION_TIME);
      String columnCreatedTimeString = (String)columnJSON.get(COLUMN_CREATED_TIME);
      Date columnCompletionTime = new Date(columnCompletionTimeString);
      Date columnCreatedTime = new Date(columnCreatedTimeString);

      UUID authorId = UUID.fromString((String)columnJSON.get(COLUMN_AUTHOR));
      User author = UserCatalog.getInstance().getUser(authorId);
      // Column Tasks
      ArrayList<Task> columnTasks = new ArrayList<Task>();
      JSONArray columnTasksJSON = (JSONArray)columnJSON.get(COLUMN_TASKS);
      for (Object t : columnTasksJSON) {
        JSONObject columnTaskJSON = (JSONObject)t;
        Task columnTask = toTask(columnTaskJSON);
        columnTasks.add(columnTask);
      }

      Column column = new Column(columnId, columnTitle, columnStatus, columnCompletionTime, columnCreatedTime, author, columnTasks);
      columnList.add(column);
    }

    /* 
    // History -> List of Updates
    JSONObject historyJSON = (JSONObject)projectJSON.get(PROJECT_HISTORY);
    UUID historyId = UUID.fromString((String)historyJSON.get(HISTORY_ID));
    String timeStampString = (String)historyJSON.get(HISTORY_TIMESTAMP);
    Date timeStamp = new Date(timeStampString);
    UUID historyUserId = UUID.fromString((String)historyJSON.get(HISTORY_USER));
    String changelog = (String)historyJSON.get(HISTORY_CHANGE_LOG);
    Update update = new Update(historyId, timeStamp, historyUserId, changelog);
    ArrayList<Update> updates = new ArrayList<Update>();
  */

    ArrayList<Update> updates = new ArrayList<Update>();
    JSONArray historyJSON = (JSONArray)projectJSON.get(PROJECT_HISTORY);
    for (Object t : historyJSON) {
      JSONObject historyJSON_2 = (JSONObject)t;
      Update update = toUpdate(historyJSON_2);
      updates.add(update);
      
    }

    History history = new History();
    history.loadHistory(updates);
    
    Project project = new Project(projectId, name, type, layout, users, completedTasks, ongoingTasks, columnList, history);
    return project;
  }

  private static Update toUpdate(JSONObject historyJSON){
    UUID historyId = UUID.fromString((String)historyJSON.get(HISTORY_ID));
    String timeStampString = (String)historyJSON.get(HISTORY_TIMESTAMP);
    Date timeStamp = new Date(timeStampString);
    UUID historyUserId = UUID.fromString((String)historyJSON.get(HISTORY_USER));
    String changelog = (String)historyJSON.get(HISTORY_CHANGE_LOG);
    Update update = new Update(historyId, timeStamp, historyUserId, changelog);
    return update;
  }

  private static Task toTask(JSONObject taskJSON) {
    UUID taskId = UUID.fromString((String)taskJSON.get(TASK_ID));
    String taskTitle = (String)taskJSON.get(TASK_TITLE);
    String taskDescription = (String)taskJSON.get(TASK_DESCRIPTION);
    double taskWeight = Double.parseDouble((String)taskJSON.get(TASK_WEIGHT));
    String taskDueDateString = (String)taskJSON.get(TASK_DUE_DATE);
    Date taskDueDate = new Date(taskDueDateString);
    boolean taskStatus = (boolean)taskJSON.get(TASK_STATUS);
    String completionTimeString = (String)taskJSON.get(TASK_COMPLETION_TIME);
    Date completionTime = new Date(completionTimeString);
    UUID authorId = UUID.fromString((String)taskJSON.get(TASK_AUTHOR));
    User author = UserCatalog.getInstance().getUser(authorId);
    UUID assignedUserId = UUID.fromString((String)taskJSON.get(TASK_ASSIGNED_USER));
    User assignedUser = UserCatalog.getInstance().getUser(assignedUserId);

    // Categories
    ArrayList<String> categories = new ArrayList<String>();
    JSONArray categoriesJSON = (JSONArray)taskJSON.get(TASK_CATEGORIES);
    for (Object o : categoriesJSON) {
      String category = (String)o;
      categories.add(category);
    }

    // ToDo List
    ArrayList<ToDo> todoList = new ArrayList<ToDo>();
    JSONArray todoListJSON = (JSONArray)taskJSON.get(TASK_TODO_LIST);
    for (Object o : todoListJSON) {
      JSONObject todoJSON = (JSONObject)o;
      ToDo todo = toToDo(todoJSON);
      todoList.add(todo);
    }

    // Comment Thread
    ArrayList<Comment> commentThread = new ArrayList<Comment>();
    JSONArray commentThreadJSON = (JSONArray)taskJSON.get(TASK_COMMENT_THREAD);
    for (Object o : commentThreadJSON) {
      JSONObject commentJSON = (JSONObject)o;
      Comment comment = toComment(commentJSON);
      commentThread.add(comment);
    }

    Task task = new Task(taskId, taskTitle, taskDescription, taskDueDate, taskWeight, categories, commentThread, todoList, taskStatus, completionTime, assignedUser, author);
    return task;
  }

  private static ToDo toToDo(JSONObject todoJSON) {
    
  }

  private static Comment toComment(JSONObject commentJSON) {

  }
}
