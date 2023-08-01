package com.example.service;

import javax.ejb.Stateless;

@Stateless
public class ConfigService {

	private double tipoCambio = 17.6;
	//

	public double getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

}
