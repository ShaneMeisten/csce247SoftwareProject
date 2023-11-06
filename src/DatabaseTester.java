import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.Date;

public class DatabaseTester {
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
    
    User User1 = new User(id1, name1, role1, adminPerms1, team1, username1, password1, phone1, email1, currentProjects1, invitedProjects1);

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

    User User2 = new User(id2, name2, role2, adminPerms2, team2, username2, password2, phone2, email2, currentProjects2, invitedProjects2);
    
    users.addUser(User1);
    users.addUser(User2);

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
    UUID tdId1 = todo1;
    boolean tdDone1 = true;
    String tdDescription1 = "First toilet";
    ToDo ToDo1 = new ToDo(tdDone1,tdDescription1, tdId1);

    UUID tdId2 = todo2;
    boolean tdDone2 = false;
    String tdDescription2 = "Second Toilet!";
    ToDo ToDo2 = new ToDo(tdDone2, tdDescription2, tdId2);

    UUID tdId3 = todo3;
    boolean tdDone3 = false;
    String tdDescription3 = "Mix First Batch!";
    ToDo ToDo3 = new ToDo(tdDone3, tdDescription3, tdId3);

    UUID tdId4 = todo4;
    boolean tdDone4 = true;
    String tdDescription4 = "First Loaf!";
    ToDo ToDo4 = new ToDo(tdDone4, tdDescription4, tdId4);

    UUID cId1 = comment1;
    String cName1 = "Clean!";
    String cDescription1 = "Great job on this one!";
    UUID cAuthor1 = user2;
    Date cDate1 = new Date("Tue Oct 17 09:04:55 EDT 2023");
    ArrayList<Comment> replies1 = new ArrayList<Comment>();
    Comment Comment1 = new Comment(cId1, cName1, cDescription1, cAuthor1, cDate1, replies1);

    UUID cId2 = comment2;
    String cName2 = "Thanks!";
    String cDescription2 = "I do my best!";
    UUID cAuthor2 = user2;
    Date cDate2 = new Date("Tue Oct 17 10:04:55 EDT 2023");
    ArrayList<Comment> replies2 = new ArrayList<Comment>();
    Comment Comment2 = new Comment(cId2, cName2, cDescription2, cAuthor2, cDate2, replies2);

    UUID cId3 = comment3;
    String cName3 = "Yum!";
    String cDescription3 = "That loaf was wonderful!";
    UUID cAuthor3 = user1;
    Date cDate3 = new Date("Tue Oct 17 09:07:55 EDT 2023");
    ArrayList<Comment> replies3 = new ArrayList<Comment>();
    replies3.add(Comment2);
    Comment Comment3 = new Comment(cId3, cName3, cDescription3, cAuthor3, cDate3, replies3);

    UUID cId4 = comment4;
    String cName4 = "Don't Worry!";
    String cDescription4 = "I will bake these soon!";
    UUID cAuthor4 = user1;
    Date cDate4 = new Date("Wed Oct 18 09:07:55 EDT 2023");
    ArrayList<Comment> replies4 = new ArrayList<Comment>();
    Comment Comment4 = new Comment(cId4, cName4, cDescription4, cAuthor4, cDate4, replies4);


    UUID tId1 = task1;
    String tTitle1 = "Clean First Bathroom";
    String tDescription1 = "Really thorough!";
    double tWeight1 = 2.0;
    Date tDueDate1 = new Date("Tue Oct 17 23:59:59 EDT 2023");
    ArrayList<String> tCategories1 = new ArrayList<String>();
    tCategories1.add("enticing");
    tCategories1.add("exciting");
    boolean tStatus1 = true;
    double tCompletionTime1 = 0.5;
    User tAuthor1 = users.getUser(user1);
    User tAssigned1 = users.getUser(user1);
    ArrayList<ToDo> tToDoList1 = new ArrayList<ToDo>();
    ArrayList<Comment> comments1 = new ArrayList<Comment>();
    tToDoList1.add(ToDo1);
    tToDoList1.add(ToDo2);
    comments1.add(Comment1);
    Task Task1 = new Task(tId1, tTitle1, tDescription1, tDueDate1, tWeight1, tCategories1, comments1,tToDoList1, tStatus1, tCompletionTime1, tAssigned1, tAuthor1);

