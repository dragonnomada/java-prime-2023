package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.example.entity.Contrato;

@Stateless
public class ContratoService {

	private List<Contrato> contratos = new ArrayList<Contrato>();
	
	private long nextId = 0;
	
	public void agregarContrato(String nombre, String rfc) {
		Contrato contrato = new Contrato();
		
		contrato.setId(++nextId);
		contrato.setNombre(nombre);
		contrato.setRfc(rfc);
		contrato.setFecha(new Date());
		
		contratos.add(contrato);
	}
	
	public List<Contrato> getContatos() {
		return contratos;
	}
	
}
