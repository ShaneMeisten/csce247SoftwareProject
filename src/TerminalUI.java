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
        if(!login()) return;
        if(!UserHomePage()) login();
        

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

        facade.createProject("Electric Missiles", "SCRUM");
        //In Electric Missles
        //Create Doing Column
        //Create task "Curve the metal to make a cylindrical shape" to the 'Doing' column.
        //Create task "Make impossible burger possible"
        facade.createProject("Soap Free Washers", "SCRUM");
        facade.createProject("Air Computers", "SCRUM");

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
     * @return boolean      Returns if the user has created an account(true) or wants to quit(false)
     */

    public boolean login() {
        while(true) {
            System.out.println("Welcome\n(1)Login\n(2)Create Account\n(3)Quit");
            String userKB = scanner.nextLine();
            if (userKB.equals("1")) {
                System.out.println("Enter Username:");
                String username = scanner.nextLine();
                System.out.println("Enter Passowrd:");
                String password = scanner.nextLine();
                if(facade.login(username, password)) {
                    return true;
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
                if(facade.createUser(newUser)) return true;
                System.out.println("Issue Creating User");
                continue;
            }
            else if (userKB.equals("3")) {
                return false;
            }
        }
    }

    
    public boolean UserHomePage() {
        User currentUser = facade.getCurrentUser();
        System.out.println("Welcome " + currentUser.getName());
        while(true) {
            System.out.println("(1)See Current Projects\n(2)See Invited Projects\n(3)Logout");
            String command = scanner.nextLine();
            if (command.equals("1")) {
                int counter = 0;
                System.out.println("Printing Current Projects:\n\tEnter the number to open project\n\tPress Q to exit");
                for(Project project : facade.getUserCurrentProjects()) {
                    System.out.println(counter + ":\t" + project.getName());
                    counter++;
                }
                String invite = scanner.nextLine();
                if(invite.toLowerCase().equals("q")) continue;
                if(facade.setCurrentProject(Integer.valueOf(invite))) return true;
                else System.out.println("Incorrect Value");
            }
            else if (command.equals("2")) {
                int counter = 0;
                System.out.println("Printing Current Projects:\n\tEnter the number to open project\n\tPress Q to exit");
                for(Project project : facade.getUserInvitedProjects()) {
                    System.out.println(counter + ":\t" + project.getName());
                    counter++;
                }
                String invite = scanner.nextLine();
                if(invite.toLowerCase().equals("q")) continue;
                if(facade.AcceptInvite(Integer.valueOf(invite))) return true;
                else System.out.println("Incorrect Value");
            }
            else if (command.equals("3")) {
                return false;
            }

        }
    }
}
