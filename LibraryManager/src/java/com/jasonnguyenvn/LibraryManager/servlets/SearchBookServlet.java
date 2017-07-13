/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets;

import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookDto;
import com.jasonnguyenvn.Utilities.LibraryManagerConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Hau
 */
public class SearchBookServlet extends HttpServlet {
    private static final String searchResultPage = "BookSearch.jsp";

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
            String txtSearchValue = request.getParameter("txtSearchValue").trim();
            String cbxSearchBy = request.getParameter("cbxSearchBy").trim().toUpperCase();
            
            String pageStr = request.getParameter("page");
            int page = 1;
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            
            String pageSizeStr = request.getParameter("pagesize");
            int pageSize = 10;
            if (pageStr != null) {
                pageSize = Integer.parseInt(pageSizeStr);
            }
            
            String apiUrl = LibraryManagerConstants.API_URL;
            String searchValue = URLEncoder.encode(txtSearchValue, "UTF-8");
            
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(apiUrl);
            String result = 
                    target.path( LibraryManagerConstants.BOOK_SEARCH_METHOD)
                    .queryParam("searchby", cbxSearchBy)
                    .queryParam("searchvalue",searchValue)
                    .queryParam("page", page)
                    .queryParam("pagesize", pageSize)
                    .request("").get(String.class);
            
            request.setAttribute("SEARCHRESULT", result);
           
        } finally {
            
            RequestDispatcher rd = request.getRequestDispatcher(searchResultPage);
            rd.forward(request, response);
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
