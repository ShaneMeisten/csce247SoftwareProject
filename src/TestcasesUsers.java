import java.util.ArrayList;
import java.util.UUID;

/*
 * @author Cameron
 * 
 * will complete by 10/27/23
 * 
 * USER: PASSED
 * USERCATALOG: PASSED
 * FACADE: NOT STARTED
 */

public class TestcasesUsers {

    public TestcasesUsers() {
        init();
    }

    public void init() {
        Boolean print = true;
        testUserClass(print);
        UserCatalog userCatalog = UserCatalog.getInstance();
        loadUser(userCatalog, print);
    }

    public void loadUser(UserCatalog userCatalog, Boolean print) {
        Boolean testfail = false;
        System.out.println("Adding 5 Users");
        User sam = new User("Sam", "Sammuel", "cool321", "111-111-1112", "Sammy@mail.com");
        User joe = new User("Joe", "Mars", "cool4321", "111-111-1113", "Mars@mail.com");
        User mat = new User("Mat", "Mathew", "cool", "111-111-1114", "mat@mail.com");
        User zoe = new User("Zoe", "Zoe", "cool1", "111-111-1115", "zoe@mail.com");
        User dave = new User("dave", "dave", "davecool321", "111-111-1116", "dave@mail.com");
        userCatalog.addUser(sam);
        userCatalog.addUser(joe);
        userCatalog.addUser(mat);
        userCatalog.addUser(zoe);
        userCatalog.addUser(dave);
        for (User ok: userCatalog.getUsers()) {
            System.out.println(ok.getName() + "\tusername: " + ok.getUsername());
        }
        if(userCatalog.getUsers().size() == 5)
            System.out.println("ADDING USERS WORKS");
        else
        {
            testfail = true;
            System.out.println("ERROR ADDING USERS");
        }
        System.out.println("Testing adding User To project");

        UUID testID = UUID.randomUUID();
        userCatalog.addUserToProject(zoe.getUUID(), testID);
        printUUID(zoe.getInvitedProjects());
        if(zoe.getInvitedProjects().size() == 1)    
            System.out.println("ADDING PROJECTS WORKS");
        else {
            testfail = true;
            System.out.println("ERROR ADDING PROJECTS");
        }

        System.out.println("TESTING REMOVING USER");
        if (! userCatalog.removeUser(zoe, mat.getUUID())) {
            System.out.println("TEST 1 PASS: USER NOT ADMIN");
        }
        else {
            System.out.println("TEST 1 FAIL: USER NOT ADMIN");
            testfail = true;
        }
        zoe.becomeAdmin();
        if (userCatalog.removeUser(zoe, mat.getUUID())) {
            System.out.println("TEST 2 PASS: USER ADMIN");
        }
        else {
            System.out.println("TEST 2 FAIL: USER ADMIN");
            testfail = true;
        }
        if(userCatalog.getUsers().size() == 4) {
            System.out.println("TEST 3 PASS: USER REMOVAL");
        }
        else {
            System.out.println("TEST 3 FAIL: USER REMOVAL");
            testfail = true;
        }

        System.out.println("TESTING USER RETRIVAL");
        String username = "dave";
        String password = "davecool321";
        if (userCatalog.retrieveUser(username, password) == dave) {
            System.out.println("TEST 1: COMPLETE");
        }
        else {
            System.out.println("TEST 1: INCCOMPLETE");
            testfail = true;
        }
        if(userCatalog.retrieveUser("dave", "123") == null) {
            System.out.println("TEST 2: COMPLETE");
        }
        else {
            System.out.println("TEST 2: INCCOMPLETE");
            testfail = true;
        }

        System.out.println("TESTING GET USER BY UUID");
        if (userCatalog.getUser(dave.getUUID()) == dave) {
            System.out.println("TEST 1: COMPLETE");
        }
        else {
            System.out.println("TEST 1: INCCOMPLETE");
            testfail = true;
        }
        if(userCatalog.getUser(UUID.randomUUID()) == null) {
            System.out.println("TEST 2: COMPLETE");
        }
        else {
            System.out.println("TEST 2: INCCOMPLETE");
            testfail = true;
        }
        System.out.println("TESTING LEADERBOARD IN CATALOG");
        userCatalog.addUserToProject(dave.getUUID(), testID);
        dave.AcceptInvite(0);
        zoe.AcceptInvite(0);
        if (userCatalog.getLeaderboard(testID).size() == 2) {
            System.out.println("LEADERBOARD WORKS");
            for (User ok: userCatalog.getLeaderboard(testID)) {
                System.out.println(ok.getName() + "\t" + ok.getProjectPoints(testID));
            }
        }
        else {
            System.out.println("ERROR LEADERBOARD");
            testfail = true;
        }
        System.out.println("TESTING USERS IN PROJECT");

        if (userCatalog.getUsersInProjectUUID(testID).size() == 2) {
            System.out.println("USERS IN PROJECT WORKS");
            for (User ok: userCatalog.getUsersInProjectUUID(testID)) {
                System.out.println(ok.getName() + "\t" +  ok.getProjectPoints(testID));
            }
        }
        else {
            System.out.println("ERROR USERS IN PROJECT");
            testfail = true;
        }

        if (testfail)
            System.out.println("ERROR IN USERCATALOG");
        else
            System.out.println("USERCATALOG WORKS");

    }

