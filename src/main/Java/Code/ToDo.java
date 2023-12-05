package Code;
<<<<<<< HEAD:src/Code/ToDo.java
=======

>>>>>>> c0f85a63d929d6468f00aa15753f2334bfad43ba:src/main/Java/Code/ToDo.java
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
