/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.JavaEERouterWebApp.DTOs;

/**
 *
 * @author Hau
 */
public class UserDTO {
    private String username;
    private String password;
    private String lastname;
    private boolean admin;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String lastname, boolean admin) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
    
}
