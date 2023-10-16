import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;


public class Task {
    
    private UUID id;
    private String title;
    private String description;
    private Date dueDate;
    private double weight;
    private ArrayList<String> categories;
    private ArrayList<Comment> commentThread;
    private ToDoList toDoList;
    private boolean status;
    private double completionTime;
    private User assignedUser;
    private User author;

    public Task(UUid id, String title, String description, Date dueDate, 
    double weight, ArrayList<String> categories, ArrayList<Comment> commentThread,
    ToDoList toDoList, boolean status, double compleitonTime, User assignedUser, User author){

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
        //adding code later
        return categories;
    }

    public ArrayList<Comment> getCommentThread(){
        //adding code later
        return this.commentThread;
    }

    public ToDoList getToDoList(){
        //adding code later
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
}