    UUID tId2 = task2;
    String tTitle2 = "Clean Second Bathroom";
    String tDescription2 = "Super Clean!";
    double tWeight2 = 4.0;
    Date tDueDate2 = new Date("Tue Oct 17 23:59:59 EDT 2023");
    ArrayList<String> tCategories2 = new ArrayList<String>();
    tCategories2.add("fun");
    tCategories2.add("hygenic");
    boolean tStatus2 = false;
    double tCompletionTime2 = 0.5;
    User tAuthor2 = users.getUser(user1);
    User tAssigned2 = users.getUser(user1);
    ArrayList<ToDo> tTodoList2 = new ArrayList<ToDo>();
    ArrayList<Comment> comments2 = new ArrayList<Comment>();
    Task Task2 = new Task(tId2, tTitle2, tDescription2, tDueDate2, tWeight2, tCategories2, comments2, tTodoList2, tStatus2, tCompletionTime2, tAssigned2, tAuthor2);

    UUID tId3 = task3;
    String tTitle3 = "Bake First Batch";
    String tDescription3 = "Make 'em tasty!";
    double tWeight3 = 3.5;
    Date tDueDate3 = new Date("Tue Oct 17 10:30:59 EDT 2023");
    ArrayList<String> tCategories3 = new ArrayList<String>();
    tCategories3.add("delicious");
    tCategories3.add("rewarding");
    boolean tStatus3 = false;
    double tCompletionTime3 = 0.75;
    User tAuthor3 = users.getUser(user1);
    User tAssigned3 = users.getUser(user1);
    ArrayList<ToDo> tToDoList3 = new ArrayList<ToDo>();
    ArrayList<Comment> comments3 = new ArrayList<Comment>();
    tToDoList3.add(ToDo3);
    comments3.add(Comment4);
    Task Task3 = new Task(tId3, tTitle3, tDescription3, tDueDate3, tWeight3, tCategories3, comments3, tToDoList3, tStatus3, tCompletionTime3, tAssigned3, tAuthor3);

    UUID tId4 = task4;
    String tTitle4 = "Bake Second Batch";
    String tDescription4 = "Make 'em tastier!";
    double tWeight4 = 4.5;
    Date tDueDate4 = new Date("Tue Oct 17 10:59:59 EDT 2023");
    ArrayList<String> tCategories4 = new ArrayList<String>();
    tCategories4.add("better");
    tCategories4.add("yummier");
    boolean tStatus4 = false;
    double tCompletionTime4 = 0.75;
    User tAuthor4 = users.getUser(user1);
    User tAssigned4 = users.getUser(user1);
    ArrayList<ToDo> tToDoList4 = new ArrayList<ToDo>();
    ArrayList<Comment> comments4 = new ArrayList<Comment>();
    Task Task4 = new Task(tId4, tTitle4, tDescription4, tDueDate4, tWeight4, tCategories4, comments4, tToDoList4, tStatus4, tCompletionTime4, tAssigned4, tAuthor4);

    UUID tId5 = task5;
    String tTitle5 = "Clean First Table";
    String tDescription5 = "Super Shiny!";
    double tWeight5 = 1.0;
    Date tDueDate5 = new Date("Wed Oct 18 8:59:59 EDT 2023");
    ArrayList<String> tCategories5 = new ArrayList<String>();
    tCategories5.add("satisfying");
    tCategories5.add("beautiful");
    boolean tStatus5 = false;
    double tCompletionTime5 = 0.10;
    User tAuthor5 = users.getUser(user1);
    User tAssigned5 = users.getUser(user2);
    ArrayList<ToDo> tToDoList5 = new ArrayList<ToDo>();
    ArrayList<Comment> comments5 = new ArrayList<Comment>();
    Task Task5 = new Task(tId5, tTitle5, tDescription5, tDueDate5, tWeight5, tCategories5, comments5, tToDoList5, tStatus5, tCompletionTime5, tAssigned5, tAuthor5);

