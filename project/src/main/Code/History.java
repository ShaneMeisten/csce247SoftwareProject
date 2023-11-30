import java.util.*;
/*
 * Allows projects to record the changes throughout time
 * 
 * Relies on the Update class
 * 
 * @author  Cameron Reyes
 */

public class History {
    private  ArrayList<Update> historyList = new ArrayList<Update>();

    /*
     * History Constructor
     */

    public History(){}

    /*
     * Loads an array of comments into the current history
     * 
     * The history is arranged by date constructed in the update
     * This method relies on sortHistory
     * 
     * @param historylist   The list of updates to be added to history
     */
    public void loadHistory( ArrayList<Update> historyList){
        for (int i = 0; i < historyList.size(); i++){
            this.historyList.add(historyList.get(i));
        }
        sortHistory();
    }

    /*
     * Adds a change to history
     * 
     * @param update    The update to be added to the historylist
     */

    public void add(Update update){
        historyList.add(update);
    }

    /*
     * Allows the ability to see the changes in history
     * 
     * @return historylist      The current list of updates
     */

    public ArrayList<Update> getHistoryList() {
        return historyList;
    }

    /*
     * Allows the ability to see the change of history by a specific user
     * 
     * @param ChangedID     The User UUID who is searched to see changes in the history list
     * @return userUpdate   The list of updates by the user
     */
    
    public  ArrayList<Update> accessHistoryChangedByUUID(UUID ChangedID){
        ArrayList<Update> userUpdate = new ArrayList<Update>();
        for (int i = 0; i < historyList.size(); i++){
            if(historyList.get(i).getUserUUID().compareTo(ChangedID) == 0){
                userUpdate.add(historyList.get(i));
            }
        }
        return userUpdate;
    }

    /*
     * Helper method to sort history
     * 
     * Used when loading history
     * Sorts through selection sort
     * /Resourced from https://www.geeksforgeeks.org/selection-sort/
     */
    private void sortHistory(){
        if(historyList.size() == 0) return;
        for(int i = 0; i < historyList.size(); i++){
            int hold = i;
            for(int j = i + 1; j < historyList.size(); j++){
                if(historyList.get(j).getDate().before(historyList.get(hold).getDate())) 
                    hold = j;
            }
            Update holdUpdate = historyList.get(hold);
            historyList.set(hold, historyList.get(i));
            historyList.set(i, holdUpdate);
        }
    }
}
