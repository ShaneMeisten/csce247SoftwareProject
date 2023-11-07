import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {
    
    @BeforeClass
    public void oneTimeSetup(){

    }

    @AfterClass
    public void oneTimeTearDown(){

    }

    @BeforeEach
    public static void setup(){

    }

    @AfterEach
    public static void tearDown(){

    }

    @Test 
    public void TestAddTask(){
        ToDo todo = new ToDo(false, "new todo", UUID.randomUUID());
        Task tasklist = new Task();
        Boolean bool = tasklist.addToDo(todo);
        assertEquals(true,bool);
    }

}
