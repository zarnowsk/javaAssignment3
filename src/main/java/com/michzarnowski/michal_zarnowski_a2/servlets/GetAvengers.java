/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michzarnowski.michal_zarnowski_a2.servlets;

import com.michzarnowski.michal_zarnowski_a2.model.Avenger;
import com.michzarnowski.michal_zarnowski_a2.model.AvengerDb;
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
public class GetAvengers extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*Create ArrayList holding all Avenger records retrieved from the DB
          via AvengerDb class*/
        ArrayList<Avenger> avengerList;
        avengerList = AvengerDb.getAvengers();
        
        //Save ArrayList of Avengers as a request attribure
        request.setAttribute("avengers", avengerList);
        
        //Forward to JSP
        RequestDispatcher rd = request.getRequestDispatcher("displayAvengers.jsp");
        rd.forward(request, response);
        
    }

}
