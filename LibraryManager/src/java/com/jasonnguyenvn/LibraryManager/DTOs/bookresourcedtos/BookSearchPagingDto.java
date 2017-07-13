/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement(name = "BookSearchResult")
public class BookSearchPagingDto {
    int page;
    int pagesize;
    Integer totalsize;
    BookItemsDto books;
    String searchby;
    String searchvalue;

    public BookSearchPagingDto() {
    }

    public BookSearchPagingDto(Integer totalSize, BookItemsDto books) {
        this.totalsize = totalSize;
        this.books = books;
    }
    
    

    public BookSearchPagingDto(int page, int pageSize, Integer totalSize, BookItemsDto books) {
        this.page = page;
        this.pagesize = pageSize;
        this.totalsize = totalSize;
        this.books = books;
    }
    @XmlElement
    public String getSearchby() {
        return searchby;
    }
    @XmlElement
    public String getSearchvalue() {
        return searchvalue;
    }
    
    

    @XmlElement
    public int getPage() {
        return page;
    }

    @XmlElement
    public int getPagesize() {
        return pagesize;
    }

    @XmlElement
    public Integer getTotalsize() {
        return totalsize;
    }

    @XmlElement
    public BookItemsDto getBooks() {
        return books;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalsize = totalSize;
    }

    public void setBooks(BookItemsDto books) {
        this.books = books;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
    }

    public void setSearchby(String searchby) {
        this.searchby = searchby;
    }

    public void setSearchvalue(String searchvalue) throws UnsupportedEncodingException {
        this.searchvalue = URLEncoder.encode(searchvalue, "UTF-8");
    }
    
    
    
    
}
