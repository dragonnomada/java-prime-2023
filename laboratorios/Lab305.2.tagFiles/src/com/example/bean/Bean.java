package com.example.bean;

import java.util.Date;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@ManagedBean
@RequestScoped
public class Bean implements ActionListener {

	Logger logger = Logger.getLogger("Bean");

	String email = "test@gmail.com";

	String date = "2023-07-01";

	public String getEmail() {
		return email;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void submit() {
		logger.info("OK");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hello " + new Date()));
	}

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		logger.info("OK 2");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hi " + new Date()));

	}

}
