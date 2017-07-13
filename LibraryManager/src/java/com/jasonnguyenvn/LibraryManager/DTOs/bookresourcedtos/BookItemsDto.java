/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement(name = "bookitems")
public class BookItemsDto {
    List<BookDto> bookDtos;

    public BookItemsDto() {
        this.bookDtos = new ArrayList<BookDto>();
    }

    public BookItemsDto(List<BookDto> bookDtos) {
        this.bookDtos = bookDtos;
    }

    @XmlElement(required = true)
    public List<BookDto> getBook() {
        return bookDtos;
    }
    
    
    
}
