/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.userresourcedtos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement(name = "user")
public class UserDto {
    private int id;
    private String username;
    private String fullname;

    public UserDto() {
    }

    public UserDto(int id, String username, String fullname) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    @XmlElement
    public String getFullname() {
        return fullname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    
    
    
    
}
