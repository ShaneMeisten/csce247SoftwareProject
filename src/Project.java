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
    private ArrayList<Task> completedTasks;
    private ArrayList<Task> ongoingTasks;
    private ArrayList<Column> columnList;
    private History history;

    public Project(UUID id, String name, String type, Layout layout,
                    ArrayList<Task> completedTasks, ArrayList<Task> ongoingTasks, 
                    ArrayList<Column> columnList, History history){
        this.id = id;
        this.name = name;
        this.type = type;
        this.layout = layout;
        this.ongoingTasks = ongoingTasks;
        this.completedTasks = completedTasks;
        this.columnList = columnList;
        this.history = history;
    }

    public void addHistory(UUID userUUID, UUID changedID, String changeLog){
        Update newUpdate = new Update(userUUID,changedID, changeLog);
        history.add(newUpdate);
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

    public ArrayList<Task> getCompletedTasks(){
        return completedTasks;
    }

    public ArrayList<Task> getOngoingTasks(){
        return ongoingTasks;
    }

    public ArrayList<Column> getColumnList(){
        return columnList;
    }

    public History getHistory(){
        return history;
    }




    public boolean addTask(Task task){
        if (!ongoingTasks.contains(task)) {
            ongoingTasks.add(task);
            return true;
        } else {
            return false; 
        }

    }

    public boolean removeTask(Task task){
        if (ongoingTasks.contains(task)) {
            ongoingTasks.remove(task);
            return true; 
        } else {
            return false; 
        }
    }

    public boolean addColumn(Column Column){
        if (columnList.contains(Column)) {
            return false; 
        } else {
            columnList.add(Column);
            return true; 
        }

    }

    public boolean addTaskToColumn(Task task, Column column){
        if (columnList.contains(column)) { 
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
    if (columnList.contains(column)) {
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
        for (Task projectTask : taskList) {
            if (projectTask.equals(task)) {
                projectTask.setStatus(status); 
                break; 
            }
        }
    }
}
