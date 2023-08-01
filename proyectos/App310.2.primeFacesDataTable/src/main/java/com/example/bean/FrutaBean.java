package com.example.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.example.entity.Fruta;
import com.example.service.FrutaService;

@ManagedBean
@RequestScoped
public class FrutaBean {

	@Inject
	FrutaService frutaService;

	private String nombre;
	private double precio;
	
	public void openAddFrutaDialog() {
		nombre = "";
		precio = 0.0;
	}

	public void submitAddFruta() {
		frutaService.addFruta(nombre, precio);
	}

	public List<Fruta> getFrutas() {
		return frutaService.getFrutas();
	}

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

}
