package com.example.bean;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

@ManagedBean
@RequestScoped
public class Bean {

	Logger logger = Logger.getLogger("Bean");

	Part file; // phase 2

	String fullname;
	String name;
	String contentType;
	long size;
	
	
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public String getFullname() {
		return fullname;
	}

	public String getContentType() {
		return contentType;
	}

	public long getSize() {
		return size;
	}

	public void upload() { // phase 5

		logger.info("Cargando archivo");

		if (file != null) {
			logger.info("Archivo recibido");

			fullname = file.getSubmittedFileName();
			String[] parts = fullname.split("\\\\"); // C:\...\...\archivo.pdf
			name = parts[parts.length - 1];
			contentType = file.getContentType();
			size = file.getSize();

			logger.info("Archivo: " + name + " " + contentType + " " + size + " bytes");

			// TODO: Escribir el archivo en el servidor
			try {
				file.write(name);
				file.delete();
				logger.info("Archivo escrito: " + name);
			} catch (IOException e) {
				logger.warning(e.getMessage());
			}

		} else {
			logger.info("No se envió ningún archivo");
		}

	}

}
