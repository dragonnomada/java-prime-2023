package com.example.bean;

import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class SaveBean {

	Logger logger = Logger.getLogger("SaveBean");
	
	@Asynchronous
	public void save() {
		logger.info("Saving...");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			//
		}
		
		PrimeFaces.current().executeScript("PF('blockUIWidget').hide()");
	}
	
}
