package Code;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * The Data Loader accesses the JSON Database files to read and load this saved information into working memory
 * @author Aidan Godwin
 */
public class DataLoader extends DataConstants{
  public static UserCatalog userCatalog = UserCatalog.getInstance();

  /**
   * Parses through the user.json file to create User objects based on the information within the file. Adds these Users to a new catalog. 
   * @return ArrayList of the Users located in the user.json databse file. 
   */
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

  /**
   * Parses through the project.json file to create Project objects based on the information within the file. Adds these Projects to a new catalog. 
   * @return ArrayList of the Projects located in the project.json databse file. 
   */
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

  /**
   * Creates a User object based on the information collected from a given JSONObject of a user. 
   * @param userJSON JSONObject holding the information of a given User
   * @return A User Object based on the information collected from the userJSON JSONObject. 
   */
  private static User toUser(JSONObject userJSON) {
    UUID id = UUID.fromString((String)userJSON.get(USER_ID));
    String name = (String)userJSON.get(USER_NAME);
    String role = (String)userJSON.get(USER_ROLE);
    boolean adminPerms = (boolean)userJSON.get(USER_PERMS);
    String team = (String)userJSON.get(USER_TEAM);
    String userName = (String)userJSON.get(USER_TEAM);
    String password = (String)userJSON.get(USER_PASSWORD);
    String phone = (String)userJSON.get(USER_PHONE);
    String email = (String)userJSON.get(USER_EMAIL);

    // Current Projects
    HashMap<UUID,Double> currentProjects = new HashMap<UUID,Double>();
    JSONArray currentProjectsJSON = (JSONArray)userJSON.get(USER_CURRENT_PROJECTS);
    for (Object o : currentProjectsJSON) {
      JSONObject currentProjectJSON = (JSONObject)o;
      UUID projectId = UUID.fromString((String)currentProjectJSON.get(PROJECT_ID));
      double projectPoints = Double.parseDouble(currentProjectJSON.get(USER_PROJECT_POINTS).toString());
      currentProjects.put(projectId, projectPoints);
    }

    // Invited Projects
    ArrayList<UUID> invitedProjects = new ArrayList<UUID>();
    JSONArray invitedProjectsJSON = (JSONArray)userJSON.get(USER_INVITED_PROJECTS);
    for (Object o : invitedProjectsJSON) {
      UUID projectId = UUID.fromString(o.toString());
      invitedProjects.add(projectId);
    }
    
    User user = new User(id, name, role, adminPerms, team, userName, password, phone, email, currentProjects, invitedProjects);
    return user;
  }

  /**
   * Creates a Project object based on the information collected from a given JSONObject of a project. 
   * @param projectJSON JSONObject holding the information of a given Project
   * @return A Project Object based on the information collected from the projectJSON JSONObject. 
   */
  private static Project toProject(JSONObject projectJSON) {
    UUID projectId = UUID.fromString((String)projectJSON.get(PROJECT_ID));
    String name = (String)projectJSON.get(PROJECT_NAME);
    String type = (String)projectJSON.get(PROJECT_TYPE);
    Layout layout = Enum.valueOf(Layout.class, (String)projectJSON.get(PROJECT_LAYOUT));

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
      Column column = toColumn(columnJSON);
      columnList.add(column);
    }

    // History
    ArrayList<Update> updates = new ArrayList<Update>();
    JSONArray historyJSON = (JSONArray)projectJSON.get(PROJECT_HISTORY);
    for (Object t : historyJSON) {
      JSONObject updateJSON = (JSONObject)t;
      Update update = toUpdate(updateJSON);
      updates.add(update);
      
    }

    History history = new History();
    history.loadHistory(updates);
    
