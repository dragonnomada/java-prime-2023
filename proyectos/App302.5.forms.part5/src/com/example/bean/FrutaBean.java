package com.example.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class FrutaBean {

	private String name;

	private String selectedPrice;

	public String getSelectedPrice() {
		return selectedPrice;
	}

	public void setSelectedPrice(String selectedPrice) {
		this.selectedPrice = selectedPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
