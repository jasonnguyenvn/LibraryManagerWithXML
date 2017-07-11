/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos;

import com.jasonnguyenvn.LibraryManager.DTOs.SearchResultElementDto;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement
public class BookDto implements SearchResultElementDto {
    private int id;
    private String booktitle;
    private String author;
    private Double  price;
    private String description;
    private int year;
    private String publisher;
    
    private String tags;
    
    private List<BookcopyDto> copies;

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getBooktitle() {
        return booktitle;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    @XmlElement
    public Double getPrice() {
        return price;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public int getYear() {
        return year;
    }

    @XmlElement
    public String getPublisher() {
        return publisher;
    }

    @XmlElement
    public String getTags() {
        return tags;
    }

    @XmlElement
    public List<BookcopyDto> getCopies() {
        return copies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void addNewCopy(BookcopyDto copyDto) {
        if (copies == null) {
            copies = new ArrayList<BookcopyDto>();
        }
        copies.add(copyDto);
    }
    
    public void addNewCopies(BookcopyDto ... copyDtos) {
        if (copies == null) {
            copies = new ArrayList<BookcopyDto>();
        }
        for (BookcopyDto dto : copyDtos) {
            copies.add(dto);
        }
    }
    
}
