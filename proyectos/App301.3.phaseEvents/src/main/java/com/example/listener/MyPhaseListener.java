package com.example.listener;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MyPhaseListener implements PhaseListener {

	Logger logger = Logger.getLogger("MyPhaseListener");
	
	public void afterPhase(PhaseEvent event) {
		logger.info("Saliendo de la sexta fase");
		
		FacesContext context = event.getFacesContext();
		
		UIViewRoot viewRoot = context.getViewRoot();
		
		for (UIComponent child : viewRoot.getChildren()) {
			logger.info(child.toString());
		}
	}

	public void beforePhase(PhaseEvent event) {
		logger.info("Entrando a la sexta fase");
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
