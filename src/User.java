import java.util.ArrayList;
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
    private double points;
    private ArrayList<UUID> currentProjects;
    private ArrayList<UUID> inviteProjects;

    public User(String name, String role, String team, boolean adminPerms,
    String phone, String email, double points, String haveUUID){
        this.name = name;
        this.role = role;
        this.team = team;
        this.adminPerms = adminPerms;
        this.phone = phone;
        this.email = email;
        this.points = points;
        
        if(haveUUID.isEmpty()) this.id = UUID.randomUUID();
        else this.id = UUID.fromString(haveUUID);
    }

    public User login(String username, String passsword){
        if (this.username.equals(username) && this.password.equals(passsword)) return this;
        return null;
    }

}
