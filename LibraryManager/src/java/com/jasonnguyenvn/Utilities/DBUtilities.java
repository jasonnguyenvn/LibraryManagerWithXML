/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.Utilities;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Hau
 */
public class DBUtilities {
    public static Connection makeConnection()
            throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("LibraryManagerDB");
        
        
        Connection con = ds.getConnection();
        
        return con;
    }
    
}
