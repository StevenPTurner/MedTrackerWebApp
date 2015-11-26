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
@WebServlet(name = "Refill", urlPatterns = {"/Refil/*"})
public class Refill extends HttpServlet {
    
    Cluster cluster = null;
    private Object session;
    
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods
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
        //used to get a profile bean of the users data to display
        MedicineModel medM = new MedicineModel();
        medM.setCluster(cluster);
        Medicine med = medM.getUserMedicine(id);
        
        String username = med.getUsername();
        String medicine_name = med.getMedicineName();
        medM.refillPrescription(id,username,medicine_name);   

        
        
        response.sendRedirect("/MedTracker/MyMeds/" + username);
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
