/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets;

import com.jasonnguyenvn.Utilities.LibraryManagerConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Hau
 */
public class ViewBookInfoServlet extends HttpServlet {
    private static final String notFoundPage = "notFound.html";
    private static final String displayBookInfoPage = "BookInfo.jsp";

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
        try {
            String idStr = request.getParameter("id").trim();
            
            int id = Integer.parseInt(idStr);
            
            String apiUrl = LibraryManagerConstants.API_URL;
            
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(apiUrl);
            String result = 
                    target.path( LibraryManagerConstants.BOOK_GET_INFO_METHOD)
                    .queryParam("id", id)
                    .request("").get(String.class);
            if (result.trim().equals("")) {
                response.sendRedirect(notFoundPage);
            } else {
            
                request.setAttribute("BOOKINFO", result);
            
                RequestDispatcher rd = request
                        .getRequestDispatcher(displayBookInfoPage);
                rd.forward(request, response);
            }
           
        } catch (NumberFormatException e) { 
            response.sendRedirect(notFoundPage);
        } finally {
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
