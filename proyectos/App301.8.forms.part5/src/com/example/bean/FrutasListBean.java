package com.example.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class FrutasListBean {

	private List<Double> prices;
	
	public List<Double> getPrices() {
		return prices;
	}
	
	public boolean hasPrices() {
		return prices.size() > 0;
	}
	
	public boolean isPricesEmpty() {
		return prices.size() == 0;
	}

	@PostConstruct
	public void init() {
		prices = new ArrayList<Double>();
	}
	
	public void updatePriceList(String name) {
		
		switch (name) {
		case "manzana":
			prices = Arrays.asList(45.5, 4.5, 23.5);
			break;
			
		case "pera":
			prices = Arrays.asList(65.5, 8.5, 56.5);
			break;
			
		case "SHOW-ALL":
			String outcome = "/pages/page1.xhtml?faces-redirect=true";

		    FacesContext context = FacesContext.getCurrentInstance();
		    
		    Application application = context.getApplication();
		    
		    NavigationHandler handler = application.getNavigationHandler();
		    
		    handler.handleNavigation(context, null, outcome);
		    
			break;

		default:
			prices = new ArrayList<Double>();
			break;
		}
		
	}
	
}
