package com.example.model;

// Producto(123, "Coca Cola", 28.5) <--> "123" | "123::Coca Cola::28.5" | "0987123"

public class Producto {

	private Long id;
	private String nombre;
	private Double precio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
