package com.example.entity;

import java.util.Date;

public class Contrato {

	private Long id;
	private String nombre;
	private String rfc;
	private Date fecha;

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

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Contrato [id=" + id + ", nombre=" + nombre + ", rfc=" + rfc + ", fecha=" + fecha + "]";
	}

}
