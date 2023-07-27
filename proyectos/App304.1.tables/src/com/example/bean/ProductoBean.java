package com.example.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.example.entity.Producto;
import com.example.jpa.ProductoService;

@ManagedBean
@RequestScoped
public class ProductoBean {

	@Inject
	ProductoService productoService;

	List<Producto> productos;

	private String productoNombre;
	private double productoPrecio;

	@PostConstruct
	public void init() {
		productos = productoService.getAllProductos();
	}

	public void submitProducto() {
		Producto producto = productoService.addProducto(productoNombre, productoPrecio);
		
		productos.add(producto);
		
		productoNombre = "";
		productoPrecio = 0.0;
		
		FacesContext.getCurrentInstance().addMessage("myForm", new FacesMessage("Producto agregado: " + producto));
	}
	
	public void actualizarProducto(Producto producto) {
		int index = productos.indexOf(producto);
		productos.set(index, producto);
//		productoService.updateProducto(Producto);
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public String getProductoNombre() {
		return productoNombre;
	}

	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}

	public double getProductoPrecio() {
		return productoPrecio;
	}

	public void setProductoPrecio(double productoPrecio) {
		this.productoPrecio = productoPrecio;
	}

}
