import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;

import java.util.UUID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;



public class ProjectCatalogTestCases {
    private ProjectCatalog test = ProjectCatalog.getInstance();
    private ArrayList<Project> pTest;
    
    @BeforeEach
    public void setUp(){
        test = ProjectCatalog.getInstance();
        pTest = new ArrayList<>();
    }

    @AfterEach
    public void tearDown(){
        pTest = null;
        test = null;
    }

    @Test 
    void alreadyMadeProject()
    {
        History h = new History();
        UUID r = UUID.randomUUID();
        Project newProject = new Project(r, "testName", "testType", Layout.SCRUM, new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Column>(), h);
        test.addProject(newProject);
        boolean btest = false;
        if(ProjectCatalog.projects.contains(newProject))
        {
            btest = true;
        }
        assertTrue(btest);

    }

    @Test 
    void addProjectByName()
    {
        boolean tester = false;
        test.addProject("Jerry");
        if(ProjectCatalog.projects != null) {
            tester = true;
        }
        assertTrue(tester);
    }

    @Test
    void addingBlankProject() {
        boolean tester = false;
        test.addProject("");
        if(ProjectCatalog.projects != null) {
            tester = true;
        }
        assertTrue(tester);
    }

    @Test
    void removingProject() {
        History h = new History();
        UUID r = UUID.randomUUID();
        Project newProject = new Project(r, "", "", Layout.SCRUM, new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Column>(), h);
        test.addProject(newProject);
        boolean btest = true;
        ProjectCatalog.removeProject(r);
        if(ProjectCatalog.projects.contains(newProject))
        {
            btest = false;
        }
        assertTrue(btest);
    }
    @Test
    void removingWithInvalidUUID() {
        History h = new History();
        UUID r = UUID.randomUUID();
        UUID s = UUID.randomUUID();
        Project newProject = new Project(r, "", "", Layout.SCRUM, new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Column>(), h);
        test.addProject(newProject);
        boolean btest = false;
        ProjectCatalog.removeProject(s);
        if(ProjectCatalog.projects.contains(newProject))
        {
            btest = true;
        }
        assertTrue(btest);
    }
    @Test
    void gettingWithValidUUID() {
        History h = new History();
        UUID r = UUID.randomUUID();
        Project newProject = new Project(r, "", "", Layout.SCRUM, new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Column>(), h);
        test.addProject(newProject);
        boolean btest = false;
        if(test.getProject(r) == newProject)
        {
            btest = true;
        }
        assertTrue(btest);
    }
    @Test
    void gettingWithInvalidUUID() {
        History h = new History();
        UUID r = UUID.randomUUID();
        UUID s = UUID.randomUUID();
        Project newProject = new Project(r, "", "", Layout.SCRUM, new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Column>(), h);
        test.addProject(newProject);
        boolean btest = false;
        ProjectCatalog.removeProject(s);
        if(test.getProject(s) == null)
        {
            btest = true;
        }
        assertTrue(btest);
    }
    void readingInAnEmptyUUIDArray() {
        ArrayList<UUID> te = new ArrayList<>();
        ArrayList<Project> projects;
        projects = ProjectCatalog.readUserProjectUUID(te);
        boolean btest = false;
        if(projects == null)
        {
            btest = true;
        }
        assertTrue(btest);
    }

    


}
