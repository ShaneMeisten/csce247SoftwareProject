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
        this.id = id;
        this.name = name;
        this.type = type;
        this.layout = layout;
        this.ongoingTasks = ongoingTasks;
        this.completedTasks = completedTasks;
        this.leaderboard = leaderboard;
        this.ColumnList = ColumnList;
        this.TaskList = TaskList;
    }

    public UUID getUUID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public Layout getLayout(){
        return layout;
    }

  




    public boolean addTask(Task task){
        if (!TaskList.contains(task)) {
            TaskList.add(task);
            return true;
        } else {
            return false; 
        }

    }

    public boolean removeTask(Task task){
        if (TaskList.contains(task)) {
            TaskList.remove(task);
            return true; 
        } else {
            return false; 
        }
    }

    public boolean addColumn(Column Column){
        if (ColumnList.contains(Column)) {
            return false; 
        } else {
            ColumnList.add(Column);
            return true; 
        }

    }

    public boolean addTaskToColumn(Task task, Column column){
        if (ColumnList.contains(column)) { 
            if (column.addTask(task)) {
                return true; 
            } else {
                return false; 
            }
        } else {
            return false; 
        }
        

    }

    public boolean removeTaskToColumn(Task task, Column column){
    if (ColumnList.contains(column)) {
        if (column.removeTask(task)) {
            return true; 
        } else {
            return false; 
        }
    } else {
        return false;
    }
}

    public ArrayList<Column> viewColumn(String name){
        // insert code
        return new ArrayList<>();
    }

    public ArrayList<Task> viewColumnTask(String column , String task){
        //insert code
        return new ArrayList<>();
    }

    public Task viewUnassignedTask(String task){
       
       // insert code 
        return null;
    }

    public void setProjectStatus(Task task , boolean status){
        for (Task projectTask : TaskList) {
            if (projectTask.equals(task)) {
                projectTask.setStatus(status); 
                break; 
            }
        }
    }

    public boolean addToLeaderboard(User user){
        if (!leaderboard.contains(user)) {
            leaderboard.add(user);
            return true; 
        } else {
            return false;
        }
    }






    

}
