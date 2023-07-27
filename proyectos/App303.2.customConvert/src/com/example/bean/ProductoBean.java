package com.example.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.example.model.Producto;

@ManagedBean
@RequestScoped
public class ProductoBean {

	// viewParam String id -> ProductoConverter.getAsObject(id) -> Producto producto
	private Producto currentProducto;

	public Producto getCurrentProducto() {
		return currentProducto;
	}

	public void setCurrentProducto(Producto currentProducto) {
		this.currentProducto = currentProducto;
	}
	
}
