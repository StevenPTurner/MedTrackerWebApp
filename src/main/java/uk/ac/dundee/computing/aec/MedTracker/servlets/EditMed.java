/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.MedTracker.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.MedTracker.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.MedTracker.lib.Convertors;
import uk.ac.dundee.computing.aec.MedTracker.models.MedicineModel;
import uk.ac.dundee.computing.aec.MedTracker.stores.Medicine;

/**
 *
 * @author steven
 */
@WebServlet(name = "EditMed", urlPatterns = {"/EditMed/*"})
public class EditMed extends HttpServlet {

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
        
        UUID id = UUID.fromString(args[2]);
        
        System.out.println(id);
        //used to get a profile bean of the users data to display
        MedicineModel medM = new MedicineModel();
        medM.setCluster(cluster);
        Medicine med = medM.getUserMedicine(id);     
        request.setAttribute("userMed",med);

        
        RequestDispatcher rd = request.getRequestDispatcher("/editMed.jsp");
        rd.forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //used to get details from form
        // String password = request.getParameter("password");
        String username = request.getParameter("login");
        String medicine_name = request.getParameter("medicine_name");
        UUID id = UUID.fromString(request.getParameter("id"));
        String instructions = request.getParameter("instructions");
        int dose = Integer.parseInt(request.getParameter("dose"));
        int time_between = Integer.parseInt(request.getParameter("time_between"));

        //sets up and calls the editUserProfile method
        MedicineModel medM = new MedicineModel();
        medM.setCluster(cluster);
        medM.editMedicine(username, medicine_name, id, instructions, dose, time_between);
       
        
        //sends user to edited profile
	response.sendRedirect("/MedTracker/MyMeds/" + username);
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