    UUID tId6 = task6;
    String tTitle6 = "Clean Second Table";
    String tDescription6 = "Super Shiny!";
    double tWeight6 = 1.0;
    Date tDueDate6 = new Date("Wed Oct 18 8:59:59 EDT 2023");
    ArrayList<String> tCategories6 = new ArrayList<String>();
    tCategories5.add("better");
    tCategories5.add("clean");
    boolean tStatus6 = false;
    double tCompletionTime6 = 0.10;
    User tAuthor6 = users.getUser(user1);
    User tAssigned6 = users.getUser(user2);
    ArrayList<ToDo> tToDoList6 = new ArrayList<ToDo>();
    ArrayList<Comment> comments6 = new ArrayList<Comment>();
    Task Task6 = new Task(tId6, tTitle6, tDescription6, tDueDate6, tWeight6, tCategories6, comments6, tToDoList6, tStatus6, tCompletionTime6, tAssigned6, tAuthor6);

    UUID tId7 = task7;
    String tTitle7 = "Bake First Batch";
    String tDescription7 = "Make it great!";
    double tWeight7 = 6.0;
    Date tDueDate7 = new Date("Tue Oct 17 10:30:59 EDT 2023");
    ArrayList<String> tCategories7 = new ArrayList<String>();
    tCategories3.add("delicious");
    tCategories3.add("fluffy");
    boolean tStatus7 = true;
    double tCompletionTime7 = 2.0;
    User tAuthor7 = users.getUser(user1);
    User tAssigned7 = users.getUser(user2);
    ArrayList<ToDo> tToDoList7 = new ArrayList<ToDo>();
    ArrayList<Comment> comments7 = new ArrayList<Comment>();
    tToDoList7.add(ToDo4);
    comments7.add(Comment3);
    Task Task7 = new Task(tId7, tTitle7, tDescription7, tDueDate7, tWeight7, tCategories7, comments7, tToDoList7, tStatus7, tCompletionTime7, tAssigned7, tAuthor7);

    UUID tId8 = task8;
    String tTitle8 = "Bake Second Batch";
    String tDescription8 = "Make it even better!";
    double tWeight8 = 6.5;
    Date tDueDate8 = new Date("Tue Oct 17 10:30:59 EDT 2023");
    ArrayList<String> tCategories8 = new ArrayList<String>();
    tCategories3.add("delicious");
    tCategories3.add("fluffy");
    boolean tStatus8 = false;
    double tCompletionTime8 = 2.0;
    User tAuthor8 = users.getUser(user1);
    User tAssigned8 = users.getUser(user2);
    ArrayList<ToDo> tToDoList8 = new ArrayList<ToDo>();
    ArrayList<Comment> comments8 = new ArrayList<Comment>();
    Task Task8 = new Task(tId8, tTitle8, tDescription8, tDueDate8, tWeight8, tCategories8, comments8, tToDoList8, tStatus8, tCompletionTime8, tAssigned8, tAuthor8);

    UUID cmId1 = column1;
    String cmTitle1 = "Upstairs Toilets";
    double cmWeight1 = 5.0;
    boolean cmStatus1 = false;
    double cmCompletionTime1 = 3.0;
    Date cmCreatedTime1 = new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor1 = users.getUser(user1);
    ArrayList<Task> cmTasks1 = new ArrayList<Task>();
    cmTasks1.add(Task1);
    Column Column1 = new Column(cmId1, cmTitle1, cmWeight1, cmStatus1, cmCompletionTime1, cmCreatedTime1, cmAuthor1, cmTasks1);

