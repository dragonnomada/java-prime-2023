package com.example.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class SimpleView {

	private String title = "Hola mundo";
	
	private List<String> suggestions;

	@PostConstruct
	public void init() {
		suggestions = new ArrayList<String>();
		suggestions.add("Prueba 1");
		suggestions.add("Prueba 2");
		suggestions.add("Prueba 3");
	}
		
	public List<String> getSuggestions() {
		return suggestions;
	}



	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void titleFocus(AjaxBehaviorEvent event) {
		HtmlInputText component = (HtmlInputText) event.getComponent();
		
		String text = (String) component.getValue();
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Foco recibido: " + event.getComponent().toString() + " (" + event.getPhaseId() + ") " + "[" + text + "]", "Se ha recibido el foco sobre el h:inputText");
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, message);
	}
	
	public void titleBlur(AjaxBehaviorEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Foco perdido: "  + event.getComponent().toString(), "Se ha perdido el foco sobre el h:inputText");
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.addMessage(null, message);
	}
	
	public void updateSuggestions(AjaxBehaviorEvent event) {
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizando sugerencias...", "Sin detalles");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
		
		// 1. Recuperar el componente h:inputText
		// String text = this.title;
		String text = (String) ((UIInput) event.getComponent()).getValue();
		
		if (text.equals("hola")) {
			suggestions.clear();
			
			for (int i = 0; i < 10; i++) {
				suggestions.add("Hola " + i);
			}
		}
		
		
		if (text.equals("mundo")) {
			suggestions.clear();
			
			for (int i = 0; i < 10; i++) {
				suggestions.add("Mundo " + i);
			}
		}
		
		for (String suggestion : suggestions) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, suggestion + " Basado en: " + text + " (" + title + ")", "Sin detalles");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, message);
		}
	}

}
