/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michzarnowski.michal_zarnowski_a2.servlets;

import com.michzarnowski.michal_zarnowski_a2.model.Avenger;
import com.michzarnowski.michal_zarnowski_a2.model.AvengerDb;
import com.michzarnowski.michal_zarnowski_a2.model.PowerSource;
import com.michzarnowski.michal_zarnowski_a2.model.PowerSourceDb;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michal Zarnowski
 */
public class AddAvenger extends HttpServlet {

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

        //New Avenger details
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String powerSource = request.getParameter("powerSource");

        //Validate user input
        if (name == null || name.length() == 0
                || description == null || description.length() == 0) {
            RequestDispatcher rd = request.getRequestDispatcher("invalidInput.jsp");
            rd.forward(request, response);
        } else {

            //Get new ID based on amount of records in Avnger DB already
            int avengerId = AvengerDb.getAvengers().size() + 1;

            /*Find ID of powerSource by finding a match for description parameter
          inside ArrayList of power sources, start count at 1 to match DB*/
            int psId = 0;
            int count = 1;
            ArrayList<PowerSource> powerSources = PowerSourceDb.getPowerSources();
            for (PowerSource ps : powerSources) {
                if (ps.getDescription().equals(powerSource)) {
                    psId = count;
                    break;
                }
                count++;
            }

            //Create new Avenger
            PowerSource tempPowerSource = new PowerSource(psId, powerSource);
            Avenger tempAvenger = new Avenger(avengerId, name, description, tempPowerSource);

            //Add Avenger to DB via AvengerDb class
            int addResult = AvengerDb.addAvenger(tempAvenger);

            //Set query result as a request attribute
            request.setAttribute("queryResult", addResult);

            //Dispatch results JSP
            RequestDispatcher rd = request.getRequestDispatcher("displayAddResult.jsp");
            rd.forward(request, response);
        }
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