    UUID cmId2 = column2;
    String cmTitle2 = "Downstairs Toilets";
    double cmWeight2 = 5.0;
    boolean cmStatus2 = true;
    double cmCompletionTime2 = 3.0;
    Date cmCreatedTime2 = new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor2 = users.getUser(user1);
    ArrayList<Task> cmTasks2 = new ArrayList<Task>();
    cmTasks2.add(Task2);
    Column Column2 = new Column(cmId2, cmTitle2, cmWeight2, cmStatus2, cmCompletionTime2, cmCreatedTime2, cmAuthor2, cmTasks2);

    UUID cmId3 = column3;
    String cmTitle3 = "Cookies for Corporate";
    double cmWeight3 = 3.0;
    boolean cmStatus3 = false;
    double cmCompletionTime3 = 2.0;
    Date cmCreatedTime3 = new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor3 = users.getUser(user1);
    ArrayList<Task> cmTasks3 = new ArrayList<Task>();
    cmTasks3.add(Task3);
    Column Column3 = new Column(cmId3, cmTitle3, cmWeight3, cmStatus3, cmCompletionTime3, cmCreatedTime3, cmAuthor3, cmTasks3);

    UUID cmId4 = column4;
    String cmTitle4 = "Cookies for Boss";
    double cmWeight4 = 2.9;
    boolean cmStatus4 = false;
    double cmCompletionTime4 = 1.5;
    Date cmCreatedTime4 = new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor4 = users.getUser(user1);
    ArrayList<Task> cmTasks4 = new ArrayList<Task>();
    cmTasks4.add(Task4);
    Column Column4 = new Column(cmId4, cmTitle4, cmWeight4, cmStatus4, cmCompletionTime4, cmCreatedTime4, cmAuthor4, cmTasks4);

    UUID cmId5 = column5;
    String cmTitle5 = "Dining Room Tables";
    double cmWeight5 = 3.0;
    boolean cmStatus5 = false;
    double cmCompletionTime5 = 0.5;
    Date cmCreatedTime5 = new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor5 = users.getUser(user1);
    ArrayList<Task> cmTasks5 = new ArrayList<Task>();
    cmTasks5.add(Task5);
    Column Column5 = new Column(cmId5, cmTitle5, cmWeight5, cmStatus5, cmCompletionTime5, cmCreatedTime5, cmAuthor5, cmTasks5);

    UUID cmId6 = column6;
    String cmTitle6 = "Bar Room Tables";
    double cmWeight6 = 3.0;
    boolean cmStatus6 = false;
    double cmCompletionTime6 = 0.5;
    Date cmCreatedTime6 = new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor6 = users.getUser(user1);
    ArrayList<Task> cmTasks6 = new ArrayList<Task>();
    cmTasks6.add(Task6);
    Column Column6 = new Column(cmId6, cmTitle6, cmWeight6, cmStatus6, cmCompletionTime6, cmCreatedTime6, cmAuthor6, cmTasks6);

    UUID cmId7 = column7;
    String cmTitle7 = "Bread for Orders";
    double cmWeight7 = 4.0;
    boolean cmStatus7 = true;
    double cmCompletionTime7 = 3.0;
    Date cmCreatedTime7= new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor7 = users.getUser(user1);
    ArrayList<Task> cmTasks7 = new ArrayList<Task>();
    cmTasks7.add(Task7);
    Column Column7 = new Column(cmId7, cmTitle7, cmWeight7, cmStatus7, cmCompletionTime7, cmCreatedTime7, cmAuthor7, cmTasks7);

    UUID cmId8 = column8;
    String cmTitle8 = "Bread for Customers";
    double cmWeight8 = 2.5;
    boolean cmStatus8 = false;
    double cmCompletionTime8 = 3.0;
    Date cmCreatedTime8 = new Date("Mon Oct 16 9:00:00 EDT 2023");
    User cmAuthor8 = users.getUser(user1);
    ArrayList<Task> cmTasks8 = new ArrayList<Task>();
    cmTasks8.add(Task8);
    Column Column8 = new Column(cmId8, cmTitle8, cmWeight8, cmStatus8, cmCompletionTime8, cmCreatedTime8, cmAuthor8, cmTasks8);
    
