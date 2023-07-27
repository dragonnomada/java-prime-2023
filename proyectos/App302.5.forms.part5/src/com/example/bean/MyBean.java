package com.example.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MyBean {

	public String submit() {
		// ...
		
		if (Math.random() > 0.8) {
			return "pages/page1.xhtml";
		} else if (Math.random() > 0.4) { 
			return "pages/page2.xhtml";
		} else {
			return null;
		}
	}
	
}
