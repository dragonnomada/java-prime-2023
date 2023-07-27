package com.example.converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.example.model.Producto;

@FacesConverter(forClass=Producto.class)
public class ProductoConverter implements Converter {

	Logger logger = Logger.getLogger("ProductoConverter");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		logger.info("Convirtiendo el texto en objeto");
		
		long id = Long.parseLong(value);
		// String[] parts = value.split("::");
		// long id = Long.parseLong(parts[0])
		// String nombre = parts[1]
		// Double precio = Double.parseDouble(parts[2])
		// Producto producto = new Producto();
		// producto.setId(id);
		// producto.setNombre(nombre);
		// producto.setPrecio(precio);
		
		logger.info("ID: " + id);
		
		// TODO: return productoService.getById(id);
		
		if (id == 123L) {
			Producto producto = new Producto();
			
			producto.setId(id);
			producto.setNombre("Coca Cola");
			producto.setPrecio(28.5);
			
			return producto;
		} else {
			logger.info("No se encuentra el producto " + id);
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		logger.info("Convirtiendo el objeto en texto");
		
		Producto producto = (Producto) value;
		
		logger.info("Producto: [" + producto.getId() + "] " + producto.getNombre() + " " + producto.getPrecio());
		
		return String.valueOf(producto.getId());
		
		// return String.format("%d::%s::%f", producto.getId(), producto.getNombre(), producto.getPRecio());
	}

}
