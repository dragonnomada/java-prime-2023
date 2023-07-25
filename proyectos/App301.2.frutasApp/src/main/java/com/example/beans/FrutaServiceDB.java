package com.example.beans;

import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.entity.Fruta;

@ManagedBean
@ApplicationScoped
public class FrutaServiceDB {
	
	Logger logger = Logger.getLogger("FrutaServiceDB");

	// TODO: Conectar JPA y una entidad para tener persistencia
	@PersistenceContext(unitName = "frutaStoreEntityManager")
	EntityManager frutaStoreEntityManager;
	
	@Transactional // JTA -> BEGIN ~ Throws (CLOSE: rollback) | (CLOSE: commit)
	public void addFruta(String nombre, double precio) {
		logger.info("Agregar fruta: " + nombre + " $" + precio);
		// TODO: 1. Crear la entidad Fruta con los datos del nombre y el precio
		Fruta fruta = new Fruta();
		fruta.setNombre(nombre);
		fruta.setPrecio(precio);
		fruta.setCreado(new Date());
		// TODO: 2. Persistir la entidad en la base de datos con JPA
		frutaStoreEntityManager.persist(fruta);
		
//		frutaStoreEntityManager.flush();
	}
	
}
