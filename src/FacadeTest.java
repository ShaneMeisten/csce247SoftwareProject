import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class FacadeTest {
    @Test
    public void TestAddTaskToTaskList(){
        Task task = new Task("title", "this is a title", "Joe Random");
        Facade facade = new Facade();
        Boolean bool = facade.addTaskToTaskList(task);
        assertEquals(true, bool);
    }
}
