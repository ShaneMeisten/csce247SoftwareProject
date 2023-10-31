import java.util.ArrayList;
import java.util.HashMap;
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
        userCatalog.getUser(userID).addInvite(projectID); return true;
    }

    public static boolean removeUser(User mainUser, UUID toRemoveUser){
        if (!mainUser.isAdmin()  || !getUserBool(toRemoveUser)) return false;
        userList.remove(userCatalog.getUser(toRemoveUser));
        return true;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    private static boolean checkUsername(String username){
        try{
            for (int i = 0; i <= userList.size(); i++){
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
            for (int i = 0; i <= userList.size(); i++){
                if(userList.get(i).getUUID().compareTo(userUUID) == 0)
                return true;
            }
            return false;
        }
        catch(Exception e){
            return false;
        }

    }

    public static User retrieveUser(String username, String passsword){
        for (int i = 0; i < userList.size(); i++){
                if(userList.get(i).login(username, passsword)) return userList.get(i);
        }
        return null;
    }

    public User getUser(UUID userUUID){
        try{
            for (int i = 0; i < userList.size(); i++){
                if(userList.get(i).getUUID().equals(userUUID))
                return userList.get(i);
            }
            return null;
        }
        catch(Exception e){
            return null;
        }
    }

    private static ArrayList<User> sortArray(ArrayList<User> users, UUID projectUUID){
        if(users.size() == 0) return null;
        //Implement selection sort
        //Resourced from https://www.geeksforgeeks.org/selection-sort/
        for(int i = 0; i < users.size(); i++){
            int hold = i;
            for(int j = i + 1; j< users.size(); j++){
                if(users.get(j).getProjectPoints(projectUUID) < (users.get(hold).getProjectPoints(projectUUID))) 
                    hold = j;
            }
            User holdUser = users.get(hold);
            users.set(hold, users.get(i));
            users.set(i, holdUser);
        }
        return users;
    }

    public static ArrayList<User> getLeaderboard(UUID projectUUID){
        ArrayList<User> userProject = new ArrayList<User>();
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).containsProject(projectUUID)){
                userProject.add(userList.get(i));
            }
        }
        userProject = sortArray(userProject, projectUUID);
        return userProject;
    }

    public static ArrayList<User> getUsersInProjectUUID(UUID projectUUID){
        ArrayList<User> userProject = new ArrayList<User>();
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).containsProject(projectUUID)){
                userProject.add(userList.get(i));
            }
        }
        return userProject;
    }

}
