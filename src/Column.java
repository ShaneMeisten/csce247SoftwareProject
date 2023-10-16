import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;

public class Column {
    private UUID id;
    private String title;
    private double weight;
    private boolean status;
    private Date completionTime;
    private Date createdTime;
    private User author;
    private ArrayList<Task> TaskList;

    public Column(String title){
        this.title = title;
        TaskList = new ArrayList<>();
    }

    public boolean addTask(Task task){
        if(TaskList.add(task)){
            return true;
        }else{
            return false;
        }
    }

    public boolean removeTask(Task task){
        if(TaskList.remove(task)){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Task> viewTask(){
        //adding code later
        return null;
    }

    public UUID getUUID(){
        //adding code later
        return null;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean setCompletionTime(){
        //adding code later
        return true;
    }

    public boolean setWeight(double weight){
        if(weight > 0 ){
            this.weight = weight;
            return true;
        }else{
            return false;
        }
    }
}