    public void testUserClass(Boolean print) {
        Boolean all_good = true;
        User user_1 = new User("John", "JohnMan", "123fe", "111-111-1111", "Johnmail");
        if(print)
            System.out.println("User\n" + user_1.getUsername() + " : " + user_1.getName());
        loadUUID(user_1);
        if(print)
            System.out.println("Invited Projects:");
        if(print)
            printUUID(user_1.getInvitedProjects());
        if(print)
            System.out.println("Accepting First 2 Projects");
        user_1.AcceptInvite(0);
        user_1.AcceptInvite(0);
        if(print) {
            System.out.println("Current Projects:");
            printUUID(user_1.getCurrentProjects());
            System.out.println("Invited Projects:");
            printUUID(user_1.getInvitedProjects());
            System.out.println("Getting Points for Project 1");
            System.out.println(user_1.getProjectPoints(user_1.getCurrentProjects().get(0)));
            System.out.println("Adding 5 Points");
            user_1.addPoints(user_1.getCurrentProjects().get(0), 5);
            System.out.println(user_1.getProjectPoints(user_1.getCurrentProjects().get(0)));
            System.out.println("Removing 7 Points");
            user_1.removePoints(user_1.getCurrentProjects().get(0), 7);
            System.out.println(user_1.getProjectPoints(user_1.getCurrentProjects().get(0)));
            System.out.println("Removing 1st Project");
        }
        user_1.RemoveProject(0);
        if (print) {
            if (user_1.getCurrentProjects().size() == 1) {
                System.out.println("Correct Output: Remove Project");
            }
            else {
                System.out.println("Incorrect Output: Remove Project");
                all_good = false;
            }
            System.out.println("Current Projects");
            printUUID(user_1.getCurrentProjects());
            System.out.println("Invited Projects:");
            printUUID(user_1.getInvitedProjects());
            System.out.println("Changing Password, case false");
            if (!(user_1.changePassword("JohnMans", "123ff"))) 
                System.out.println("Correct Outut: Wrong Password");
            else {
                all_good = false;
                System.out.println("Incorrect Output: Wrong Password");
            }
                
        }
        if (user_1.changePassword("JohnMan", "123ff")) {
            if (print) 
                System.out.println("Correct Output: New Password");
        }
        else {
            if(print)
                System.out.println("Incorrect Output: New Password\nTesting Login");
            all_good = false;
        }
            
        if(user_1.login("JohnMan", "123ff")) 
            if (print) 
                System.out.println("Correct Output: Login");
        else {
            if (print) 
                System.out.println("Incorrect Output: Login");
            all_good = false;
        }

        if (all_good)
            System.out.println("USER HAS PASSES TESTCASES");
        else 
            System.out.println("USER HAS ERROR");        

    }

    public void loadUUID(User user_1) {
        for(int i = 0; i < 4; i++) {
            user_1.addInvite(UUID.randomUUID());
        }
    }

    public void printUUID(ArrayList<UUID> projects) {
        for (int i = 0; i < projects.size(); i++) {
            System.out.println(projects.get(i));
        }
    }




    
    public static void main(String[] args) {
        TestcasesUsers t1 = new TestcasesUsers();
        
    }
    

    

}
