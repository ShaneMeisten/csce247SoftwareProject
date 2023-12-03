package Code;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/*
 * Allows for users to communicate on Tasks
 * @author  Cameron Reyes
 */

public class Comment {
    private UUID id;
    private UUID author;
    private String name;
    private String description;
    private Date date;
    private ArrayList<Comment> replies;

    /*
     * Dataloader constructor for Comment
     * 
     * @param id            The id of the comment
     * @param name          The name of the comment
     * @param description   A description in the comment
     * @param author        The UUID of the author who made the comment
     * @param date          The date the comment was made
     * @param replies       The attached comments to this comment
     */
    public Comment(UUID id, String name, String description, UUID author, Date date, ArrayList<Comment> replies) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.date = date;
        this.replies = replies;
    }

    /*
     * Dataloader constructor for Comment
     * 
     * @param name          The name of the comment
     * @param description   A description in the comment
     * @param author        The UUID of the author who made the comment
     */

    public Comment(UUID author, String name, String description) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.name = name;
        this.description = description;
        this.date = new Date();
        this.replies = new ArrayList<Comment>();
    }

    /*
     * Get comment UUID
     * 
     * @return id    comment's UUID
     */

    public UUID getId(){
        return id;
    }

    /*
     * Get comment author UUID
     * 
     * @return author    Author of the comment
     */

    public UUID getAuthorUUID(){
        return author;
    }

    /*
     * Get comment description
     * 
     * @return description   The description of the comment
     */

    public String getDescription(){
        return description;
    }

    /*
     * Get comment Date
     * 
     * @return date     The date the comment was made
     */

    public Date getDate(){
        return date;
    }

    /*
     * Get comment name
     * 
     * @return name     The name of the comment
     */

    public String getName(){
        return name;
    }

    /*
     * Add comment replies
     * 
     * @param comment   The commment to be added to the replies 
     */

    public void addReply(Comment comment){
        replies.add(comment);
    }

    /*
     * remove comment replies
     * 
     * @param author    checks to see if UUID author matches comment
     * @param comment   The comment to be removed
     */

    public boolean removeComment(UUID author, Comment comment){
        if(author.compareTo(this.author) != 0) return false;
        replies.remove(comment);
        return true;
    }

    /*
     * See the replies on the comment
     * 
     * @return replies  The arraylist of comments for the current comment
     */

    public ArrayList<Comment> getReplies(){
        return replies;
    }
}
