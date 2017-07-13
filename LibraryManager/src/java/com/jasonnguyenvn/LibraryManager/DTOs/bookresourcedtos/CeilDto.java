/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement(name = "ceil")
public class CeilDto implements Serializable {
    private int id;
    private int row;
    private String col;

    public CeilDto() {
    }

    public CeilDto(int id, int row, String col) {
        this.id = id;
        this.row = row;
        this.col = col;
    }
    
    
    
    private BookshelfDto ceilof;

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public int getRow() {
        return row;
    }

    @XmlElement
    public String getCol() {
        return col;
    }


    @XmlElement
    public BookshelfDto getCeilof() {
        return ceilof;
    }

    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(String col) {
        this.col = col;
    }


    public void setCeilof(BookshelfDto ceilof) {
        this.ceilof = ceilof;
    }

    
    
    
}
