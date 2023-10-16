import java.util.ArrayList;
import java.util.UUID;

public class ToDoList {

    private ArrayList<ToDo> toDoList;

    public ToDoList(){
        toDoList = new ArrayList<>();
}
    public void ToDoLIst(ArrayList<ToDo> toDoList){
        this.toDoList = toDoList;
    }

    public boolean addToDo(){
        //adding code later
        return true;

    }

    public ToDo removeToDo(UUID id){
        //adding code later
        return null;
    }

    public ToDo getToDo(UUID id){
        //adding code later
        return null;
    }
}
