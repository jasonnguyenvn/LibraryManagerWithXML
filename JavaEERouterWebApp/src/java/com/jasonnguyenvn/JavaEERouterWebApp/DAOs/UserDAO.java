/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.JavaEERouterWebApp.DAOs;

import com.jasonnguyenvn.JavaEERouterWebApp.DTOs.UserDTO;
import com.jasonnguyenvn.Utilities.DBUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hau
 */
public class UserDAO {
    public boolean checkLogin(String username, String password) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM [user] WHERE username=? AND password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        
        return false;
    }
    
    private List<UserDTO> listAccounts;

    public List<UserDTO> getListAccounts() {
        return listAccounts;
    }
    
    public void searchLastname(String searchValue) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM [user] WHERE lastname LIKE ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    
                    UserDTO dto  = new UserDTO(username, password, lastname, 
                            isAdmin);
                    
                    if (listAccounts == null) {
                        listAccounts = new ArrayList<UserDTO>();
                    }
                    this.listAccounts.add(dto);
                }
            }
        } finally {
             if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }
    
    
    public boolean deleteRecord(String username) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            
            if (con != null) {
                String sql = "DELETE FROM [user] WHERE username=?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                
                int row = stm.executeUpdate();
                
                if (row > 0) {
                    return true;
                }
            }
            
        } finally {
             if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
            
        
        return false;
    }
    
    public String checkLoginUser(HttpSession session) {
        String loginUser = (String) session.getAttribute("USERNAME");

        return loginUser;
    }
    
    public boolean updatePassAndRole(String username, String password, 
            boolean isAdmin) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            
            if (con != null) {
                String sql = "UPDATE [user] "
                        + "SET password=?, isAdmin=?"
                        + " WHERE username=?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                
                int row = stm.executeUpdate();
                
                if (row > 0) {
                    return true;
                }
            }
            
        } finally {
             if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
}
