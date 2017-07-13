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
@XmlRootElement(name = "bookshelf")
public class BookshelfDto implements Serializable  {
    private int id;
    private String title;
    
    public BookshelfDto() {
    }

    public BookshelfDto(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
    

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
