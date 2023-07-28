package com.example.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class SaveBean {

	public void save() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha iniciado el guardado", "Espera aproximadamente 10 segundos");
		
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se produjo un error al guardar", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se han guardado los cambios", "Todo salió bien");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
