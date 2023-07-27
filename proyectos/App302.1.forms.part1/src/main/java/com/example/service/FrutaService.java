package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.example.entity.Fruta;

@ManagedBean
@ApplicationScoped
public class FrutaService {

	List<Fruta> frutas = new ArrayList<Fruta>();
	
	long nextId = 0;
	
	public void addFruta(String nombre, double precio) {
		Fruta fruta = new Fruta();
		fruta.setId(++nextId);
		fruta.setNombre(nombre);
		fruta.setPrecio(precio);
		frutas.add(fruta);
	}
	
	public List<Fruta> getFrutas() {
		return frutas;
	}
	
}
