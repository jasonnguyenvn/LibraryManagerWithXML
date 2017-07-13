/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.userresourcedtos;

import com.jasonnguyenvn.Utilities.LibraryManagerConstants;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Sha2Crypt;

/**
 *
 * @author Hau
 */
public class Password {
    private final String username;
    private final String plainPassword;
    private String salt = null;
    
    private String hashedPassword;

    public Password(String username, String plainPassword) {
        this.username = username;
        this.plainPassword = plainPassword;
    }

    
    
    public Password(String username, String plainPassword, String salt) {
        this.username = username;
        this.plainPassword = plainPassword;
        this.salt = salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }
    
    
    public void generatePassword() {
        if (salt == null) {
            this.generateSalt();
        }
        hashedPassword = 
                DigestUtils.sha256Hex((plainPassword+"."+salt).getBytes());
    }
    
   
    public void  generateSalt() {
        Date date = new Date();
        String raw = username + "." + date.getTime() + "." + 
                LibraryManagerConstants.PRIVATE_KEY;
        salt =  DigestUtils.sha256Hex(raw.getBytes());
    }
    
}
