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

    public Comment(UUID id, String name, String description, UUID author, Date date, ArrayList<Comment> replies){
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.date = date;
        this.replies = replies;
    }

    public Comment(UUID author, String name, String description){
        this.id = UUID.randomUUID();
        this.author = author;
        this.name = name;
        this.description = description;
        this.date = new Date();
        this.replies = new ArrayList<Comment>();
    }

    public UUID getId(){
        return id;
    }

    public UUID getAuthorUUID(){
        return author;
    }

    public String getDescription(){
        return description;
    }

    public Date getDate(){
        return date;
    }

    public String getName(){
        return name;
    }

    public void addReply(Comment comment){
        replies.add(comment);
    }

    public boolean removeComment(UUID author, Comment comment){
        if(author.compareTo(this.author) != 0) return false;
        replies.remove(comment);
        return true;
    }

    public ArrayList<Comment> getReplies(){
        return replies;
    }
}
