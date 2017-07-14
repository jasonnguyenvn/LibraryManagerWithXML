/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.LibraryManager.servlets.services;

import java.util.Set;

/**
 *
 * @author Hau
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.jasonnguyenvn.LibraryManager.servlets.services.BookResource.BookCheckAvailableResource.class);
        resources.add(com.jasonnguyenvn.LibraryManager.servlets.services.BookResource.BookGetInforResource.class);
        resources.add(com.jasonnguyenvn.LibraryManager.servlets.services.BookResource.BookSearchResource.class);
        resources.add(com.jasonnguyenvn.LibraryManager.servlets.services.UserResource.class);
    }
    
}
