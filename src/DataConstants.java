
/**
 * @author Aidan Godwin
 */
public abstract class DataConstants {
  protected static final String USER_FILE_NAME = "Json/user.json";
  protected static final String USER_NAME = "name";
  protected static final String USER_ID = "id";
  protected static final String USER_ROLE = "role";
  protected static final String USER_PERMS = "adminPerms";
  protected static final String USER_TEAM = "team";
  protected static final String USER_USERNAME = "userName";
  protected static final String USER_PASSWORD = "password";
  protected static final String USER_PHONE = "phone";
  protected static final String USER_POINT = "point";
  protected static final String USER_EMAIL = "email";
  protected static final String USER_CURRENT_PROJECTS = "currentProjects";
  protected static final String USER_INVITED_PROJECTS = "invitedProjects";

  protected static final String PROJECT_FILE_NAME = "Json/project.json";
  protected static final String PROJECT_ID = "projectId";
  protected static final String PROJECT_NAME = "name";
  protected static final String PROJECT_TYPE = "type";
  protected static final String PROJECT_LAYOUT = "layout";
  protected static final String PROJECT_USERS = "users";
  protected static final String PROJECT_COMPLETED_TASKS = "completed-tasks";
  protected static final String PROJECT_ONGOING_TASKS = "ongoing-tasks";
  protected static final String PROJECT_COLUMNS = "columnList";
  protected static final String PROJECT_HISTORY = "history";

  protected static final String COLUMN_ID = "id";
  protected static final String COLUMN_TITLE = "title";
  protected static final String COLUMN_WEIGHT = "weight";
  protected static final String COLUMN_STATUS = "status";
  protected static final String COLUMN_COMPLETION_TIME = "completionTime";
  protected static final String COLUMN_CREATED_TIME = "createdTime";
  protected static final String COLUMN_AUTHOR = "author";
  protected static final String COLUMN_TASKS = "tasks";

  protected static final String TASK_ID = "id";
  protected static final String TASK_TITLE = "title";
  protected static final String TASK_DESCRIPTION = "description";
  protected static final String TASK_DUE_DATE = "dueDate";
  protected static final String TASK_WEIGHT = "weight";
  protected static final String TASK_CATEGORIES = "categories";
  protected static final String TASK_COMMENT_THREAD = "commentThread";
  protected static final String TASK_STATUS = "status";
  protected static final String TASK_COMPLETION_TIME = "completionTime";
  protected static final String TASK_ASSIGNED_USER = "assignedUser";
  protected static final String TASK_AUTHOR = "author";
  protected static final String TASK_TODO_LIST = "toDoList";

  protected static final String COMMENT_ID = "id";
  protected static final String COMMENT_NAME = "name";
  protected static final String COMMENT_DESCRIPTION = "description";
  protected static final String COMMENT_AUTHOR = "author";
  protected static final String COMMENT_DATE = "date";
  protected static final String COMMENT_REPLY = "reply";
  
  protected static final String TODOLIST_ID = "id";

  protected static final String TODO_DONE = "done";
  protected static final String TODO_DESCRIPTION = "description";

  protected static final String HISTORY_ID = "id";
  protected static final String HISTORY_TIMESTAMP = "timeStamp";
  protected static final String HISTORY_USER = "user";
  protected static final String HISTORY_CHANGE_LOG = "changelog";
}
