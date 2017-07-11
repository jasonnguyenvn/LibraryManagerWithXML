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
@XmlRootElement
public class CeilDto implements Serializable {
    private int id;
    private int row;
    private int col;
    private String description;
    private int size;
    private String tags;
    
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
    public int getCol() {
        return col;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public int getSize() {
        return size;
    }

    @XmlElement
    public String getTags() {
        return tags;
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

    public void setCol(int col) {
        this.col = col;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCeilof(BookshelfDto ceilof) {
        this.ceilof = ceilof;
    }

    
    
    
}
