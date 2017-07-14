/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DAOs;

import com.jasonnguyenvn.Utilities.DBUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Hau
 */
public abstract class AbstractDbDao {
    public static interface PrepareStatementCallback {
        void setParameters(Object ... parameters);
        PreparedStatement process(Connection con) throws SQLException;
    }
    
    public static interface ProcessResultSetCallback<T> {
         T process(ResultSet rs)  throws SQLException, NamingException;
    }
    
   
    
    protected <T> T executeSelect(PrepareStatementCallback prepareStatementCallback,
            ProcessResultSetCallback<T> processResultSetCallback, 
            Object ... parameters) 
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        T result = null;
        
        try {
            connection = DBUtilities.makeConnection();
            if (connection != null) {
                prepareStatementCallback.setParameters(parameters);
                stm = prepareStatementCallback.process(connection);
                if (stm != null) {
                    rs = stm.executeQuery();
                    return processResultSetCallback.process(rs);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            } 
                    
        }
        
        
        return null;
    }
}
