package uk.ac.dundee.computing.aec.instagrim.models;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.text.SimpleDateFormat;
import java.util.Date;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.CommStore;



/**
 *
 * @author steven
 */
public class CommentModel {
    
    Cluster cluster;
    
    public CommentModel(){       
    }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
    //add comment to database, standard insert
    public void addComment(String commenterUsername, String comment, String profileUsername)
    {
        //used to store date format example  10 October 2015
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MMMM.yyyy");
        String postedDate = format.format(currentDate);
        postedDate = postedDate.replace(".", " ");
        
        //unique ID
        Convertors convertor = new Convertors();
        java.util.UUID commentID = convertor.getTimeUUID();
        
        //simple insert into database
        Session session = cluster.connect("instagrim_swturner");
        PreparedStatement ps = session.prepare("insert into usercomments (comment_id, commenter_username, comment, profile_username, date_posted) Values(?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(commentID, commenterUsername, comment, profileUsername, postedDate));
    }
    
    
    //gets list of comments stored in commsStore bean to send back to servlet in linked list
    public java.util.LinkedList<CommStore> getComments(String profileUsername) 
    {
        //sets up linked list of CommStore bean
        java.util.LinkedList<CommStore> comment = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim_swturner");
        //gets all comments from a specific user profile
        PreparedStatement ps = session.prepare("select * from usercomments where profile_username = ? ALLOW FILTERING");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs=session.execute(boundStatement.bind(profileUsername)); // executes statement
        session.close();
        
        // if user dosn't exist or has no comments
        if (rs.isExhausted()) {
            System.out.println("User has no comments");
            return null;
        } else { //if it does fill comment bean and add to list
            for (Row row : rs) {
                CommStore comm = new CommStore();
                comm.setComment(row.getString("comment"));
                comm.setCommenter(row.getString("commenter_username"));
                comm.setProfile(row.getString("profile_username"));
                comm.setDatePosted(row.getString("date_posted"));
                comment.add(comm); //add to linked list 
            }
            return comment;
        }
        
    }
}
