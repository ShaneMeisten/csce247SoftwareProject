import java.util.ArrayList;
import java.util.UUID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestcasesFacade {
    private Facade facade;
    private User joe, mat, zoe, dave;

    @BeforeEach
    public void setUp() throws Exception {
    this.facade = new Facade();
    joe = new User("Joe", "JoeMars", "cool4321", "111-111-1113", "Mars@mail.com");
    mat = new User("Mat", "Matthew", "cool5321", "111-111-1114", "mat@mail.com");
    zoe = new User("Zoe", "ZoeZoe1", "cool4321", "111-111-1115", "zoe@mail.com");
    dave = new User("dave", "davecool", "davecool321", "111-111-1116", "dave@mail.com");
    facade.createUser(joe);
    facade.createUser(mat);
    facade.createUser(zoe);
    facade.createUser(dave);
    facade.createProject("Project_One");
    facade.createProject("Project_Two");
    }

    @Test
    void testLoginNullUsername() {
        assertFalse(facade.login(null, zoe.getPassword()));
    }

    @Test
    void testLoginNullPassword() {
        assertFalse(facade.login(zoe.getUsername(), null));
    }

    @Test
    void testLoginNullWithCurrentUser() {
        facade.login(zoe.getUsername(), null);
        assertTrue(facade.getCurrentUser() == null);
    }

    @Test
    void testLoginIncorrectLogin() {
        assertFalse(facade.login("123","123"));
    }

    @Test
    void testLoginCorrectLogin() {
        assertFalse(facade.login(zoe.getUsername(),zoe.getPassword()));
    }

    @Test
    void TestGetUserCurrentProjectsNoCurrentUser() {
        boolean ifTrue = false;
        if(facade.getUserCurrentProjects() == null) {
            ifTrue = true;
        }
        assertTrue(ifTrue);
    }

    @Test
    void TestGetUserInvitedProjectsNoCurrentUser() {
        boolean ifTrue = false;
        if(facade.getUserCurrentProjects() == null) {
            ifTrue = true;
        }
        assertTrue(ifTrue);
    }

    @Test 
    void TestAcceptInviteNoInvites() {
        facade.login(zoe.getUsername(), zoe.getPassword());
        assertFalse(facade.AcceptInvite(4));
    }
    @Test 
    void TestAcceptInvite() {
        facade.login(zoe.getUsername(), zoe.getPassword());
        facade.InviteUserToProject(zoe.getUUID(), facade.getProject(0).getUUID());
        assertTrue(facade.AcceptInvite(0));
    }
    @Test 
    void TestRemoveProject() {
        facade.login(zoe.getUsername(), zoe.getPassword());
        facade.InviteUserToProject(zoe.getUUID(), facade.getProject(0).getUUID());
        facade.AcceptInvite(0);
        assertTrue(facade.RemoveProject(0));
    }
    @Test 
    void TestRemoveProjectDoesNotExist() {
        facade.login(zoe.getUsername(), zoe.getPassword());
        facade.InviteUserToProject(zoe.getUUID(), facade.getProject(0).getUUID());
        assertFalse(facade.RemoveProject(0));
    }

    @Test 
    void setCurrentProjectNoCurrentUser() {
        assertFalse(facade.setCurrentProject(0));
    }
    
    @Test 
    void setCurrentProjectNoProject() {
        facade.login(zoe.getUsername(), zoe.getPassword());
        facade.InviteUserToProject(zoe.getUUID(), facade.getProject(0).getUUID());
        facade.AcceptInvite(0);
        assertFalse(facade.RemoveProject(5));
    }

    @Test 
    void setCurrentProject() {
        facade.login(zoe.getUsername(), zoe.getPassword());
        facade.InviteUserToProject(zoe.getUUID(), facade.getProject(0).getUUID());
        facade.AcceptInvite(0);
        assertFalse(facade.RemoveProject(0));
    }

    /*
     * @author Cameron Reyes
     * 
     * Remaining not implemented:
     *  seeUsersInCurrentProject
     *  removeUserFromCurrentProject
     *  addUserToCurrentProject
     */

}
