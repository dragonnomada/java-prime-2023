package com.example.service;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Stateless
public class MessageService {

	public void info(String title, String description) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, description);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public void warning(String title, String description) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, title, description);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public void error(String title, String description) {
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, description);
		
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
}
