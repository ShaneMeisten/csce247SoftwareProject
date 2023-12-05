package Code;
<<<<<<< HEAD:src/Code/UserCatalog.java
=======

>>>>>>> c0f85a63d929d6468f00aa15753f2334bfad43ba:src/main/Java/Code/UserCatalog.java
import java.util.ArrayList;
import java.util.UUID;

/*
 * Singleton to hold the Catalog of Users
 * 
 * @author  Cameron Reyes
 */

public class UserCatalog {
    private static UserCatalog userCatalog;
    private static ArrayList<User> userList = new ArrayList<User>();

    /*
     * UserCatalog constructor for Singleton
     */
    private UserCatalog(){}

    /*
     * Create's a new instance for UserCatalog
     * 
     * Singleton method
     * 
     * @return UserCatalog      Returns the Initialized user catalog
     */
    public static UserCatalog getInstance(){
        if (userCatalog == null) userCatalog = new UserCatalog();
        return userCatalog;
    }

    /*
     * Loads a list of users to the catalog
     * 
     * Does not add duplicate users to the user list.
     * Used within dataloader
     * 
     * @param users     An arraylist of users to add
     * @return boolean  If the User experiences issues it returns false
     */

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

    /*
     * Adds a new user to user list
     * 
     * User cannot have the same usernmae or UUID as another user in the list
     * 
     * @param user      The user to be added to the user list
     * @return boolean  True if user is succuessfully added to the list
     * 
     */

    public static boolean addUser(User user){
        if(getUserBool(user.getUUID()) && checkUsername(user.getUsername())) return false;
        userList.add(user);
        return true;
    }

    /*
     * Invites a user to a project
     * 
     * Does not add projectID, if user already has an invites
     * Does not add to a user that does not exist
     * 
     * @param userID        The UUID of the user to add project to
     * @param projectID     The proejct UUID to add to user invite list
     * @return boolean      True if the user adds new invite to project
     */

    public static boolean inviteUserToProject(UUID userID, UUID projectID){
        if (!getUserBool(userID)) return false;
        userCatalog.getUser(userID).addInvite(projectID); return true;
    }

    /*
     * Removes a user from the user list
     * 
     * User must be an admin and the "to be removed user" must exist
     * 
     * @param mainUser      The user who will remove the toRemoveUser
     * @param toRemoveUser  The user who will be removed from the userList
     * @return boolean      True if user successfully removed
     */

    public static boolean removeUser(User mainUser, UUID toRemoveUser){
        if (!mainUser.getAdminPerms()  || !getUserBool(toRemoveUser)) return false;
        userList.remove(userCatalog.getUser(toRemoveUser));
        return true;
    }

    /*
     * Returns the Users in the userlist
     * 
     * @return userList     The users within the Arraylist of Users
     */

    public ArrayList<User> getUsers() {
        return userList;
    }

    /*
     * Helper method too ensure no duplicate usernames
     * 
     * used within the add users method
     * 
     * @param username      The username to be checked
     * @reutrn boolean      True if user list contains user
     */

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

    /*
     * Helper method to check duplciates for user UUID
     * 
     * @param userUUID      The UUID to check if duplicate
     * @return boolean      True if the value is found
     */

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

    /*
     * Recieves a specific user based on Username and Password
     * 
     * Used for the login method in the facade
     * 
     * @param username      The username to be compared
     * @param password      The password to be compared
     * @return user         Returns the user that matches username and password, else null
     */

    public static User retrieveUser(String username, String passsword){
        for (int i = 0; i < userList.size(); i++){
                if(userList.get(i).login(username, passsword)) return userList.get(i);
        }
        return null;
    }

    /*
     * Used to get users based on UUID
     * 
     * @param userUUID      The UUID to see if in the user list
     * @return user         Returns the matching user in the userlist, else null
     */

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

    /*
     * Helper method to sort a project by user points
     * 
     * Resourced from https://www.geeksforgeeks.org/selection-sort/
     * Implement selection sort
     * Used within leaderboard
     * 
     * @param users     The arraylist for users to sort
     * @param UUID      The project UUID to determine the users points
     * @return users    The sorted arraylist for users
     */

    private static ArrayList<User> sortArray(ArrayList<User> users, UUID projectUUID){
        if(users.size() == 0) return null;
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

    /*
     * Returns a sorted list of Users in a project based on points
     * 
     * Uses helper Method sortArray
     * 
     * @param projectUUID       The project to store users based on
     * @return User             The sorted list of users in a project based on that project's points
     */

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

    /*
     * Returns all the users within a project not based on points
     * 
     * @param projectUUID       The project to store users based on
     * @return userProject      The arraylist of users containing that project
     */

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
