package Code;

import java.util.UUID;
/**
 * 
 * @author Shane Meisten
 * 
 */

public class ToDo {
    private boolean done;
    private String description;
    private UUID id;
    
    /**
     * constructor used for the Json file
     * @param done
     * @param description
     * @param id
     */
    public ToDo(boolean done, String description, UUID id){
        this.done = done;
        this.description = description;
        this.id = id;

    }

    /*
     * All accessors used in the ToDo class
     */
    public boolean getDone(){
        return this.done;
    }
    public UUID getID(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }
}
