/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jasonnguyenvn.JavaEERouterWebApp.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Hau
 */
public class Router implements Filter {
    private static final boolean debug = true;
    private final String loginPage = "login.html";
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        String url = loginPage;
        
        try {
            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);
            
            if (resource.length() > 0) {
                url = resource.substring(0, 1).toUpperCase()
                        + resource.substring(1)
                        + "Servlet";
                if (resource.lastIndexOf(".html") > 0 ||
                        resource.lastIndexOf(".jsp") > 0) {
                    url = resource;
                }
            }
            
            if (url != null) {
                RequestDispatcher rd = httpRequest.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void destroy() {
    }
    
}
