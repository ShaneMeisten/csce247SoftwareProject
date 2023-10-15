import java.util.*;

/*
 * @author  Cameron Reyes
 */

public class Comment {
    private UUID id;
    private UUID author;
    private String name;
    private String description;
    private Date date;
    private ArrayList<Comment> replies;

    public Comment(UUID author, String name, String description){
        this.id = UUID.randomUUID();
        this.author = author;
        this.name = name;
        this.description = description;
        this.date = new Date();
        this.replies = new ArrayList<Comment>();
    }

    public void addReply(Comment comment){
        replies.add(comment);
    }

    public ArrayList<Comment> getReplies(){
        return replies;
    }
}
