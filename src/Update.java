import java.util.*;

/*
 * @author  Cameron Reyes
 */

public class Update {
    private Date timeStamp;
    private UUID userUUID;
    private UUID changedID;
    private String changeLog;

    public Update(UUID userUUID, UUID changedID, String changeLog){
        this.timeStamp = new Date();
        this.userUUID = userUUID;
        this.changedID = changedID;
        this.changeLog = changeLog;
    }

    //historyId, timeStamp, historyUser, changelog
    public Update(UUID changedID, Date timeStamp, UUID userUUID, String changeLog){
        this.changedID = changedID;
        this.timeStamp = timeStamp;
        this.userUUID = userUUID;
        this.changeLog = changeLog;
    }

    public Date getDate(){
        return timeStamp;
    }

    public UUID getUserUUID(){
        return userUUID;
    }

    public UUID getChangedID(){
        return changedID;
    }

    public String getChangedLog(){
        return changeLog;
    }

    public String toString(){
        return "TIME: [" + timeStamp.toString() + "]  USER: [" + userUUID.toString() + "] Change: " + changeLog + 
        "|    CHANGEDID: [" + changedID.toString() + "]";
    }

    public static void main(String[] args){
        ArrayList<Integer> amongus = new ArrayList<Integer>();
        amongus.add(1);
        amongus.add(2);
        System.out.println(amongus.size());
        for(int i = 0; i < amongus.size(); i++){
            System.out.println("amongus");
        }
    }
}
