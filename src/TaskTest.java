import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class TaskTest {
    
    @Test 
    public void TestAddTask(){
        ToDo todo = new ToDo(false, "new todo", UUID.randomUUID());
        Task tasklist = new Task();
        Boolean bool = tasklist.addToDo(todo);
        assertEquals(true,bool);
    }

    @Test
    public void TestRemoveTask(){
        UUID id = UUID.randomUUID();
        ToDo todo = new ToDo(false, "new todo", id);
        Task tasklist = new Task();
        tasklist.addToDo(todo);
        Boolean bool = tasklist.removeToDo(id);
        assertEquals(true,bool); 
    }

}
