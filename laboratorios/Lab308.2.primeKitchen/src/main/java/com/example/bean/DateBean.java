package com.example.bean;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DateBean {
	
	Logger logger = Logger.getLogger("DateBean");
	
	private Date date;

	@PostConstruct
	public void init() {
		date = new Date();
		logger.info("Initial Date: " + date);
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		logger.info("Date: " + date);
		this.date = date;
	}

}
