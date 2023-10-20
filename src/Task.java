import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

/**
 * 
 * @author Shane Meisten
 * 
 */

public class Task {
    
    private UUID id;
    private String title;
    private String description;
    private Date dueDate;
    private double weight;
    private ArrayList<String> categories;
    private ArrayList<Comment> commentThread;
    private ArrayList<Task> toDoList;
    private boolean status;
    private double completionTime;
    private User assignedUser;
    private User author;

    public Task(UUID id, String title, String description, Date dueDate, 
    double weight, ArrayList<String> categories, ArrayList<Comment> commentThread,
    ArrayList<Task> toDoList, boolean status, double compleitonTime, User assignedUser, User author){

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.weight = weight;
        this.categories = categories;
        this.commentThread = commentThread;
        this.toDoList = toDoList;
        this.status = status;
        this.completionTime = completionTime;
        this.assignedUser = assignedUser;
        this.author = author;
        }

    public UUID getID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public Date getDueData(){
        return this.dueDate;
    }

    public double getWeight(){
        return this.weight;
    }

    public ArrayList<String> getCategories(){
        return categories;
    }

    public ArrayList<Comment> getCommentThread(){
        return this.commentThread;
    }

    public ArrayList<Task> getToDoList(){
        return this.toDoList;
    }

     public boolean getStatus(){
        return this.status;
    }

     public double getCompletionTime(){
        return this.completionTime;
    }

     public User getAssignedUser(){
        return this.assignedUser;
    }

     public User getAuthor(){
        return this.author;
    }

    public void setWeight(double weight){
        if(weight < 0 ){
            this.weight = 0;
        }else{
            this.weight = weight;
        }
    }

    public void setCompletionTime(double completionTime){
        if(completionTime < 0){
            this.completionTime = 0;
        }else{
            this.completionTime = completionTime;
        }
    }

    public boolean addToDo(Task task){
        if(task != null){
        toDoList.add(task);
        return true;
        }
        return false;
    }

    public Task removeToDo(UUID id){
        for(Task toDo : toDoList){
                if(toDo.getID() == id){
                    return toDo;
                }
        }
        return null;
    }

    public Task getToDo(UUID id){
        for(Task toDo : toDoList){
                if(toDo.getID() == id){
                    return toDo;
                }
        }
        return null;
    } 
}
