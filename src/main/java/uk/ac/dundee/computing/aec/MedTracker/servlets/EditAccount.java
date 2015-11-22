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
import uk.ac.dundee.computing.aec.MedTracker.models.User;
import uk.ac.dundee.computing.aec.MedTracker.stores.UserProfile;

/**
 *
 * @author steven
 */
@WebServlet(name = "EditAccount", urlPatterns = {"/EditAccount/*"})
public class EditAccount extends HttpServlet {
    
    Cluster cluster = null;
    
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
        
        //used to get current details
        String args[] = Convertors.SplitRequestPath(request);
        
        //used to get a profile bean of the users data to display
        User user = new User();
        user.setCluster(cluster);
        UserProfile data = user.getUserProfile(args[2]);     
        request.setAttribute("UserProfile",data);

        
        RequestDispatcher rd = request.getRequestDispatcher("/edit.jsp");
        rd.forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //used to get details from form
        // String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        
        
        //sets up and calls the editUserProfile method
        User us=new User();
        us.setCluster(cluster);
        us.editUserProfile(login, first_name, last_name, email);
        System.out.println(login);
        
        //sends user to edited profile
	response.sendRedirect("/MedTracker/Account/" + login);
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
