package com.example.bean;

import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIForm;
//import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
@RequestScoped
public class MyBean {

	Logger logger = Logger.getLogger("");
	
	public void onInputAddToView(ComponentSystemEvent event) {
		logger.info("El UIInput ha sido agregado al árbol (onInputAddToView)");
		//UIInput input = (UIInput) event.getComponent();
		HtmlInputText input = (HtmlInputText) event.getComponent();
		logger.info(input.toString());
		
		Map<String, Object> attributes = input.getAttributes();
		
		for (Entry<String, Object> entry : attributes.entrySet()) {
			logger.info("KEY: " + entry.getKey() + " VALUE: " + entry.getValue());
		}
	}
	
	public void addFields(ComponentSystemEvent event) {
		logger.info("Formulario cargado");
		
		UIForm form = (UIForm) event.getComponent();
	    
	    HtmlOutputLabel label = new HtmlOutputLabel();
	    label.setId("myLabel");
	    label.setFor("myInput");
	    label.setValue("My Label");
	    
	    form.getChildren().add(label);
	}
	
}
