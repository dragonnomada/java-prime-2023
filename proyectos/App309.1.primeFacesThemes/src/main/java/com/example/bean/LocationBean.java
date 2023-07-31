package com.example.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class LocationBean {

	private double latitud;
	private double longitud;
	
	public void info(String title, String description) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, description);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void updateCoords() {
		String lat = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lat");
        String lon = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lon");
		
        if (lat != null) {
        	latitud = Double.parseDouble(lat);
        }
        
        if (lon != null) {
        	longitud = Double.parseDouble(lon);
        }
        
		info("Ubicación actualizada", "Lat/Lon: " + latitud + ", " + longitud);
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

}
