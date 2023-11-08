import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class FacadeTest {
    @Test
    public void TestAddTaskToTaskList(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Facade facade = new Facade();
        Boolean bool = facade.addTaskToTaskList(task);
        assertEquals(true, bool);
    }
    @Test
    public void addColumnToCurrentProject(){
        Facade facade = new Facade();
        Boolean bool = facade.addColumnToCurrentProject("title");
        assertEquals(true, bool); 
    } 

    @Test 
    public void TestViewColumn(){
        Column column = new Column("title");
        Facade facade = new Facade();
        facade.addColumnToCurrentProject(column.getTitle());
        Column bool = facade.viewColumn(column.getTitle());
        assertEquals(column, bool);
    }
    @Test 
    public void TestUnassignedTask(){
        Task task = new Task("title", "this is description", "john doe");
        Project project = new Project();
        project.addTask(task);
        Facade facade = new Facade();
        Task test = facade.viewUnassingedTask(task.getTitle());
        assertEquals(task,test);
    }

    @Test
    public void TestViewColumns(){
        Column column = new Column("title");
        Column column2 = new Column("title2");
        ArrayList<Column> Columns = new ArrayList<Column>();
        Columns.add(column);
        Columns.add(column2);
        Facade facade = new Facade();
        facade.addColumnToCurrentProject(column.getTitle());
        facade.addColumnToCurrentProject(column2.getTitle());
        assertEquals(Columns,facade.viewColumns());
    }
}
