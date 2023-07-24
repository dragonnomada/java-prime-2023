package com.example.beans;

import java.util.Date;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.entity.Fruta;

// JSF 2.2 -> @Managed (javax.faces.bean)
// JSF 2.3 -> @Named (javax.inject)

@ManagedBean // Definimos que FrutaService ser� el bean (instancia) -> frutaService
@RequestScoped // La vida del bean dura lo que dure la petici�n
public class FrutaService {

	Logger logger = Logger.getLogger("FrutaService");
	
	// Propiedad completa ([variable], getter, setter)
	private String nombre = "ejemplo";
	// Propiedad completa ([variable], getter, setter)
	private double precio = 123.45;
	
	// TODO: Conectar JPA y una entidad para tener persistencia
	@PersistenceContext(unitName = "frutaEntityManager")
	EntityManager entityManager;
	
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
		// TODO: 1. Crear la entidad Fruta con los datos del nombre y el precio
		Fruta fruta = new Fruta();
		// TODO: 2. Persistir la entidad en la base de datos con JPA
		entityManager.persist(fruta);
	}
	
}
