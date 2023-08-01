package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.example.entity.Fruta;

@Stateless
public class FrutaService {

	private List<Fruta> frutas;

	@PostConstruct
	public void init() {

		frutas = new ArrayList<Fruta>();

		frutas.add(Fruta.create("Manzana", 12.5));
		frutas.add(Fruta.create("Pera", 32.5));
		frutas.add(Fruta.create("Mango", 23.5));
		frutas.add(Fruta.create("Piña", 67.5));
		frutas.add(Fruta.create("Melón", 15.0));

	}
	
	public void addFruta(String nombre, double precio) {
		frutas.add(Fruta.create(nombre, precio));
	}

	public List<Fruta> getFrutas() {
		return frutas;
	}
	
}
