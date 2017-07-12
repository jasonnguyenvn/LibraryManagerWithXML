/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets.services;

import com.jasonnguyenvn.LibraryManager.DAOs.BookResourceDao;
import com.jasonnguyenvn.LibraryManager.DTOs.SearchPagingDto;
import com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos.BookDto;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Hau
 */
@Path("book")
public class BookResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookSearchResource
     */
    public BookResource() {
    }
    
    public static final String SEARCH_BY_BOOKTITLE = "BOOKTITLE";
    public static final String SEARCH_BY_AUTHOR = "AUTHOR";
    public static final String SEARCH_BY_PUBLISHER = "PUBLISHER";
    public static final String SEARCH_BY_YEAR = "YEAR";
    
    
    
    /**
     * Retrieves representation of an instance of com.jasonnguyenvn.LibraryManager.servlets.services.BookResource
     * @param searchby Search books by title.
     * @param searchvalue Value to search
     * @param pagesize The size of page
     * @param page The page to get
     * @param response
     * @return an instance of java.lang.String
     */
    @Path("search")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Collection<BookDto> search(
           @DefaultValue(SEARCH_BY_BOOKTITLE) @QueryParam("searchby") String searchby,
           @DefaultValue("") @QueryParam("searchvalue") String searchvalue,
           @DefaultValue("100") @QueryParam("pagesize") Integer pagesize,
           @DefaultValue("1") @QueryParam("pagesize") Integer page
        ) {
        Collection<BookDto> searchResult = null;
        BookResourceDao dao = new BookResourceDao();
        try {
            
            System.out.println("SEARCH " + searchvalue);
            searchvalue = URLDecoder.decode(searchvalue, "UTF-8");
            System.out.println("SEARCH " + searchvalue);
            if (searchby.equals(SEARCH_BY_BOOKTITLE)) {
                dao.searchByBookTitle(searchvalue, pagesize, page);
            } else if (searchby.equals(SEARCH_BY_AUTHOR)) {
                dao.searchByAuthor(searchvalue, pagesize, page);
            } else if (searchby.equals(SEARCH_BY_PUBLISHER)) {
                dao.searchByPublisher(searchvalue, pagesize, page);
            } else if (searchby.equals(SEARCH_BY_YEAR)) {
                dao.searchByYear(searchvalue, pagesize, page);
            }
                    
            searchResult = dao.getSearchResult();
        } catch (SQLException ex) {
            Logger.getLogger(BookResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BookResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BookResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (searchResult == null) {
            return  null;
        }
        
        return searchResult;
    }
    

    
}
