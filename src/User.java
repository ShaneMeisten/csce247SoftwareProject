import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/*
 * Allows the ability to store data for a created user and the projects associated with it
 * 
 * @author  Cameron Reyes
 */
public class User{
    private UUID id;
    private String name;
    private String role;
    private String team;
    private String username;
    private String password;
    private String phone;
    private String email;
    private boolean adminPerms;
    private HashMap<UUID, Double> currentProjects;
    private ArrayList<UUID> invitedProjects;

    /*
     * Public constructor for a User when loading data
     * 
     * Works through DataLoader
     * 
     * @param id                UUID for specific user
     * @param name              name of the user
     * @param role              the role of the user
     * @param adminPerms        Ability for user to remove user from project
     * @param team              the team's name
     * @param username          What the user uses for login
     * @param password          What the user uses for login
     * @param phone             The phonenumber of the user
     * @param email             The user's email
     * @param currentProjects   UUID of users project and points associated
     * @param invitedProejcts   UUID of invited projexts
     */
    
    public User(UUID id, String name, String role, boolean adminPerms, String team,
    String username, String password, String phone,  String email, HashMap<UUID, Double> currentProjects, ArrayList<UUID> invitedProjects){
        this.id = id;
        this.name = name;
        this.role = role;
        this.adminPerms = adminPerms;
        this.team = team;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.currentProjects = currentProjects;
        this.invitedProjects = invitedProjects;
    }

    /*
     * Basic creation when storing users
     * 
     * @param name              name of the user
     * @param username          What the user uses for login
     * @param password          What the user uses for login
     * @param phone             The phonenumber of the user
     * @param email             The user's email
     */

    public User(String name, String username, String password, String phone, String email){
        this.id = UUID.randomUUID();
        this.name = name;
        this.role = "";
        this.adminPerms = false;
        this.team = "";
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.currentProjects = new  HashMap<UUID, Double>();
        this.invitedProjects = new ArrayList<UUID>();
    }

    /*
     * The user's name
     * 
     * @return name     the User's name
     */

    public String getName(){
        return name;
    }

    /*
     * The user's role
     * 
     * @return role     the User's role
     */

    public String getRole(){
        return role;
    }

    /*
     * The user's admin perms
     * 
     * @return adminperms     If the user is an admin = true, else false
     */
    public boolean getAdminPerms(){
        return adminPerms;
    }

    /*
     * The user's team name
     * 
     * @return team     The team name
     */
    public String getTeam() {
        return team;
    }

    /*
     * The user's passwrod
     * 
     * @return passowrd     The user's password
     */

    public String getPassword(){
        return password;
    }

    /*
     * The user's phone
     * 
     * @return phone     The user's phone number
     */

    public String getPhone(){
        return phone;
    }

    /*
     * The user's email
     * 
     * @return email     The user's email
     */

    public String getEmail(){
        return email;
    }

    /*
     * The user's username
     * 
     * @return username     The user's username
     */

    public String getUsername(){
        return username;
    }
    
    /*
     * The user's UUID
     * 
     * @return UUID     The user's id
     */

    public UUID getUUID(){
        return id;
    }

    /*
     * Give's user the UUIDS for proejcts they are in
     * 
     * @return currentProjects      The UUID's of Projects the user is in
     */

    public ArrayList<UUID> getCurrentProjectsUUID(){
        ArrayList<UUID> currentProjects = new ArrayList<UUID>();
        for(UUID i: this.currentProjects.keySet()) currentProjects.add(i);
        return currentProjects;
    }

    public HashMap<UUID, Double> getCurrentProjects() {
        return currentProjects;
    }

    /*
     * Get's the project points for a specific proejct for the user
     * 
     * @param UUID      The UUID of the project
     * @return Double   The Points the user has for that project
     */
    public Double getProjectPoints(UUID projectUUID){
        return currentProjects.get(projectUUID);
    }
    
    /*
     * Gives user the list of UUIDS for invited Projects
     * 
     * @return invited Projects     returns the arraylist of invited projects
     */
    public ArrayList<UUID> getInvitedProjects(){
        return this.invitedProjects;
    }

