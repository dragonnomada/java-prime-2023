package com.example.config;

import javax.faces.annotation.FacesConfig;
import javax.faces.push.PushContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@FacesConfig
@WebListener
public class ApplicationConfig implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.setInitParameter(
				PushContext.ENABLE_WEBSOCKET_ENDPOINT_PARAM_NAME, "true");
	}
	
}