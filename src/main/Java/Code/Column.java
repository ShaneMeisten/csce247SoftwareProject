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
    private double completionTime;
    private Date createdTime;
    private User author;
    private ArrayList<Task> TaskList= new ArrayList<Task>();
    
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
    public Column(UUID id, String title, double weight, boolean status, double completionTime,
                  Date createdTime, User author, ArrayList<Task> TaskList){
        this.title = title;
        this.id = id;
        this.setWeight(weight);
        this.setStatus(status);
        this.setCompletionTime(completionTime);
        this.createdTime = createdTime;
        this.author = author;
        this.TaskList = TaskList;
        
    }

    public Column(String title){
       this.title = title; 
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

    /**
     * removes a specfic task based on the task that is passed through as an argument
     * @param task
     * @return a boolean value based on if it was able to remove it
     */
    public boolean removeTask(Task task){
        if(TaskList.remove(task)){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * This is used to view a specfic task based on the task that is passed through as an argument
     * @param task
     * @return the task that is being looked for if it is found in the Array List, otherwise it returns null
     */
    public Task viewTask(Task task){
        if(TaskList.contains(task)){
            return task;
        }else{
            return null;
        }
    }

    /*
     * These are all the accessors of the column class
     */
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

    public double getCompletionTime(){
        return this.completionTime;
    }

    public Date getCreatedTime(){
        return this.createdTime;
    }

    public User getAuthor(){
        return this.author;
    }

    /**
     * This sets the status of a specific task in a column
     * @param status
     */
    
    public ArrayList<Task> getTasks() {
        return this.TaskList;
    }

    /**
     * This sets the status of a specific task in a column
     * @param status
     */
    public void setStatus(boolean status){
        if(status !=true && status !=false){
            this.status = false;
        }else{
            this.status = status;
        }
    }
    /**
     * This sets the weight of a specific task in a column
     * @param weight
     */
    public void setWeight(double weight){
        if(weight > 0 ){
            this.weight = weight;
        }else{
            this.weight = 0;
        }
    }

    public void setCompletionTime(double completionTime){
        if(completionTime < 0 ){
            this.completionTime = 0;
        }else{
            this.completionTime = completionTime;
        }
    }
}
