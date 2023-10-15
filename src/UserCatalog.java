import java.util.ArrayList;
import java.util.UUID;

/*
 * @author  Cameron Reyes
 */

public class UserCatalog {
    private static UserCatalog userCatalog;
    private static ArrayList<User> userList;

    private UserCatalog(){}

    public static UserCatalog getInstance(){
        if (userCatalog == null) userCatalog = new UserCatalog();
        return userCatalog;
    }

    public static boolean addUser(User user){
        if(!userList.contains(user)){
            userList.add(user);
            return true;
        }
        return false;
    }

    public static boolean removeUser(User mainUser, User toRemoveUser){
        if (!(mainUser.isAdmin()  && userList.contains(toRemoveUser))) return false;
        userList.remove(toRemoveUser);
        return true;
    }

    public static User getUser(UUID userUUID){
        try{
            return null;
        }
        catch(Exception e){
            return null;
        }

    }

    public ArrayList<User> getUsers() {
        return userList;
    }

}
