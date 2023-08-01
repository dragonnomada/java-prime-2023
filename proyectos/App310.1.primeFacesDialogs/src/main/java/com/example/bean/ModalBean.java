package com.example.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import com.example.service.ConfigService;

@ManagedBean
@RequestScoped
public class ModalBean {
	
	@Inject
	private ConfigService configService;
	
	private double tipoCambio;

	public double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	
	public void submit() {
		// TODO: Guardar el tipo de cambio en base datos...
		configService.setTipoCambio(tipoCambio);
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Tipo de cambio aplicado", String.format("Ahora el tipo de cambio es $%.2f", tipoCambio));
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
