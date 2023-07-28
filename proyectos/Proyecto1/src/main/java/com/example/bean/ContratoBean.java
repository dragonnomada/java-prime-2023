package com.example.bean;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.example.entity.Contrato;
import com.example.service.ContratoService;

@ManagedBean
@RequestScoped
public class ContratoBean {
	
	Logger logger = Logger.getLogger("ContratoBean");

	@Inject
	ContratoService contratoService;

	private String nombre;
	private String rfc;
	
	public void agregar() {
		logger.info("Agregando contrato: " + nombre + " " + rfc);
		
		FacesMessage message = new FacesMessage("Agregando contrato: " + nombre + " " + rfc);
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		contratoService.agregarContrato(nombre, rfc);
	}
	
	public List<Contrato> getContratos() {
		return contratoService.getContatos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

}
