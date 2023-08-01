package com.example.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class ThemeBean {

	private String currentTheme = "saga";

	private List<SelectItem> themes;

	@PostConstruct
	public void init() {
		themes = new ArrayList<SelectItem>();

		SelectItemGroup group1 = new SelectItemGroup("Temas Oficiales");

		group1.setSelectItems(new SelectItem[] { new SelectItem("saga", "Saga"), new SelectItem("vela", "Vela"),
				new SelectItem("arya", "Arya"), });

		themes.add(group1);

		SelectItemGroup group2 = new SelectItemGroup("Temas de Material");

		group2.setSelectItems(new SelectItem[] { new SelectItem("material-light", "Material Light"),
				new SelectItem("material-dark", "Material Dark"), });

		themes.add(group2);

		SelectItemGroup group3 = new SelectItemGroup("Temas de Bootstrap");

		group3.setSelectItems(new SelectItem[] { new SelectItem("bootstrap-light", "Bootstrap Light"),
				new SelectItem("bootstrap-dark", "Bootstrap Dark"), });

		themes.add(group3);

		SelectItemGroup group4 = new SelectItemGroup("Temas Personalizado", "Nuestros propios temas", true, 
				new SelectItem[] { 
						new SelectItem("mexico-1", "Mexico Formal"),
						new SelectItem("mexico-2", "Mexico Folclorico") 
				});

		themes.add(group4);
	}

	public List<SelectItem> getThemes() {
		return themes;
	}

	public String getCurrentTheme() {
		return currentTheme;
	}

	public void setCurrentTheme(String currentTheme) {
		this.currentTheme = currentTheme;

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tema actualizado",
				"Se cambió el tema por: " + currentTheme);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
