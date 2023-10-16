import java.util.ArrayList;
import java.util.UUID;



/**
 * @author Rafael Prignano
 */


public class Project {
    private UUID id;
    private String name;
    private String type;
    private Layout layout;
    private ArrayList<Task> ongoingTasks;
    private ArrayList<Task> completedTasks;
    private ArrayList<User> leaderboard;
    private ArrayList<Column> ColumnList;
    private ArrayList<Task> TaskList;

    public Project(String name , String type){

    }

    public boolean addTask(Task task){
        return true; //placeholder

    }

    public boolean removeTask(Task task){
        return true; //placeholder

    }

    public boolean addColumn(Column Column){
        return true; //placeholder

    }

    public boolean addTaskToColumn(Task task, Column column){
        return true; //placeholder

    }

    public boolean removeTaskToColumn(Task task, Column column){
        return true; //placeholder

    }

    public ArrayList<column> viewColumn(String name){

    }

    public ArrayList<Task> viewColumnTask(String column , String task){

    }

    public Task viewUnassignedTask(String task){

    }

    public void setProjectStatus(Task task , boolean status){

    }

    public boolean addToLeaderboard(User user){
        
    }








}