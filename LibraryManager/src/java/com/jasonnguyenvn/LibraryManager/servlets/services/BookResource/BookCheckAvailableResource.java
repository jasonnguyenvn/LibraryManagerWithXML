/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets.services.BookResource;

import com.jasonnguyenvn.LibraryManager.DAOs.BookResourceDao;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookCheckAvailableDto;
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

/**
 * REST Web Service
 *
 * @author Hau
 */
@Path("book/checkAvailable")
public class BookCheckAvailableResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookCheckAvailableResource
     */
    public BookCheckAvailableResource() {
    }

    /**
     * Retrieves representation of an instance of com.jasonnguyenvn.LibraryManager.servlets.services.BookResource.BookCheckAvailableResource
     * @param bookid
     * @return an instance of BookCheckAvailableDto
     */
    @GET
    @Produces("application/xml")
    public BookCheckAvailableDto getXml(
            @QueryParam("bookid") int bookid
        ) {
        BookResourceDao dao = new BookResourceDao();
        
        try {
            dao.checkAvailableToBorrow(bookid);
            return dao.isAvailableToBorrow();
        } catch (SQLException ex) {
            Logger.getLogger(BookCheckAvailableResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BookCheckAvailableResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new BookCheckAvailableDto(false);
    }

}
