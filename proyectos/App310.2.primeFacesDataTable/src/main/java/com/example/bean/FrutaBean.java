package com.example.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.example.entity.Fruta;
import com.example.service.FrutaService;
import com.example.service.MessageService;

@ManagedBean
@RequestScoped
public class FrutaBean {

	@Inject
	FrutaService frutaService;

	@Inject
	MessageService messageService;

	private String nombre;
	private Double precio = 0.0;

	private Fruta frutaSeleccionada;

	
	public void openAddFrutaDialog() {
		nombre = "";
		precio = 0.0;

		messageService.warning("Se abrió el modal para agregar frutas", "Esto solo es informativo");
	}

	public void submitAddFruta() {
		if (nombre == null || nombre.isEmpty()) {
			messageService.error("La fruta debe contener un nombre", "Sin nombre de fruta, no válido");
			return;
		}

		frutaService.addFruta(nombre, precio);

		messageService.info("Se agregó la fruta", String.format("[%s] $%.2f", nombre, precio));
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
		messageService.warning("Se modificó el precio", String.format("Ahora el precio es: $%.2f", precio));
	}

	public Fruta getFrutaSeleccionada() {
		return frutaSeleccionada;
	}

	public void setFrutaSeleccionada(Fruta frutaSeleccionada) {
		this.frutaSeleccionada = frutaSeleccionada;
		messageService.warning("Se ha seleccionado una fruta", frutaSeleccionada.toString());
	}

}
