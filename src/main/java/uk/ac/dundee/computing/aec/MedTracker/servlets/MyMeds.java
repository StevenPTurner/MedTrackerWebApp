/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.MedTracker.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
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
import uk.ac.dundee.computing.aec.MedTracker.models.User;

/**
 *
 * @author steven
 */
@WebServlet(name = "MyMeds", urlPatterns = {"/MyMeds", "/MyMeds/*"})
public class MyMeds extends HttpServlet {

    private Cluster cluster;

    public MyMeds()
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
        RequestDispatcher rd = request.getRequestDispatcher("/myMeds.jsp");
        rd.forward(request,response);
        
    }
    
    /**
     * Handles the HTTP <code>GET</code> method. 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String args[] = Convertors.SplitRequestPath(request); 
        
        MedicineModel med = new MedicineModel();     
        med.setCluster(cluster);
        java.util.LinkedList<Medicine> accountMeds = med.getAllUserMeds(args[2]);
        request.setAttribute("meds", accountMeds);
        
        RequestDispatcher rd = request.getRequestDispatcher("/myMeds.jsp");
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
