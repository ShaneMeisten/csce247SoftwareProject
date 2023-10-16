import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

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
        ArrayList<UUID> currentProjects = (ArrayList<UUID>)userJSON.get(USER_CURRENT_PROJECTS);
        ArrayList<UUID> invitedProjects = (ArrayList<UUID>)userJSON.get(USER_INVITED_PROJECTS);

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

      for (int i=0;i<projectCatalog.size();i++) {
        JSONObject projectJSON = (JSONObject)projectCatalog.get(i);
        UUID projectId = UUID.fromString((String)projectJSON.get("projectId"));
        String name = (String)projectJSON.get("name");
        String type = (String)projectJSON.get("type");
        Layout layout = (String)projectJSON.get("layout");
        ArrayList<User> leaderboard = (ArrayList<User>)projectJSON.get("leaderboard");
        ArrayList<User> users = (ArrayList<User>)projectJSON.get("users");
        // TODO finish this
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}