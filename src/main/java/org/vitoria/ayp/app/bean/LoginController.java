package org.vitoria.ayp.app.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vitoria.ayp.app.vo.Usuario;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getRootLogger();

	
	private Usuario usuario;
	
	private boolean logeado;
	private String nombre;
	private String password;

	@PostConstruct
	public void postConstruct() {
		this.logeado = false;
		this.usuario = null;
		this.nombre = "";
		this.password = "";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	
	public String login() {
		
		LOG.trace("submitado formulario de login");
		String view = "/login";
		
		if ( "admin".equals(nombre) && "admin".equals(password)) {
			
			this.setLogeado(true);			
			this.setUsuario( new Usuario(34,"Administrador", "", "admin@vitoria.org") );
			view = "/clase?faces-redirect=true";
			
		}else {
			this.setUsuario(null);
			this.setLogeado(false);
			
			//mensaje personalizado
			String sumary = "SUMARY: Credenciales Incorrectas";
			String detail = "DETAIL: Credenciales Incorrectas";
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, sumary, detail);
			FacesContext fc = FacesContext.getCurrentInstance();
			
			// si id null es un mensaje Global
			String clientId = null; 
			fc.addMessage(clientId, fm);
			
		}
		
		return view;
	}
	
	
	public void checkLoginEvent ( ComponentSystemEvent event ) {
		
		LOG.trace("Evento Seguirdad Comprobar usuario logeado");
		if (!this.isLogeado()) {
			
			FacesContext context = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler handler = 
					(ConfigurableNavigationHandler) 
					context.getApplication()
					.getNavigationHandler();

			handler.performNavigation("/login");
		}
		
		
	}
	
	public String logout() {
		this.setUsuario(null);
		this.setLogeado(false);
		return "/login?faces-redirect=true";
	}

}
