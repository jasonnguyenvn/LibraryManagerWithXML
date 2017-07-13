/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets;

import com.jasonnguyenvn.LibraryManager.DAOs.UserDAO;
import com.jasonnguyenvn.LibraryManager.DAOs.UserResourceDao;
import com.jasonnguyenvn.Utilities.LibraryManagerConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientResponse;

/**
 *
 * @author Hau
 */
public class LoginServlet extends HttpServlet {

    private final String invalidPage = "invalid.html";
    private final String homePage = "./";
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String url = invalidPage;
        try {
            String username = request.getParameter("txtUsername");
            String password  = request.getParameter("txtPassword");
            
            MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
            formData.add("username", username);
            formData.add("password", password);
            
            String apiUrl = LibraryManagerConstants.API_URL;
            Client client = ClientBuilder.newClient();
            
            
            WebTarget target = client.target(apiUrl)
                    .path( LibraryManagerConstants.USER_LOGIN_METHOD);
            
            String wsResponse = target.request(MediaType.APPLICATION_XML)
                    .post(Entity.form(formData), String.class);
            System.out.println("ahihi login " + wsResponse);
            if (wsResponse != null && !wsResponse.equals("")) {
                url = homePage;
                HttpSession session = request.getSession();
                session.setAttribute("USERINFO", wsResponse);
            }
            
            response.sendRedirect(url);
            
        }  finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
