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



public class projectTestCases {
    private Project newProject;
    
    @BeforeEach
    public void setUp(){
        History h = new History();
        UUID r = UUID.randomUUID();
        newProject = new Project(r, "testName", "testType", Layout.SCRUM, new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Column>(), h);
    }

    @AfterEach
    public void tearDown(){
        newProject = null;
    }
    @Test 
    void addingTask()
    {   
        boolean btest = false;
        if(newProject.addTask(new Task("Test", "Testing Stuff", "Jerry")) == true)
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void addingRepeatTask()
    {   
        boolean btest = false;
        newProject.addTask(new Task("Test", "Testing Stuff", "Jerry"));
        if(newProject.addTask(new Task("Test", "Testing Stuff", "Jerry")) == false)
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void removingTask()
    {   
        boolean btest = false;
        Task t = new Task("Test", "Testing Stuff", "Jerry");
        newProject.addTask(t);
        if(newProject.removeTask(t) == true)
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void removingTaskNotThere()
    {   
        boolean btest = false;
        if(newProject.removeTask(new Task("Test", "Testing Stuff", "Jerry")) == false)
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void addingColumn()
    {   
        boolean btest = false;
        Column tes = new Column("TestColumn");
        newProject.addColumn(tes);
        if(newProject.getColumnList().get(0) == tes)
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void addingRepeatColumn()
    {   
        boolean btest = false;
        Column tes = new Column("TestColumn");
        newProject.addColumn(tes);
        if(newProject.addColumn(tes) == false)
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void addingTaskToColumn()
    {   
        boolean btest = false;
        Column tes = new Column("TestColumn");
        newProject.addColumn(tes);
        Task s = new Task("String", "Test Task", "Jerry");
        newProject.addTaskToColumn(s, tes);
        if(newProject.getColumnList().get(0).getTasks().contains(s))
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void addingEmptyTaskToColumn()
    {   
        boolean btest = false;
        Column tes = new Column("TestColumn");
        newProject.addColumn(tes);
        Task s = new Task(null, null, null);
        newProject.addTaskToColumn(s, tes);
        if(newProject.getColumnList().get(0).getTasks().contains(s))
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void addingRepeatTaskToColumn()
    {   
        boolean btest = false;
        Column tes = new Column("TestColumn");
        newProject.addColumn(tes);
        Task s = new Task("Sell", "More", "rice");
        newProject.addTaskToColumn(s, tes);
        if(newProject.addTaskToColumn(s, tes) == false)
        {
            btest = true;
        }
        assertTrue(btest);

    }
    @Test
    void settingProjectStatusToTrue()
    {   
        boolean btest = true;
        Task s = new Task("Sell", "More", "rice");
        newProject.addTask(s);
        newProject.setProjectStatus(s, true);
        if(newProject.getOngoingTasks().contains(s))
        {
            btest = false;
        }
        assertTrue(btest);

    }
    @Test
    void settingProjectStatusToFalse()
    {   
        boolean btest = false;
        Task s = new Task("Sell", "More", "rice");
        newProject.addTask(s);
        newProject.setProjectStatus(s, false);
        if(newProject.getOngoingTasks() != null)
        {
            btest = true;
        }
        assertTrue(btest);

    }











    


}
