/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets.services;

import com.jasonnguyenvn.LibraryManager.DAOs.UserResourceDao;
import com.jasonnguyenvn.LibraryManager.DTOs.userresourcedtos.UserDto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Hau
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of com.jasonnguyenvn.LibraryManager.servlets.services.UserResource
     * @param username
     * @param password
     * @return an instance of java.lang.String
     */
    @Path("login")
    @POST
    @Produces("application/xml")
    public UserDto login(
              @FormParam("username") String username,
              @FormParam("password") String password
            ) {
        UserResourceDao dao = new UserResourceDao();
        try {
            dao.login(username, password);
            
            return dao.getUserDto();
        } catch (SQLException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        return null;
    }

}
