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
  public static final UUID task1 = UUID.fromString("d25010c8-82cc-446f-abc1-bf7934a06d20");
  public static final UUID task2 = UUID.fromString("a8626395-a0e4-4f7b-9aac-1e006922792e");
  public static final UUID task3 = UUID.fromString("251f6dc2-ece5-4094-af2d-871820c77810");
  public static final UUID task4 = UUID.fromString("04fdb610-009b-46bd-bf26-b0088afe82d4");
  public static final UUID task5 = UUID.fromString("322a78b0-3e00-4a6b-a0ad-3e8cf4860479");
  public static final UUID task6 = UUID.fromString("84d1684e-20ad-49d8-8e55-9f3926711434");
  public static final UUID task7 = UUID.fromString("2307fd73-00f8-4822-819f-f78c049acb2a");
  public static final UUID task8 = UUID.fromString("e13c7517-511d-46be-a836-965c0e6f048a");
  public static final UUID todo1 = UUID.fromString("00d9f5d3-9c1b-4acb-8cc7-e9744c1eb82a");
  public static final UUID todo2 = UUID.fromString("254584da-3d65-43e0-bee1-667b15e54c7e");
  public static final UUID todo3 = UUID.fromString("e0f6a5c6-ddb2-480b-898c-96d0f080efe1");
  public static final UUID todo4 = UUID.fromString("0249072b-8014-464d-8499-d61a07bfb7a9");


  public static void main(String[] args) {
    DataLoader loader = new DataLoader();
    DataWriter writer = new DataWriter();
    UserCatalog users = UserCatalog.getInstance();
    ProjectCatalog projects = ProjectCatalog.getInstance();

    // Create and add Users
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
    
    users.addUser(user1);
    users.addUser(user2);

    // Create Projects' Attributes
    UUID pId1 = project1;
    String pName1 = "Cleaning Toilets";
    String type1 = "Permanent";
    Layout layout1 = Layout.TASK_LIST;
    ArrayList<Task> completed1 = new ArrayList<Task>();
    ArrayList<Task> ongoing1 = new ArrayList<Task>();
    ArrayList<Column> columnList1 = new ArrayList<Column>();
    History history1 = new History();

    UUID pId2 = project2;
    String pName2 = "Baking cookies";
    String type2 = "Temporary";
    Layout layout2 = Layout.TASK_LIST;
    ArrayList<Task> completed2 = new ArrayList<Task>();
    ArrayList<Task> ongoing2 = new ArrayList<Task>();
    ArrayList<Column> columnList2 = new ArrayList<Column>();
    History history2 = new History();

    UUID pId3 = project3;
    String pName3 = "Cleaning Tables";
    String type3 = "Temporary";
    Layout layout3 = Layout.TASK_LIST;
    ArrayList<Task> completed3 = new ArrayList<Task>();
    ArrayList<Task> ongoing3 = new ArrayList<Task>();
    ArrayList<Column> columnList3 = new ArrayList<Column>();
    History history3 = new History();
    
    UUID pId4 = project4;
    String pName4 = "Baking Bread";
    String type4 = "Permanent";
    Layout layout4 = Layout.TASK_LIST;
    ArrayList<Task> completed4 = new ArrayList<Task>();
    ArrayList<Task> ongoing4 = new ArrayList<Task>();
    ArrayList<Column> columnList4 = new ArrayList<Column>();
    History history4 = new History();

    // Populate Projects' Attributes
    
    
    // Create and Add Projects

    
    // Write
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
