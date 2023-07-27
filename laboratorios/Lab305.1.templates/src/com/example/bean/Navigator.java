package com.example.bean;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Cookie;

@ManagedBean
@ViewScoped
public class Navigator {

	Logger logger = Logger.getLogger("Navigator");

	private String page;

	@PostConstruct
	public void init() {
		Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap()
				.get("page");

		if (cookie != null) {
			String page = cookie.getValue();

			if (page != null && !page.isEmpty()) {
				logger.info("INICIALIZANDO: " + page);

				this.page = page;
			}
		}
		
		if (this.page == null) {
			this.page = "page1";
		}
		
		FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("page", page, null);
	}

	public String getPage() {
		return page != null ? page : "page1";
	}

	public void setPage(String page) {
		this.page = page;
		FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("page", page, null);
	}

	public void firstPage(String page) {
		logger.info("Restaurando página: " + page);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Cargando la página por primera vez: " + page));

		if (page != null) {
			this.page = page;
		}

		logger.info("Abriendo la página principal");

		String path = "/index.xhtml";

		FacesContext context = FacesContext.getCurrentInstance();

		ExternalContext externalContext = context.getExternalContext();

		externalContext.addResponseCookie("page", page, null);

		String uri = externalContext.getRequestContextPath() + path;

		try {
			externalContext.redirect(uri);
		} catch (IOException e) {
			logger.warning("No se pudo redigir la página");
		}
	}

}