    Project project = new Project(projectId, name, type, layout, completedTasks, ongoingTasks, columnList, history);
    return project;
  }

  /**
   * Takes one piece of the historyJSON and converts its information into an Update object. 
   * @param historyJSON A JSONObject holding the information to be placed into the new Update object
   * @return The created Update object based on the given information. 
   */
  private static Update toUpdate(JSONObject historyJSON){
    UUID historyId = UUID.fromString((String)historyJSON.get(UPDATE_ID));
    String timeStampString = (String)historyJSON.get(UPDATE_TIMESTAMP);
    Date timeStamp = new Date(timeStampString);
    UUID historyUserId = UUID.fromString((String)historyJSON.get(UPDATE_USER));
    String changelog = (String)historyJSON.get(UPDATE_CHANGE_LOG);
    Update update = new Update(historyId, timeStamp, historyUserId, changelog);
    return update;
  }

  /**
   * Creates a Task object based on the information from the given JSONObject
   * @param taskJSON the given JSONObject holding the information of the Task Object
   * @return a new Task object based on the information from the given JSONObject
   */
  private static Task toTask(JSONObject taskJSON) {
    UUID taskId = UUID.fromString((String)taskJSON.get(TASK_ID));
    String taskTitle = (String)taskJSON.get(TASK_TITLE);
    String taskDescription = (String)taskJSON.get(TASK_DESCRIPTION);
    double taskWeight = Double.parseDouble(taskJSON.get(TASK_WEIGHT).toString());
    String taskDueDateString = (String)taskJSON.get(TASK_DUE_DATE);
    Date taskDueDate = new Date(taskDueDateString);
    boolean taskStatus = (boolean)taskJSON.get(TASK_STATUS);
    double completionTime = Double.parseDouble(taskJSON.get(TASK_COMPLETION_TIME).toString());
    UUID authorId = UUID.fromString((String)taskJSON.get(TASK_AUTHOR));
    User author = userCatalog.getUser(authorId);
    UUID assignedUserId = UUID.fromString((String)taskJSON.get(TASK_ASSIGNED_USER));
    User assignedUser = userCatalog.getUser(assignedUserId);

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

  /**
   * Creates a Column object based on the information from the given JSONObject
   * @param columnJSON the given JSONObject holding the information of the Column Object
   * @return a new Column object based on the information from the given JSONObject
   */
  private static Column toColumn(JSONObject columnJSON) {
    UUID columnId = UUID.fromString((String)columnJSON.get(COLUMN_ID));
    String columnTitle = (String)columnJSON.get(COLUMN_TITLE);
    double columnWeight = Double.parseDouble(columnJSON.get(COLUMN_WEIGHT).toString());
    boolean columnStatus = (boolean)columnJSON.get(COLUMN_STATUS);
      
    // Date implementation
    double columnCompletionTime = Double.parseDouble(columnJSON.get(COLUMN_COMPLETION_TIME).toString());
    String columnCreatedTimeString = (String)columnJSON.get(COLUMN_CREATED_TIME);
    Date columnCreatedTime = new Date(columnCreatedTimeString);

    UUID authorId = UUID.fromString((String)columnJSON.get(COLUMN_AUTHOR));
    User author = userCatalog.getUser(authorId);
      
    // Column Tasks
    ArrayList<Task> columnTasks = new ArrayList<Task>();
    JSONArray columnTasksJSON = (JSONArray)columnJSON.get(COLUMN_TASKS);
    for (Object t : columnTasksJSON) {
      JSONObject columnTaskJSON = (JSONObject)t;
      Task columnTask = toTask(columnTaskJSON);
      columnTasks.add(columnTask);
    }

    Column column = new Column(columnId, columnTitle, columnWeight, columnStatus, columnCompletionTime, columnCreatedTime, author, columnTasks);
    return column;
  }

  /**
   * Creates a ToDo object based on the information from the given JSONObject
   * @param todoJSON the given JSONObject holding the information of the ToDo Object
   * @return a new ToDo object based on the information from the given JSONObject
   */
  private static ToDo toToDo(JSONObject todoJSON) {
    UUID todoId = UUID.fromString((String)todoJSON.get(TODO_ID));
    boolean done = (boolean)todoJSON.get(TODO_DONE);
    String description = (String)todoJSON.get(TODO_DESCRIPTION);
    ToDo todo = new ToDo(done, description,todoId);
    return todo;
  }

  /**
   * Creates a Comment object based on the information from the given JSONObject
   * @param commentJSON the given JSONObject holding the information of the Comment Object
   * @return a new Comment object based on the information from the given JSONObject
   */
  private static Comment toComment(JSONObject commentJSON) {
    UUID commentId = UUID.fromString((String)commentJSON.get(COMMENT_ID));
    String name = (String)commentJSON.get(COMMENT_NAME);
    String description = (String)commentJSON.get(COMMENT_DESCRIPTION);
    UUID author = UUID.fromString((String)commentJSON.get(COMMENT_AUTHOR));
    String dateString = (String)commentJSON.get(COMMENT_DATE);
    Date date = new Date(dateString);

    ArrayList<Comment> replies = new ArrayList<Comment>();
    JSONArray repliesJSON = (JSONArray)commentJSON.get(COMMENT_REPLY);
    if (!repliesJSON.isEmpty()) {
      replies = new ArrayList<Comment>();
      for (Object o : repliesJSON) {
        JSONObject replyJSON = (JSONObject)o;
        Comment reply = toComment(replyJSON);
        replies.add(reply);
      }
    }
    Comment comment = new Comment(commentId, name, description, author, date, replies);
    return comment;
  }
}
