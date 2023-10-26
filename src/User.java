import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/*
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

    public UUID getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getRole(){
        return role;
    }
    public boolean getAdminPerms(){
        return adminPerms;
    }
    public String getTeam() {
        return team;
    }
    public String getPassword(){
        return password;
    }
    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }


    public boolean login(String username, String passsword){
        if (this.username.equals(username) && this.password.equals(passsword)) return true;
        return false;
    }

    public Boolean containsProject(UUID projectUUID){
        for(UUID id: currentProjects.keySet()){
            if(id.compareTo(projectUUID) == 0){
                return true;
            }
        }
        return false;
    }

    public boolean AcceptInvite(int invite){
        if(invite >= 0 && invite <= invitedProjects.size()){
            currentProjects.put(invitedProjects.get(invite), 0.0);
            invitedProjects.remove(invite);
            return true;
        }
        return false;
    }

    public boolean RemoveProject(int invite){
        if(invite >= 0 && invite <= currentProjects.size()){
            currentProjects.remove(invite);
            return true;
        }
        return false;
    }

    public boolean addPoints(UUID projectUUID, double add){
        for(UUID id : currentProjects.keySet()){
            if(id.compareTo(projectUUID) == 0){
                currentProjects.replace(projectUUID, currentProjects.get(projectUUID) + add);
                return true;
            }
        }
        return false;
    }

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

    public boolean checkUsername(String username){
        return this.username.equals(username);
    }

    public String getUsername(){
        return username;
    }

    public UUID getUUID(){
        return id;
    }

    public ArrayList<UUID> getCurrentProjects(){
        ArrayList<UUID> currentProjects = new ArrayList<UUID>();
        for(UUID i: this.currentProjects.keySet()) currentProjects.add(i);
        return currentProjects;
    }
    public Double getProjectPoints(UUID projectUUID){
        return currentProjects.get(projectUUID);
    }
    
    public ArrayList<UUID> getInvitedProjects(){
        return this.invitedProjects;
    }

    public boolean changePassword(String username, String password){
        if(this.username.equals(username) ){
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean addInvite(UUID newUUID){
        if (invitedProjects.contains(newUUID)) return false;
        invitedProjects.add(newUUID);
        return true;
    }

    public boolean isAdmin(){
        return adminPerms;
    }

}
