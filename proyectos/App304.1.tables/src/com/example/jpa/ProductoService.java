package com.example.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.entity.Producto;

@Stateless
public class ProductoService {

	@PersistenceContext(unitName = "tiendaEntityManager")
	EntityManager tiendaEntityManager;
	
	@Transactional
	public Producto addProducto(String nombre, double precio) {
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		
		tiendaEntityManager.persist(producto);
		
		tiendaEntityManager.flush();
		
		return producto;
	}
	
	public List<Producto> getAllProductos() {
		return tiendaEntityManager
				.createQuery("FROM Producto producto", Producto.class)
				.getResultList();
	}
	
}
