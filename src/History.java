import java.util.*;
/*
 * @author  Cameron Reyes
 */

public class History {
    private  ArrayList<Update> historyList = new ArrayList<Update>();

    public History(){}

    public void loadHistory( ArrayList<Update> historyList){
        for (int i = 0; i < historyList.size(); i++){
            this.historyList.add(historyList.get(i));
        }
        sortHistory();
    }

    public  ArrayList<Update> getHistory(UUID userID){
        return historyList;
    }
    
    //May change to merge sort in the future
    private void sortHistory(){
        if(historyList.size() == 0) return;
        //Implement selection sort
        //Resourced from https://www.geeksforgeeks.org/selection-sort/
        for(int i = 0; i <= historyList.size(); i++){
            int hold = i;
            for(int j = i + 1; j<= historyList.size(); j++){
                if(historyList.get(j).getDate().before(historyList.get(hold).getDate())) 
                    hold = j;
            }
            Update holdUpdate = historyList.get(hold);
            historyList.set(hold, historyList.get(i));
            historyList.set(i, holdUpdate);
        }
    }
    public  ArrayList<Update> accessHistoryChangedByUUID(UUID ChangedID){
        ArrayList<Update> userUpdate = new ArrayList<Update>();
        for (int i = 0; i < historyList.size(); i++){
            if(historyList.get(i).getUserUUID().compareTo(ChangedID) == 0){
                userUpdate.add(historyList.get(i));
            }
        }
        return userUpdate;
    }

    public void add(Update update){
        historyList.add(update);
    }

    public ArrayList<Update> getHistoryList() {
        return historyList;
    }
}
