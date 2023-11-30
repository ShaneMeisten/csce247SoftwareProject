import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * @author Cameron
 */

public class TestcasesUsers {
    private UserCatalog userCatalog;
    private User joe, mat, zoe, dave;
    private UUID testID;

    @BeforeEach
    public void setUp() throws Exception {
        this.userCatalog = UserCatalog.getInstance();
        joe = new User("Joe", "JoeMars", "cool4321", "111-111-1113", "Mars@mail.com");
        mat = new User("Mat", "Matthew", "cool5321", "111-111-1114", "mat@mail.com");
        zoe = new User("Zoe", "ZoeZoe1", "cool4321", "111-111-1115", "zoe@mail.com");
        dave = new User("dave", "davecool", "davecool321", "111-111-1116", "dave@mail.com");
        userCatalog.addUser(joe);
        userCatalog.addUser(mat);
        userCatalog.addUser(zoe);
        userCatalog.addUser(dave);
       
        testID = UUID.randomUUID();
        userCatalog.inviteUserToProject(zoe.getUUID(), testID);
        zoe.becomeAdmin();
        userCatalog.inviteUserToProject(dave.getUUID(), testID);
        dave.AcceptInvite(0);
        zoe.AcceptInvite(0);
    }
    
    @Test
    void testLoadUsersWithNullUser() {
        ArrayList<User> toLoadUsers = new ArrayList<>();
        User sam = new User("Sam", "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        User nullUser = null;
        Boolean ifLoaded = userCatalog.loadUser(toLoadUsers);
        assertFalse(ifLoaded);
    }

    @Test
    void testLoadUsersWithUserContainsNull() {
        ArrayList<User> toLoadUsers = new ArrayList<>();
        User invalidSam = new User(null, "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        Boolean ifLoaded = userCatalog.loadUser(toLoadUsers);
        assertFalse(ifLoaded);
    }

    @Test
    void testLoadNoUsers() {
        ArrayList<User> toLoadUsers = new ArrayList<>();
        assertTrue(userCatalog.loadUser(toLoadUsers));
    }

    @Test
    void testLoadNullArrayToUserCatalog() {
        ArrayList<User> toLoadUsers = null;
        assertTrue(userCatalog.loadUser(toLoadUsers));
    }

    @Test
    void testAddUserWithLessThen7Username() {
        User invalidSam = new User("Sam", "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        assertFalse(userCatalog.addUser(invalidSam));
    }

    @Test
    void testAddUserWith7orMoreUsername() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        assertTrue(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserWithLessThen8Password() {
        User invalidSam = new User("Sam", "Sammuel", "cool", "111-111-1112", "Sammy@mail.com");
        assertFalse(userCatalog.addUser(invalidSam));
    }

    @Test
    void testAddUserWith7orMorePassword() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        assertTrue(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserDuplicateUsername() {
        User Sam = new User("Sam", "JoeMars", "cool6321", "111-111-1112", "Sammy@mail.com");
        assertFalse(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserUniqueUsername() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        assertFalse(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserDuplicatePhone() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1111", "Sammy@mail.com");
        assertFalse(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserUniquePhone() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        assertTrue(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserValidPhone() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1111", "Sammy@mail.com");
        assertTrue(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserInvalidPhone() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111", "Sammy@mail.com");
        assertFalse(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserDuplicateEmail() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1111", "zoe@mail.com");
        assertFalse(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserUniqueEmail() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1112", "Sammy@mail.com");
        assertTrue(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserValidEmail() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111-111-1111", "Sammy@mail.com");
        assertTrue(userCatalog.addUser(Sam));
    }

    @Test
    void testAddUserInvalidEmail() {
        User Sam = new User("Sam", "Sammuel", "cool6321", "111", "Sammy");
        assertFalse(userCatalog.addUser(Sam));
    }

    @Test
    void testInviteUserNotInCatalog() {
        assertFalse(userCatalog.inviteUserToProject(UUID.randomUUID(), testID));
    }
    @Test
    void testInviteUserInCatalog() {
        assertTrue(userCatalog.inviteUserToProject(mat.getUUID(), testID));
    }
    @Test
    void testInviteUserToNullProject() {
        assertFalse(userCatalog.inviteUserToProject(UUID.randomUUID(), null));
    }
    @Test
    void testInviteNullUserToProject() {
        assertTrue(userCatalog.inviteUserToProject(null, testID));
    }
    //removeUser
    @Test
    void testRemoveUserNullMainUser() {
        assertFalse(userCatalog.removeUser(null, testID));
    }
    @Test
    void testRemoveUserNullRemove() {
        assertFalse(userCatalog.removeUser(zoe, null));
    }
    @Test
    void testRemoveUserNoAdmin() {
        assertFalse(userCatalog.removeUser(mat, zoe.getUUID()));
    }
    @Test
    void testRemoveUserAdmin() {
        assertTrue(userCatalog.removeUser(zoe, mat.getUUID()));
    }

    //retrieveUser
    @Test
    void testCheckNullUsername() {
        boolean test = false;
        if (userCatalog.retrieveUser(null, "cool4321") == null){
            test = true;
        } 
        assertTrue(test);
    }

    @Test
    void testCheckNullPassword() {
        boolean test = false;
        if (userCatalog.retrieveUser(zoe.getUsername(), null) == null){
            test = true;
        } 
        assertTrue(test);
    }

    @Test
    void testCheckFalsePassword() {
        boolean test = false;
        if (userCatalog.retrieveUser(zoe.getUsername(), "123") == null){
            test = true;
        } 
        assertTrue(test);
    }

    @Test
    void testCheckFalseUsername() {
        boolean test = false;
        if (userCatalog.retrieveUser("123", zoe.getUsername()) == null){
            test = true;
        } 
        assertTrue(test);
    }

    @Test
    void testCheckCorectRetriveUser() {
        boolean test = false;
        if (userCatalog.retrieveUser(zoe.getUsername(), zoe.getUsername()) == null){
            test = true;
        } 
        assertFalse(test);
    }

    @Test
    void testCheckUserInvalidUUID() {
        boolean test = false;
        if (userCatalog.getUser(testID) == null){
            test = true;
        } 
        assertTrue(test);
    }

    @Test
    void testCheckUserNullUUID() {
        boolean test = false;
        if (userCatalog.getUser(null) == null){
            test = true;
        } 
        assertTrue(test);
    }

    @Test
    void testCheckUsersInProject() {
        boolean test = false;
        if (userCatalog.getLeaderboard(testID).size() != 2){
            test = true;
        } 
        assertFalse(test);
    }

    @Test
    void testCheckUsersInNullProject() {
        boolean test = false;
        if (userCatalog.getLeaderboard(null).size() != 2){
            test = true;
        } 
        assertTrue(test);
    }


    
}
