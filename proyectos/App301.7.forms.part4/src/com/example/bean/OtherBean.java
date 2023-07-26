package com.example.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

// Bean de procesamiento

@ManagedBean
@RequestScoped
public class OtherBean {

	public void enviar(String subject, String body) {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Sujeto recibido: " + subject));
		context.addMessage(null, new FacesMessage("Cuerpo recibido: " + body));

		// TODO: Consumir un servicio de envio de correos para enviar el subject/body
	}

}
