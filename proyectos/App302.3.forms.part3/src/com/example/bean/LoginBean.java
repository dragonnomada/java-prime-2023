package com.example.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

@ManagedBean
@RequestScoped
public class LoginBean {

	private String user;
	private String password;
	private boolean remember;
	private String serverName;
	private List<String> servers;
	private String userType;
	private List<String> userTypes;
	private List<String> permissions;
	// private List<String> availablePermissions; // Map<String, String>
	private Map<String, String> availablePermissions;
	private List<String> extras;
//	private List<String> availableExtras; // List<SelectItem>
	private List<SelectItem> availableExtras;

	@PostConstruct
	public void init() {
		permissions = new ArrayList<String>();
		extras = new ArrayList<String>();

		servers = Arrays.asList("BACKUP", "BACKUP_2", "BACKUP_3", "BACKUP_4", "DEV", "TEST");
		userTypes = Arrays.asList("ADMINISTRADOR", "NORMAL", "INVITADO", "AUDITOR");
		// availablePermissions = Arrays.asList("READ", "WRITE", "PAYMENT", "INVOICE", "INVENTARIOS:INPUT", "INVENTARIOS:OUTPUT");
		availablePermissions = new LinkedHashMap<String, String>();
		availablePermissions.put("Lectura", "READ");
		availablePermissions.put("Escritura", "WRITE");
		availablePermissions.put("Pagos", "PAYMENT");
		availablePermissions.put("Facturación", "INVOICE");
		availablePermissions.put("Entrada a Inventarios", "INVENTARIOS:INPUT");
		availablePermissions.put("Salida a Inventarios", "INVENTARIOS:OUTPUT");
		// availableExtras = Arrays.asList("SESSION_1_HOUR", "UPLOAD_1_GB", "ADD_USER");
		SelectItemGroup group1 = new SelectItemGroup("Tipo de Sesión");
		group1.setSelectItems(new SelectItem[] {
				new SelectItem("SESSION_1_HOUR", "Sesión de 1 hora"),
				new SelectItem("SESSION_2_HOUR", "Sesión de 2 horas"),
				new SelectItem("SESSION_3_HOUR", "Sesión de 3 horas"),
				new SelectItem("SESSION_12_HOUR", "Sesión de 12 horas"),
				new SelectItem("SESSION_24_HOUR", "Sesión de 24 horas"),
		});
		
		SelectItemGroup group2 = new SelectItemGroup("Configuraciones de Subida");
		group2.setSelectItems(new SelectItem[] {
				new SelectItem("UPLOAD_50_MB", "Límite de 50mb"),
				new SelectItem("UPLOAD_150_MB", "Límite de 150mb"),
				new SelectItem("UPLOAD_500_MB", "Límite de 500mb"),
				new SelectItem("UPLOAD_1_GB", "Límite de 1GB"),
				new SelectItem("UPLOAD_5_GB", "Límite de 5GB"),
		});
		
		availableExtras = Arrays.asList(group1, group2);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public List<String> getExtras() {
		return extras;
	}

	public void setExtras(List<String> extras) {
		this.extras = extras;
	}

	public List<String> getServers() {
		return servers;
	}

	public List<String> getUserTypes() {
		return userTypes;
	}

//	public List<String> getAvailableExtras() {
//		return availableExtras;
//	}

	public List<SelectItem> getAvailableExtras() {
		return availableExtras;
	}
	
//	public List<String> getAvailablePermissions() {
//	return availablePermissions;
//}

	public Map<String, String> getAvailablePermissions() {
		return availablePermissions;
	}

	private void addMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage(message));
	}

	public void login() {
		addMessage("Iniciando sesión...");
		addMessage("Usuario: " + user);
		addMessage("Contraseña: " + password);
		// private boolean remember;
		addMessage("Recordar usuario: " + (remember ? "SI" : "NO"));
		// private String serverName;
		addMessage("Servidor: " + serverName);
		// private String userType;
		addMessage("Tipo usuario: " + userType);
		// private List<String> permissions;
		if (permissions != null) {
			addMessage("Permisos: " + Arrays.toString(permissions.toArray()));
		}
		// private List<String> extras;
		if (extras != null) {
			addMessage("Extras: " + Arrays.toString(extras.toArray()));
		}

		addMessage("Verificando usuario, contraseña y adicionales...");
		// TODO: Validar usuario y contraseña
		// TODO: loginService.signIn(user, password, remember, ...)
		
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage("myForm:password", new FacesMessage("La contraseña no es válida"));
	}

}
