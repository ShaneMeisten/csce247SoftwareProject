package Code;

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
    private ArrayList<Task> ongoingTasks = new ArrayList<Task>();
    private ArrayList<Column> columnList = new ArrayList<Column>();
    private ArrayList<String> columnListTitle = new ArrayList<>();          // Not in UML - Just for Hard Coded Presentation
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


    // This is used purely for testing, will not be used in the final product
    public Project() {
    
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

    // Not in UML, just for hard coded presentation
    public boolean addColumn(String title){
        if(columnListTitle.contains(title)){
            return false;
        }
        columnListTitle.add(title);
        return true;
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

    public Column viewColumn(String name){

    for (Column column : columnList) {
        if (column.getTitle().equals(name)) {
            return column;
        }
    }
    return null;
    }

     public ArrayList<Column> viewColumns(){
        return columnList;
    }

    public ArrayList<Task> viewColumnTask(Column column, String taskName){
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Column tempColumn : columnList) {
            if (tempColumn.equals(column)) { 
                for (Task task2 : column.getTasks()) { 
                    if (task2.getTitle().equals(taskName)) { 
                        matchedTasks.add(task2);
                    }
                }
                break; 
            }
        }
    
        return matchedTasks;
    }

    public Task viewUnassignedTask(String taskName){

        for (Task tasks : ongoingTasks) {
            if (tasks.getTitle().equals(taskName)) { 
                return tasks;
            }
        }
        return null; 
    }

    public void setProjectStatus(Task task , boolean status){
        if (status) 
            for (Task projectTask : ongoingTasks) {
                if (projectTask.equals(task)) {
                    projectTask.setStatus(status); 
                    completedTasks.add(projectTask);
                    ongoingTasks.remove(projectTask);
                    break; 
                }
            }
        else
            for (Task projectTask : completedTasks) {
                if (projectTask.equals(task)) {
                    projectTask.setStatus(status); 
                    ongoingTasks.add(projectTask);
                    completedTasks.remove(projectTask);
                    break; 
                }
            }
    }   
}
