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
    private ArrayList<ToDo> toDoList;
    private boolean status;
    private double completionTime;
    private User assignedUser;
    private User author;
    private String asignee;
    private ArrayList<Comment> comment = new ArrayList<Comment>();

    /**
     * 
     * Constructor for the Task class, used for the Json file
     * @param id
     * @param title
     * @param description
     * @param dueDate
     * @param weight
     * @param categories
     * @param commentThread
     * @param toDoList
     * @param status
     * @param completionTime
     * @param assignedUser
     * @param author
     */
    public Task(UUID id, String title, String description, Date dueDate, 
    double weight, ArrayList<String> categories, ArrayList<Comment> commentThread,
    ArrayList<ToDo> toDoList, boolean status, double completionTime, User assignedUser, User author){

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.setWeight(weight);
        this.categories = categories;
        this.commentThread = commentThread;
        this.toDoList = toDoList;
        this.status = status;
        this.setCompletionTime(completionTime);
        this.assignedUser = assignedUser;
        this.author = author;
        }
    public Task(String title, String description, String asignee){
        this.title = title;
        this.description = description;
        this.asignee = asignee;
    }

    /*
     * All accessors for the class
     */
    public UUID getID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public Date getDueDate(){
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

    public ArrayList<ToDo> getToDoList(){
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
    
    public String getAsignee(){
        return this.asignee;
    }

    public void setWeight(double weight){
        if(weight < 0 ){
            this.weight = 0;
        }else{
            this.weight = weight;
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCompletionTime(double completionTime){
        if(completionTime < 0 ){
            this.completionTime = 0;
        }else{
            this.completionTime = completionTime;
        }
    }

    public void setAssignee(String asignee){
        this.asignee = asignee;
    }

    /**
     * adds to the ToDo list
     * @param todo
     * @return a boolean based on if it can add to the List
     */
    public boolean addToDo(ToDo todo){
        if(todo != null){
        toDoList.add(todo);
        return true;
        }
        return false;
    }
    /**
     * removes a ToDo item 
     * @param id
     * @return a boolean based on if there is a ToDo object that is matches the id
     */
    public boolean removeToDo(UUID id){
        for(ToDo toDo : toDoList){
                if(toDo.getID() == id){
                    return true;
                }
        }
        return false;
    }
    /**
     * gets a ToDo object 
     * @param id
     * @return the ToDo object that matches the id in the parameter
     */
    public ToDo getToDo(UUID id){
        for(ToDo toDo : toDoList){
                if(toDo.getID() == id){
                    return toDo;
                }
        }
        return null;
    }

<<<<<<< HEAD
    public void setComplete(){
        this.status = true;
=======
    //UUID author, String name, String description

    public void addComment(String description, String name, UUID id) {
        this.comment.add(new Comment (id, name, description));
    }

    public ArrayList<Comment> getComment() {
        return comment;
>>>>>>> ccd0f3c495fc1a1aa258e9e028ad57a82ad67062
    }
}
