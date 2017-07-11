/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 * @param <T>
 */
@XmlRootElement
public class SearchPagingDto<T extends SearchResultElementDto> {
    private int pagesize;
    private int currentpage;
    private List<T> list;

    @XmlElement
    public int getPagesize() {
        return pagesize;
    }

    @XmlElement
    public int getCurrentpage() {
        return currentpage;
    }

    @XmlElement
    public List<T> getList() {
        return list;
    }

    public SearchPagingDto() {
        list = new ArrayList<T>();
    }

    public SearchPagingDto(int pagesize, int currentpage, List<T> list) {
        this.pagesize = pagesize;
        this.currentpage = currentpage;
        this.list = list;
    }
    
    

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
    
    
    
    
    
}
