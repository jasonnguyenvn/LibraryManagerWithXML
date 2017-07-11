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
@XmlRootElement
public class BookcopyDto implements Serializable {
    private int id;
    private String code;
    private List<CeilDto> storedin;

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getCode() {
        return code;
    }

    @XmlElement
    public List<CeilDto> getStoredin() {
        return storedin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public void addStoringCeil(CeilDto ceilDto) {
        if (storedin == null) {
            storedin = new ArrayList<CeilDto>();
        }
        
        storedin.add(ceilDto);
    }
    
    
    
}
