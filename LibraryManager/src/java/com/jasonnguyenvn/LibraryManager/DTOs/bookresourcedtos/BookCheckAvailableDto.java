/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.DTOs.bookresourcedtos;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@XmlRootElement(name = "availbletoborrowchecking")
public class BookCheckAvailableDto {
    private boolean result;

    public BookCheckAvailableDto() {
    }

    public BookCheckAvailableDto(boolean result) {
        this.result = result;
    }

    @XmlElement
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
    
    
}
