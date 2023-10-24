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
    
    public ToDo(boolean done, String description, UUID id){
        this.done = done;
        this.description = description;
        this.id = id;

    }

    public boolean getDone(){
        return this.done;
    }

    public String getDescription(){
        return this.description;
    }
}
