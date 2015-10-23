package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.CommentModel;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author steven
 */
@WebServlet(name = "Comment", urlPatterns = {"/Comment", "/Comment/*"})
public class Comment extends HttpServlet {
    
    private Cluster cluster;
    private String profile = null;

    public Comment()
    {
    }
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

  
    /**
     * Handles the HTTP <code>GET</code> method.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //used to set the profile variable then just redirects to comment.jsp page
        String args[] = Convertors.SplitRequestPath(request);
        profile = args[2];
        RequestDispatcher rd = request.getRequestDispatcher("/comment.jsp");
        rd.forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     */
    //used to post comment to the comment table
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //gets logged in user to record who commented
        HttpSession session=request.getSession();
        LoggedIn lg= (LoggedIn)session.getAttribute("LoggedIn");
        //gets users comment
        String comment = request.getParameter("comment");
        System.out.println(lg.getUsername() + " " + comment + " " +  profile);
        
        //creates a Comment model and passes variable to insert into the database
        //then redirects to the profile page  
        CommentModel addComm = new CommentModel();
        addComm.setCluster(cluster);
        addComm.addComment(lg.getUsername(), comment, profile);
        
       //redirects back to profile
        System.out.println("InstagrimSWTurner/Profile/" + profile);
        response.sendRedirect("/InstagrimSWTurner/Profile/" + profile);
         
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