    /*
     * Makes Admin perms true
     */

    public void becomeAdmin() {
        this.adminPerms = true;
    }

    /*
     * Makes Admin perms false
     */

    public void removeAdmin() {
        this.adminPerms = false;
    }

    /*
     * Allows usercatalog to see if username and password match
     * 
     * @param username   inputted username
     * @param password   inputted password  
     * @return boolean   true if the username and password match 
     */

    public boolean login(String username, String passsword){
        if (this.username.equals(username) && this.password.equals(passsword)) return true;
        return false;
    }

    /*
     * Checks to see if user contains a project
     * 
     * Used for creation of Leaderboard and Users in Proejct
     * 
     * @param UUID      The projects UUID
     * @return boolean  True if the project UUID in current projects
     */

    public Boolean containsProject(UUID projectUUID){
        for(UUID id: currentProjects.keySet()){
            if(id.compareTo(projectUUID) == 0){
                return true;
            }
        }
        return false;
    }

    /*
     * Allows the ability to accept invited projets
     * 
     * Moves invited project to current projects
     * 
     * @param invite    The location in the array for project to add
     * @return boolean  If the invite number is within the invited projects, then True
     */

    public boolean AcceptInvite(int invite){
        if(invite >= 0 && invite <= invitedProjects.size()){
            currentProjects.put(invitedProjects.get(invite), 0.0);
            invitedProjects.remove(getInvitedProjects().get(invite));
            return true;
        }
        return false;
    }
    
    /*
     * Allows the ability to remove current projet
     * 
     * @param invite    The location in the array for project to remove
     * @return boolean  If the invite number is within the current projects, then True
     */

    public boolean RemoveProject(int invite){
        if(invite >= 0 && invite <= currentProjects.size()){
            currentProjects.remove(getCurrentProjects().get(invite));
            return true;
        }
        return false;
    }

    /*
     * Adds points to designated project
     * 
     * Does not check if points are negative
     * 
     * @param UUID      The project to add points to
     * @param add       How much points added to that project
     * @return boolean  If the points were allocated; true
     */

    public boolean addPoints(UUID projectUUID, double add){
        for(UUID id : currentProjects.keySet()){
            if(id.compareTo(projectUUID) == 0){
                currentProjects.replace(projectUUID, currentProjects.get(projectUUID) + add);
                return true;
            }
        }
        return false;
    }

    /*
     * Removes points to designated project
     * 
     * Does not check if points are negative
     * If points added are bigger then current points, current points is 0
     * 
     * @param UUID      The project to remove points to
     * @param add       How much points removed to that project
     * @return boolean  If the points were allocated; true
     */

    public boolean removePoints(UUID projectUUID, double sub){
        for(UUID id : currentProjects.keySet()){
            if(id.compareTo(projectUUID) == 0){
                if(sub > currentProjects.get(id)){
                    currentProjects.replace(projectUUID, 0.0);
                    return true;
                }
                currentProjects.replace(projectUUID, currentProjects.get(projectUUID) - sub);
                return true;
            }
        }
        return false;
    }

    /*
     * Stops the creation of Users with the same username
     * 
     * Used within UserCatalog and Facade
     * 
     * @param username  The new user's username
     * @return boolean  True if the usernames match
     */

    public boolean checkUsername(String username){
        return this.username.equals(username);
    }

    /*
     * Allows User to change a password
     * 
     * Not implemented fully, but works if matching username
     * 
     * @param username      The inputted username
     * @param password      The inputted password
     * @return boolean      True if the password is changed
     */

    public boolean changePassword(String username, String password){
        if(this.username.equals(username) ){
            this.password = password;
            return true;
        }
        return false;
    }

    /*
     * Adds a new project UUID to the invited Project
     * 
     * Does not add duplicates
     * 
     * @param UUID      The project's UUID
     * @return boolean  True if UUID not in invited Project
     */

    public boolean addInvite(UUID newUUID){
        if (invitedProjects.contains(newUUID)) return false;
        invitedProjects.add(newUUID);
        return true;
    }

}
