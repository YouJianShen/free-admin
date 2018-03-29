package com.suit.core.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.suit.core.util.AppUtil;

public class ApplicationContextLoaderServlet extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
    	AppUtil.setContext(
            WebApplicationContextUtils.getWebApplicationContext(
                config.getServletContext()));
    }
}