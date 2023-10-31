import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;

public class DatabaseTester {
  public static final UUID user1 = UUID.fromString("9114f390-f300-4f8e-af9f-d66ae2b87adb");
  public static final UUID user2 = UUID.fromString("e2ec2f67-57c7-4a35-b79d-a0d69f2301cd");
  public static final UUID project1 = UUID.fromString("ca62fd17-27c7-4cd4-8ebb-b8f8518cc46c");
  public static final UUID project2 = UUID.fromString("14f1f749-e6df-42dc-85cc-d986926c6166");
  public static final UUID project3 = UUID.fromString("ff0871e3-5046-4abb-bbe2-06c045d06a78");
  public static final UUID project4 = UUID.fromString("e9f73e53-565f-460d-ac82-961e29d3d922");


  public static void main(String[] args) {
    DataLoader loader = new DataLoader();
    DataWriter writer = new DataWriter();
    UserCatalog users = UserCatalog.getInstance();
    ProjectCatalog projects = ProjectCatalog.getInstance();

    UUID id1 = user1;
    String name1 = "John Johnson";
    String role1 = "Janitor";
    String team1 = "Cleanup Crew";
    String username1 = "JJohnson";
    String password1 = "cle@nToilets4Life";
    String phone1 = "111-222-3333";
    String email1 = "JJJanitor@gmail.com";
    boolean adminPerms1 = true;
    HashMap<UUID, Double> currentProjects1 = new HashMap<UUID, Double>();
    ArrayList<UUID> invitedProjects1 = new ArrayList<UUID>();
    currentProjects1.put(project1, 99.0);
    currentProjects1.put(project2, 3.5);
    invitedProjects1.add(project3);
    invitedProjects1.add(project4);
    
    User user1 = new User(id1, name1, role1, adminPerms1, team1, username1, password1, phone1, email1, currentProjects1, invitedProjects1);

    UUID id2 = user2;
    String name2 = "Barry Barnwood";
    String role2 = "Baker";
    String team2 = "Bread unite";
    String username2 = "BBarnwood";
    String password2 = "b@ker$dozen";
    String phone2 = "444-555-6666";
    String email2 = "BBBaker@gmail.com";
    boolean adminPerms2 = false;
    HashMap<UUID, Double> currentProjects2 = new HashMap<UUID, Double>();
    ArrayList<UUID> invitedProjects2 = new ArrayList<UUID>();
    currentProjects2.put(project4, 95.0);
    currentProjects2.put(project3, 4.3);
    invitedProjects2.add(project2);
    invitedProjects2.add(project1);

    User user2 = new User(id2, name2, role2, adminPerms2, team2, username2, password2, phone2, email2, currentProjects2, invitedProjects2);

    // TODO create and add projects 

    // Add all and Write
    users.addUser(user1);
    users.addUser(user2);
    writer.saveUsers();

    // Print
    System.out.println("All Users:");
    ArrayList<User> printable = users.getUsers();
    for (User u : printable)
      System.out.println(u.getName());

    // Remove all
    users.removeUser(user1, user2.getUUID());
    users.removeUser(user1, user1.getUUID());

    // Print
    System.out.println("All Users:");
    printable = users.getUsers();
    for (User u : printable)
      System.out.println(u.getName());


    // Load
    ArrayList<User> loadedUsers = loader.getUsers();
    for (User u : loadedUsers)
      users.addUser(u);

    // Print
    System.out.println("All Users:");
    printable = users.getUsers();
    for (User u : printable)
      System.out.println(u.getName());
  }
}
