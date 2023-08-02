package com.example.entity;

public class Fruta {

	// Design Pattern Factory
	private static long nextId;

	public static Fruta create(String nombre, double precio) {
		Fruta fruta = new Fruta();
		fruta.setId(++nextId);
		fruta.setNombre(nombre);
		fruta.setPrecio(precio);
		fruta.setCantidad(0.0);
		fruta.setExistencias(0);
		fruta.setImagen("https://th.bing.com/th/id/OIP._FQe7ttFXerdCINpjiYo_AHaE8?pid=ImgDet&rs=1");
		return fruta;
	}

	private Long id;
	private String nombre;
	private Double precio;
	private Double cantidad;
	private Integer existencias;
	private String imagen;

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

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getExistencias() {
		return existencias;
	}

	public void setExistencias(Integer existencias) {
		this.existencias = existencias;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Fruta [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad
				+ ", existencias=" + existencias + "]";
	}

}
