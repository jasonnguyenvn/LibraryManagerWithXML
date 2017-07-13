/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DAOs;

import com.jasonnguyenvn.LibraryManager.DTOs.userresourcedtos.Password;
import com.jasonnguyenvn.LibraryManager.DTOs.userresourcedtos.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.naming.NamingException;

/**
 *
 * @author Hau
 */
public class UserResourceDao extends AbstractDbDao {
    private UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }
    
    
    
    private AbstractDbDao.PrepareStatementCallback prepareUserLoginStm =
            new PrepareStatementCallback() {
        private String username;

        public void setParameters(Object... parameters) {
            if (parameters == null) {
                return;
            }
            if (parameters.length < 1) {
                return;
            }
            if (parameters[0] != null) {
                username = (String) parameters[0];
            }
        }

        public PreparedStatement process(Connection con) throws SQLException {
            PreparedStatement stm = null;
            if (con != null) {
            String sql = "SELECT [id],[username], [fullname],  [password], [salt] " +
                            "  FROM [user] " +
                            "  WHERE [username]=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
            }
            return stm;
        }
    };
    
    private AbstractDbDao.ProcessResultSetCallback<UserDto> processUserLoginRs
            = new ProcessResultSetCallback<UserDto>() {
        

        public UserDto process(ResultSet rs) throws SQLException, NamingException {
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String password = rs.getString("password");
                String salt = rs.getString("salt");
                
                Password genPassword = new Password(username, plainPassword, salt);
                genPassword.generatePassword();
                
                if (genPassword.getHashedPassword().equals(password)) {
                    return new UserDto(id, username, fullname);
                }
            }
            return null;
        }
    };
    
    private String plainPassword;
    public void login(String username, String plainPassword) 
            throws SQLException, NamingException {
        this.plainPassword = plainPassword;
        this.userDto = executeSelect(prepareUserLoginStm, processUserLoginRs, 
                username);
    }
    
    
    
}
