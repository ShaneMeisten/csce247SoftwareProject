import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;

/**
 * 
 * @author Shane Meisten
 * 
 */

public class Column {
    private UUID id;
    private String title;
    private double weight;
    private boolean status;
    private Date completionTime;
    private Date createdTime;
    private User author;
    private ArrayList<Task> TaskList= new ArrayList<>();
    
    /**
     * Constructor used for the Json file
     * @param id
     * @param title
     * @param weight
     * @param status
     * @param completionTime
     * @param createdTime
     * @param author
     * @param TaskList
     */
    public Column(UUID id, String title, double weight, boolean status, Date completionTime,
                  Date createdTime, User author, ArrayList<Task> TaskList){
        this.title = title;
        this.id = id;
        this.setWeight(weight);
        this.setStatus(status);
        this.completionTime = completionTime;
        this.createdTime = createdTime;
        this.author = author;
        this.TaskList = TaskList;
        
    }

    /**
     * adds a task to the TaskList
     * @param task
     * @return a boolean based on if it can add to the List
     */
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

    public Task viewTask(Task task){
        if(TaskList.contains(task)){
        return task;
        }else{
            return null;
        }
    }

    public UUID getUUID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public double getWeight(){
        return this.weight;
    }

    public boolean getStatus(){
        return this.status;
    }

    public Date getCompletionTime(){
        return this.completionTime;
    }

    public Date getCreatedTime(){
        return this.createdTime;
    }

    public User getAuthor(){
        return this.author;
    }

    public ArrayList<Task> getTasks() {
        return this.TaskList;
    }

    public void setStatus(boolean status){
        if(status !=true && status !=false){
            this.status = false;
        }else{
            this.status = status;
        }
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
