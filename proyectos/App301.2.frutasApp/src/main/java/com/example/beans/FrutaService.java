package com.example.beans;

import java.util.Date;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.entity.Fruta;

// JSF 2.2 -> @Managed (javax.faces.bean)
// JSF 2.3 -> @Named (javax.inject)

@ManagedBean // Definimos que FrutaService será el bean (instancia) -> frutaService
@RequestScoped // La vida del bean dura lo que dure la petición
public class FrutaService {

	Logger logger = Logger.getLogger("FrutaService");
	
	// Propiedad completa ([variable], getter, setter)
	private String nombre = "ejemplo";
	// Propiedad completa ([variable], getter, setter)
	private double precio = 123.45;
	
	@Inject
	FrutaServiceDB frutaServiceDB;
	
	// Propiedad -> currentDate ~= getCurrentDate()
	public String getCurrentDate() {
		return new Date().toString();
	}

	// Propiedad completa (variable, [getter], setter)
	public String getNombre() {
		return nombre;
	}

	// Propiedad completa (variable, getter, [setter])
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Propiedad completa (variable, [getter], setter)
	public double getPrecio() {
		return precio;
	}

	// Propiedad completa (variable, getter, [setter])
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void addFruta() {
		logger.info("Agregar fruta: " + nombre + " $" + precio);
		frutaServiceDB.addFruta(nombre, precio);
	}
	
}
