package com.example.bean;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.example.model.Producto;

@ManagedBean
@RequestScoped
public class Bean {

	Logger logger = Logger.getLogger("Bean");
	
	private Long productoId;
	
	private Producto currentProducto;

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	
	@PostConstruct
	public void init() {
		logger.info("Construyendo bean: " + productoId);
	}
	
	public void convertProducto() {
		// TODO: this.producto = productoService.getById(productoId);
	}

}
