/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets.services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

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

    /**
     * Retrieves representation of an instance of com.jasonnguyenvn.LibraryManager.servlets.services.BookResource
     * @return an instance of java.lang.String
     */
    @Path("search")
    @GET
    @Produces("text/plain")
    public String search() {
        //TODO return proper representation object
        return "hello";
    }

    
}
