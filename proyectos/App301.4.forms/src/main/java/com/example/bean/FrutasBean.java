package com.example.bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.example.entity.Fruta;
import com.example.service.FrutaService;

@ManagedBean
@RequestScoped
public class FrutasBean {

	@Inject
	FrutaService frutaService;

	// Valores retenidos por el bean (conectados a la vista)
	String nombre;
	double precio;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void addFruta() {
		frutaService.addFruta(nombre, precio);
	}

	public List<Fruta> getFrutas() {
		return frutaService.getFrutas();
	}

	// List<String> frutas;
	//
	// public List<String> getFrutas() {
	// return frutas;
	// }
	//
	// @PostConstruct // ~ PostAddToViewEvent
	// public void init() {
	// frutas = Arrays.asList("Manzana", "Pera", "Mango"); // Iniciar la lista
	// mediante un servicio
	// }

	// private Map<String, Double> frutas;
	//
	// public Map<String, Double> getFrutas() {
	// return frutas;
	// }
	//
	// @PostConstruct // ~ PostAddToViewEvent
	// public void init() {
	// frutas = new HashMap<String, Double>();
	//
	// frutas.put("Manzana", 45.5);
	// frutas.put("Pera", 75.5);
	// frutas.put("Mango", 23.5);
	// }

}
