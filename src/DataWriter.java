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

  public static JSONObject getUserJSON(User user) {
    JSONObject userDetails = new JSONObject();
    userDetails.put(USER_NAME, user.getName());
    userDetails.put(USER_ID, user.getUUID());
    userDetails.put(USER_ROLE, user.getRole());
    userDetails.put(USER_ROLE, user.getRole());
    userDetails.put(USER_PERMS, user.getAdminPerms());
    userDetails.put(USER_USERNAME, user.getUsername());
    userDetails.put(USER_PASSWORD, user.getPassword());
    userDetails.put(USER_PHONE, user.getPhone());
    userDetails.put(USER_POINT, user.getPoints());
    userDetails.put(USER_EMAIL, user.getEmail());
    userDetails.put(USER_CURRENT_PROJECTS, user.getCurrentProjects());
    userDetails.put(USER_INVITED_PROJECTS, user.getInvitedProjects());

    return userDetails;
  }
}
