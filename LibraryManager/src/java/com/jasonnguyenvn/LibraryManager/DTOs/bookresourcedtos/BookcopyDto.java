/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement(name = "bookcopy")
public class BookcopyDto implements Serializable {
    private int id;
    private String code;
    private CeilDto storedin;

    public BookcopyDto(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public BookcopyDto() {
    }
    
    

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getCode() {
        return code;
    }

    @XmlElement
    public CeilDto getStoredin() {
        return storedin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStoredin(CeilDto storedin) {
        this.storedin = storedin;
    }
   
    
    
    
    
    
}