    // Create and Add Projects
    completed1.add(Task1);
    ongoing1.add(Task2);
    ongoing2.add(Task3);
    ongoing2.add(Task4);
    ongoing3.add(Task5);
    ongoing3.add(Task6);
    completed4.add(Task7);
    ongoing4.add(Task8);
    columnList1.add(Column1);
    columnList1.add(Column2);
    columnList2.add(Column3);
    columnList2.add(Column4);
    columnList3.add(Column5);
    columnList3.add(Column6);
    columnList4.add(Column7);
    columnList4.add(Column8);

    Project Project1 = new Project(pId1, pName1, type1, layout1, completed1, ongoing1, columnList1, history1);
    Project Project2 = new Project(pId2, pName2, type2, layout2, completed2, ongoing2, columnList2, history2);
    Project Project3 = new Project(pId3, pName3, type3, layout3, completed3, ongoing3, columnList3, history3);
    Project Project4 = new Project(pId4, pName4, type4, layout4, completed4, ongoing4, columnList4, history4);

    projects.addProject(Project1);
    projects.addProject(Project2);
    projects.addProject(Project3);
    projects.addProject(Project4);
    
    // Write
    writer.saveUsers();
    writer.saveProjects();

    // Print
    System.out.println("All Users:");
    ArrayList<User> printable = users.getUsers();
    for (User u : printable)
      System.out.println(u.getName());

    // Remove all
    users.removeUser(User1, User2.getUUID());
    users.removeUser(User1, User1.getUUID());

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

  public static final UUID user1 = UUID.fromString("9114f390-f300-4f8e-af9f-d66ae2b87adb");
  public static final UUID user2 = UUID.fromString("e2ec2f67-57c7-4a35-b79d-a0d69f2301cd");
  public static final UUID project1 = UUID.fromString("ca62fd17-27c7-4cd4-8ebb-b8f8518cc46c");
  public static final UUID project2 = UUID.fromString("14f1f749-e6df-42dc-85cc-d986926c6166");
  public static final UUID project3 = UUID.fromString("ff0871e3-5046-4abb-bbe2-06c045d06a78");
  public static final UUID project4 = UUID.fromString("e9f73e53-565f-460d-ac82-961e29d3d922");
  public static final UUID column1 = UUID.fromString("fc86508e-7741-4bf2-a361-023e722b5bf2");
  public static final UUID column2 = UUID.fromString("a34b7792-ee27-4898-998f-3a02fabea495");
  public static final UUID column3 = UUID.fromString("608ecc89-a3c4-493b-9f9c-f4ec2e9225df");
  public static final UUID column4 = UUID.fromString("0077d443-e2bf-4efa-90f0-ae93ea50864d");
  public static final UUID column5 = UUID.fromString("44d6021a-a4be-426a-9743-ef8ff5e7b92c");
  public static final UUID column6 = UUID.fromString("08d6d4b4-8ec3-4295-96a8-bee93cd58ed5");
  public static final UUID column7 = UUID.fromString("1437e88f-9d80-47f4-8693-47b4a12190e3");
  public static final UUID column8 = UUID.fromString("f324991b-c874-41a8-9984-9956a0e8363f");
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
  public static final UUID comment1 = UUID.fromString("af322e03-7625-4dc7-bbb3-fcdf10939a2a");
  public static final UUID comment2 = UUID.fromString("a721e614-70f8-4a02-91fd-a4ac8e0c83ec");
  public static final UUID comment3 = UUID.fromString("f74001d1-5ec6-434f-825d-8d714595195a");
  public static final UUID comment4 = UUID.fromString("e1a934f1-9225-4229-ad81-16fc8db5340a");
}
