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
    public static interface PrepareStatementCallback<T> {
        void setParameters(Object ... parameters);
        PreparedStatement process(Connection con) throws SQLException;
    }
    
    public static interface ProcessResultSetCallback<T> {
         T process(ResultSet rs)  throws SQLException;
    }
    
    private Connection connection;
    
    protected void openConnection() throws NamingException, SQLException {
        Connection con = null;
        con = DBUtilities.makeConnection();
    }
    
    protected void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    protected Connection getConnection() {
        return connection;
    }
    
    protected boolean isConnectionOpened() throws SQLException {
        if (connection == null) {
            return false;
        }
        
        
        return !connection.isClosed();
    }
    
    public <T> T executeSelect(PrepareStatementCallback prepareStatementCallback,
            ProcessResultSetCallback<T> processResultSetCallback) 
            throws SQLException, NamingException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        T result = null;
        
        try {
            this.openConnection();
            if (isConnectionOpened()) {
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
            this.closeConnection();
        }
        
        
        return null;
    }
}
