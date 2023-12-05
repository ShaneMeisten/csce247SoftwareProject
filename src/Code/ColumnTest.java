package Code;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ColumnTest {
    @Test
    public void TestAddTask(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Column column = new Column("title");
        Boolean bool = column.addTask(task);
        assertEquals(true,bool);
    }

    @Test
    public void TestAddTask2(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Column column = new Column("title");
        Boolean bool = column.addTask(task);
        assertEquals(false,bool);
    }

    @Test
    public void TestRemoveTask(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Column column = new Column("title");
        column.addTask(task);
        Boolean bool = column.removeTask(task);
        assertEquals(true,bool);    
    }

    @Test
    public void TestRemoveTask2(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Column column = new Column("title");
        column.addTask(task);
        Boolean bool = column.removeTask(task);
        assertEquals(false,bool);    
    }

    @Test
    public void TestViewTask(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Task task2 = new Task("title2", "this is a title a second time", "Joe Random a second timne");
        Column column = new Column("title");
        column.addTask(task);
        column.addTask(task2);
        Task Task = column.viewTask(task);
        assertEquals(task,Task);
    }

    @Test
    public void TestViewTask2(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Task task2 = new Task("title2", "this is a title a second time", "Joe Random a second timne");
        Column column = new Column("title");
        column.addTask(task);
        column.addTask(task2);
        Task Task = column.viewTask(task2);
        assertEquals(task2,Task);
    }
}
