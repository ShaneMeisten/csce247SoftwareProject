package Code;
import java.util.*;

/*
 * Stores the content of changes for History
 * @author  Cameron Reyes
 */

public class Update {
    private Date timeStamp;
    private UUID userUUID;
    private UUID changedID;
    private String changeLog;

    /*
     * Normal constructor for Update
     * 
     * @param userUUID      The User who created the time stamp
     * @param changedID     The project/task/column that was changed
     * @param changeLog     The desciprtive change of what happend
     */

    public Update(UUID userUUID, UUID changedID, String changeLog){
        this.timeStamp = new Date();
        this.userUUID = userUUID;
        this.changedID = changedID;
        this.changeLog = changeLog;
    }

    /*
     * Dataloader constructor for Update
     * 
     * @param changedID     The project/task/column that was changed
     * @param timestamp     The date when this update was constructed
     * @param userUUID      The User who created the time stamp
     * @param changeLog     The desciprtive change of what happend
     */

    public Update(UUID changedID, Date timeStamp, UUID userUUID, String changeLog){
        this.changedID = changedID;
        this.timeStamp = timeStamp;
        this.userUUID = userUUID;
        this.changeLog = changeLog;
    }

    /*
     * Returns the Date the update was made
     * 
     * @return timestamp    The date the update was made
     */

    public Date getDate(){
        return timeStamp;
    }

    /*
     * Returns the user who made the Update
     * 
     * @return userUUID    the user who made the Update
     */

    public UUID getUserUUID(){
        return userUUID;
    }

    /*
     * Returns the Project/Column/task where the update happened
     * 
     * @return changedID   the Project/Column/task where the update happened
     */

    public UUID getChangedID(){
        return changedID;
    }

    /*
     * Returns the change in the update 
     * 
     * @return changeLog   the change in the update 
     */
    

    public String getChangedLog(){
        return changeLog;
    }

    /*
     * Printing the timestamp, userUUID, changedUUID and changeLog
     * 
     * @return toString     Prinable method of the update
     */

    public String toString(){
        return "TIME: [" + timeStamp.toString() + "]  USER: [" + userUUID.toString() + "] Change: " + changeLog + 
        "|    CHANGEDID: [" + changedID.toString() + "]";
    }
}
