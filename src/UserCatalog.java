import java.util.ArrayList;
import java.util.UUID;

/*
 * @author  Cameron Reyes
 */

public class UserCatalog {
    private static UserCatalog userCatalog;
    private static ArrayList<User> userList = new ArrayList<User>();

    private UserCatalog(){}

    public static UserCatalog getInstance(){
        if (userCatalog == null) userCatalog = new UserCatalog();
        return userCatalog;
    }

    public static boolean loadUser(ArrayList<User> users){
        try{
            for (int i = 0; i <= users.size(); i++){
                if(getUserBool(users.get(i).getUUID())) continue;
                userList.add(users.get(i));
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static boolean addUser(User user){
        if(getUserBool(user.getUUID()) && checkUsername(user.getUsername())) return false;
        userList.add(user);
        return true;
    }

    public static boolean addUserToProject(UUID userID, UUID projectID){
        if (!getUserBool(userID)) return false;
        getUser(userID).addInvite(projectID); return true;
    }

    public static boolean removeUser(User mainUser, UUID toRemoveUser){
        if (!(mainUser.isAdmin()  && getUserBool(toRemoveUser))) return false;
        userList.remove(getUser(toRemoveUser));
        return true;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    private static boolean checkUsername(String username){
        try{
            for (int i = 0; i <userList.size(); i++){
                if(userList.get(i).checkUsername(username) == true) return true;
            }
            return false;
        }
        catch(Exception e){
            return true;
        }
    }

    private static boolean getUserBool(UUID userUUID){
        try{
            for (int i = 0; i <userList.size(); i++){
                if(userList.get(i).getUUID().equals(userUUID))
                return true;
            }
            return false;
        }
        catch(Exception e){
            return false;
        }

    }

    public static User retrieveUser(String username, String passsword){
        for (int i = 0; i <userList.size(); i++){
                if(userList.get(i).login(username, passsword)) return userList.get(i);
        }
        return null;
    }

    private static User getUser(UUID userUUID){
        try{
            for (int i = 0; i <userList.size(); i++){
                if(userList.get(i).getUUID().equals(userUUID))
                return userList.get(i);
            }
            return null;
        }
        catch(Exception e){
            return null;
        }

    }

}
