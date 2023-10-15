import java.util.*;
/*
 * @author  Cameron Reyes
 */

public class History {
    private static History history;
    private static ArrayList<Update> historyList = new ArrayList<Update>();

    public History(){}

    private static History getInstance(){
        if (history == null) history = new History();
        return history;
    }

    public static ArrayList<Update> getHistory(UUID userID){
        return historyList;
    }
    
    public static ArrayList<Update> accessHistoryChangedByUUID(UUID ChangedID){
        return null;
    }

    public static ArrayList<Update> accessHistoryUserByUUID(UUID ChangedID){
        return null;
    }

    public static void add(Update update){
        historyList.add(update);
    }
}
