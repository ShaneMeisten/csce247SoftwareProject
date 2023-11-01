import java.util.Scanner;

/*
 * 
 * Implement Project methods in loadData
 * When user exits a Project, it brings them to UserHomePage
 */

public class TerminalUI {
    Facade facade = new Facade();
    Scanner scanner = new Scanner(System.in);
    public TerminalUI() {
        loadData();
        login();
        

    }

    /*
     * Loads the data for scenario
     * Users:
     *  Atticus Madden
     *  Jeff Goldblum
     *  Atticus Finch
     * Projects:
     *  Electric Missiles
     *  Soap Free Washers
     *  Air Computers
     * 
     */

    public void loadData() {
        User AtticusM = new User("Atticus Madden", "Atticus Madden", "Madden123", "111-111-1111", "AtticusM@gmail.com");
        User Jeff = new User("Jeff Goldblum", "Jeff", "Goldblum123", "111-111-1112", "Jeff@gmail.com");
        User AtticusF = new User("Atticus Finch", "Atticus Finch", "Finch123", "111-111-1113", "AtticusF@gmail.com");
        facade.createUser(AtticusM);
        facade.createUser(Jeff);
        facade.createUser(AtticusF);

        facade.createProject("Electric Missiles");
        //In Electric Missles
        //Create Doing Column
        //Create task "Curve the metal to make a cylindrical shape" to the 'Doing' column.
        //Create task "Make impossible burger possible"
        facade.createProject("Soap Free Washers");
        facade.createProject("Air Computers");

        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(0).getUUID());
        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(1).getUUID());
        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(2).getUUID());
        facade.InviteUserToProject(Jeff.getUUID(), facade.getProject(0).getUUID());
        facade.InviteUserToProject(AtticusF.getUUID(), facade.getProject(0).getUUID());
        facade.login("Atticus Madden", "Madden123");
        facade.AcceptInvite(0);
        facade.AcceptInvite(0);
        facade.AcceptInvite(0);
        facade.login("Jeff", "Goldblum123");
        facade.AcceptInvite(0);
        // Create exisiting Comment "Not cylindrical enough" - by Jeff for task  "Curve the metal to make a cylindrical shape"
        facade.login("Atticus Finch", "Finch123");
        //// Create exisiting Comment  "What's a cylinder" by Atticus Finch for task  "Curve the metal to make a cylindrical shape"
        facade.AcceptInvite(0);
    }

    /*
     * Allows the user to login, create account, or exit the program
     * 
     */

    public void login() {
        while(true) {
            System.out.println("Welcome\n(1)Login\n(2)Create Account\n(3)Quit");
            String userKB = scanner.nextLine();
            if (userKB.equals("1")) {
                System.out.println("Enter Username:");
                String username = scanner.nextLine();
                System.out.println("Enter Passowrd:");
                String password = scanner.nextLine();
                if(facade.login(username, password)) {
                    UserHomePage();
                }
                continue;
                
            }
            else if (userKB.equals("2")) {
                System.out.println("Enter Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Username:");
                String username = scanner.nextLine();
                System.out.println("Enter Passowrd:");
                String password = scanner.nextLine();
                System.out.println("Enter Phone Number:");
                String phone = scanner.nextLine();
                System.out.println("Enter Email:");
                String email = scanner.nextLine();
                User newUser = new User(name, username, password, phone, email);
                if(facade.createUser(newUser)) continue;
                System.out.println("Issue Creating User");
                continue;
            }
            else if (userKB.equals("3")) {
                return;
            }
        }
    }

    /*
     * Home page for the user to see and access current/invited projects
     */

    public void UserHomePage() {
        User currentUser = facade.getCurrentUser();
        System.out.println("Welcome " + currentUser.getName());
        while(true) {
            System.out.println("(1)See Current Projects\n(2)See Invited Projects\n(3)Logout");
            String command = scanner.nextLine();
            if (command.equals("1")) {
                int counter = 0;
                System.out.println("Printing Current Projects:\n\tEnter the number to open project\n\tPress Q to exit");
                if (facade.getUserCurrentProjects() != null) {
                        for(Project project : facade.getUserCurrentProjects()) {
                        System.out.println(counter + ":\t" + project.getName());
                        counter++;
                    }
                    String invite = scanner.nextLine();
                    if(invite.toLowerCase().equals("q")) continue;
                    if(facade.setCurrentProject(Integer.valueOf(invite))) UserProjectPage();
                    else System.out.println("Incorrect Value");
                }   
                else System.out.println("No Current Projects");
            }
            else if (command.equals("2")) {
                int counter = 0;
                System.out.println("Printing Current Projects:\n\tEnter the number to open project\n\tPress Q to exit");
                if(facade.getUserInvitedProjects() != null) {
                        for(Project project : facade.getUserInvitedProjects()) {
                        System.out.println(counter + ":\t" + project.getName());
                        counter++;
                    }
                    String invite = scanner.nextLine();
                    if(invite.toLowerCase().equals("q")) continue;
                    if(facade.AcceptInvite(Integer.valueOf(invite))) continue;
                    else System.out.println("Incorrect Value");
                }
                else System.out.println("No Invited Projects");
                
            }
            else if (command.equals("3")) {
                return;
            }

        }
    }
    //Has bug currently due to else statement, just make an else if for size of command

    /*
     * Allows the ability to see the Project and it's columns and task
     */

    public void UserProjectPage() {
        Project currProject = facade.getCurrentProject();
        while (true) {
            System.out.println(currProject.getName());
            int counter = 0;
            if(currProject.getColumnList().size() > 0) {
                for (Column column: currProject.getColumnList()) {
                System.out.println("(" + counter + ") " + column.getTitle());
            }
            }
            System.out.println("(A)\nAdd Column(U) unassigned Task\n(C) completed Task\n(P) print Project\n(Q) Exit Project");
            String command = scanner.nextLine();
            if(command.toLowerCase().equals("q")) {
                return;
            }
             else if (command.toLowerCase().equals("a")) {
                System.out.println("Enter Column name:");
                String name = scanner.nextLine();
                facade.addColumnToCurrentProject(name);
                continue;
            }
            else if (command.toLowerCase().equals("p")) {
                System.out.println("Enter File Name to Save Project: ");
                String filename = scanner.nextLine();
                /*
                 * 
                 * Implement Method to save to file
                 * 
                 */
            }
            else if (command.toLowerCase().equals("c")) {
                counter = 0;
                System.out.println("Viewing unassigned Task:\n\tEnter the number to open project\n\tPress Q to exit");
                for(Task task: currProject.getCompletedTasks()) {
                    System.out.println("(" + counter + ") " + task.getTitle());
                }
                String invite = scanner.nextLine();
                if(invite.toLowerCase().equals("q")) continue;
                if(Integer.valueOf(invite) >= 0 && Integer.valueOf(invite) < currProject.getCompletedTasks().size())
                    taskPage(currProject.getCompletedTasks().get(Integer.valueOf(invite)));
                else System.out.println("Wrong input");
            }

            else if (command.toLowerCase().equals("u")) {
                counter = 0;
                System.out.println("Viewing unassigned Task:\n\tEnter the number to open project\n\tPress Q to exit");
                for(Task task: currProject.getOngoingTasks()) {
                    System.out.println("(" + counter + ") " + task.getTitle());
                }
                String invite = scanner.nextLine();
                if(invite.toLowerCase().equals("q")) continue;
                if(Integer.valueOf(invite) >= 0 && Integer.valueOf(invite) < currProject.getOngoingTasks().size())
                    taskPage(currProject.getOngoingTasks().get(Integer.valueOf(invite)));
                else System.out.println("Wrong input");
            }
            else {
                //For columns
                counter = 0;
                Column currentColumn = currProject.getColumnList().get(Integer.valueOf(command));
                System.out.println("Viewing Column Task for " + currentColumn.getTitle() + ":\n\tEnter the number to open project\n\tPress Q to exit");
                for(Task task: currentColumn.getTasks()) {
                    System.out.println("(" + counter + ") " + task.getTitle());
                }
                String invite = scanner.nextLine();
                if(invite.toLowerCase().equals("q")) continue;
                if(Integer.valueOf(invite) >= 0 && Integer.valueOf(invite) < currProject.getOngoingTasks().size())
                    taskPage(currentColumn.getTasks().get(Integer.valueOf(invite)));
                else System.out.println("Wrong input");
            }
        }
        
    }

    public void taskPage(Task task) {
        /*
         * Add ability to locate a task in project based on UUID
         * 
         */
        //List task info
        //if user inputs "Q" return
        //Make Comment Ability
    }

    public static void main (String[] args) {
    TerminalUI ui = new TerminalUI();
    }
}


