/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets.services.BookResource;

import com.jasonnguyenvn.LibraryManager.DAOs.BookResourceDao;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookDto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Hau
 */
@Path("book/getInfo")
public class BookGetInforResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookGetInforResource
     */
    public BookGetInforResource() {
    }

     /**
     * Retrieves representation of an instance of com.jasonnguyenvn.LibraryManager.servlets.services.BookSearchResource
     * @param id The id of book record.
     * @return an instance of BookDto.
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public BookDto search(
            @QueryParam("id") int id
        ) {
        BookDto result = null;
        
        BookResourceDao dao = new BookResourceDao();
        try {
            dao.getBookById(id);
            result = dao.getBookDto();
        } catch (SQLException ex) {
            Logger.getLogger(BookSearchResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BookSearchResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}
