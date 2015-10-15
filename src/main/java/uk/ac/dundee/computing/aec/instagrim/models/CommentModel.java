package uk.ac.dundee.computing.aec.instagrim.models;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.stores.UserProfile;
import uk.ac.dundee.computing.aec.instagrim.stores.CommStore;
import java.util.LinkedList;



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
    
    public void addComment(String commenterUsername, String comment, String profileUsername)
    {
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MMMM.yyyy");
        String postedDate = format.format(currentDate);
        postedDate = postedDate.replace(".", " ");
        
        Convertors convertor = new Convertors();
        java.util.UUID commentID = convertor.getTimeUUID();
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into usercomments (comment_id, commenter_username, comment, profile_username, date_posted) Values(?,?,?,?,?)");
       
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(commentID, commenterUsername, comment, profileUsername, postedDate));
    }
    
    public java.util.LinkedList<CommStore> getComments(String profileUsername) {
        java.util.LinkedList<CommStore> comment = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from usercomments where profile_username = ? ALLOW FILTERING");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs=session.execute(boundStatement.bind(profileUsername)); // executes statement
        session.close();
        
        // if user dosn't exist
        if (rs.isExhausted()) {
            System.out.println("User has no comments");
            return null;
        } else { //if it does fill profile bean
            for (Row row : rs) {
                CommStore comm = new CommStore();
                comm.setComment(row.getString("comment"));
                comm.setCommenter(row.getString("commenter_username"));
                comm.setProfile(row.getString("profile_username"));
                comm.setDatePosted(row.getString("date_posted"));
                comment.add(comm);
            }
            return comment;
        }
        
    }
}
