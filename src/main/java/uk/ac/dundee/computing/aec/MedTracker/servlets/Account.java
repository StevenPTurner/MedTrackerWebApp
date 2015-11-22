package uk.ac.dundee.computing.aec.MedTracker.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.MedTracker.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.MedTracker.lib.Convertors;
import uk.ac.dundee.computing.aec.MedTracker.stores.UserProfile;
import uk.ac.dundee.computing.aec.MedTracker.models.User;

/**
 *
 * @author steven
 */
@WebServlet(name = "Account", urlPatterns = {"/Account", "/Account/*"})
public class Account extends HttpServlet {

    
    private Cluster cluster;

    public Account()
    {
        //super();
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
        RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
        rd.forward(request,response);
    }

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //method as based on the display image list in image servlet
        
        //sets up needed data from URL
        String args[] = Convertors.SplitRequestPath(request);     
        
        //gets all user details to be displayed in a bean
        User user = new User();
        user.setCluster(cluster);
        UserProfile data = user.getUserProfile(args[2]);
        request.setAttribute("UserProfile",data);
        
        RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
        rd.forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
