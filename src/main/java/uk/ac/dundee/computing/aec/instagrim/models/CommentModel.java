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
import uk.ac.dundee.computing.aec.instagrim.stores.Comment;
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
        
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into usercomments (commentID, commenterusername, comment, profileusername) Values(?,?,?,?)");
        
        Convertors convertor = new Convertors();
        java.util.UUID commentID = convertor.getTimeUUID();
        
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(boundStatement.bind(commentID, commenterUsername, comment, profileUsername));
    }
    
    public java.util.LinkedList<Comment> getComments(String profileUsername)
    {
        java.util.LinkedList<Comment> comment = new java.util.LinkedList<>();
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select * from usercomments where profileusername=?");
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
                Comment comm = new Comment();
                comm.setComment(row.getString("comment"));
                comm.setCommenter(row.getString("commenterusername"));
                comm.setProfile(row.getString("profileusername"));
                comment.add(comm);
            }
        }
            return comment;
    }
}
