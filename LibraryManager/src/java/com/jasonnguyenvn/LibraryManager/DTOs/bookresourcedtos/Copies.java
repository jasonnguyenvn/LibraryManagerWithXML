/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement(name = "copies")
public class Copies {
    private List<BookcopyDto> bookcopy;

    public Copies() {
    }

    public Copies(List<BookcopyDto> bookcopy) {
        this.bookcopy = bookcopy;
    }

    
    @XmlElement(required = true)
    public List<BookcopyDto> getBookcopy() {
        return bookcopy;
    }
    
    
    
}
