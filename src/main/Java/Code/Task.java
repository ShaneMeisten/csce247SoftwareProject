package Code;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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
    private String assignee;
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

        this.id = id;
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

    public Task(String title, double weight, double completionTime,String categories, String assignee,Date dueDate ,String description) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
    }
    public Task(String title, String description, String asignee, User Author){
        this.title = title;
        this.description = description;
        this.assignee = asignee;
        this.author = Author;
    }

    //only used for testing, will not be used for the software as a whole
    public Task(){
        this.toDoList = new ArrayList<ToDo>();
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
    
    public String getAssignee(){
        return this.assignee;
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

    public void setCategories(String categories){
        String temp = "";
        int count = 0;
        while(true){
           if(categories.contentEquals(" ")){
                break;
           }
           temp = categories.substring(count, categories.indexOf(','));
           if(temp.contentEquals(" "))
               break;
           else
               this.categories.add(temp);
           count = temp.length() + 2;

        }
    }

    public void setAssignee(String asignee){
        this.assignee = asignee;
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

    public void setComplete(){
        this.status = true;
    }
    //UUID author, String name, String description
    
    public void addComment(String description, String name, UUID id) {
        this.comment.add(new Comment (id, name, description));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Comment> getComment() {
        return comment;
    }
}
