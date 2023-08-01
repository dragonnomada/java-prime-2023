package com.example.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.example.service.ConfigService;

@ManagedBean
@RequestScoped
public class HomeBean {

	@Inject
	private ConfigService configService;
	
	public double currentTipoCambio() {
		return configService.getTipoCambio();
	}
	
}
