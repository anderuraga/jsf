package org.vitoria.ayp.app.i18n;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@SessionScoped
public class IdiomaController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getRootLogger();
	
	private String locale;
	
	private HashMap<String, Object> idiomas;
	

	@PostConstruct
	public void postConstruct() {
		LOG.trace("postConstruct");
		
		this.locale = "eu";
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
		LOG.debug("idiomas por defecto eu");
		
		this.idiomas = new HashMap<String, Object>();
		this.idiomas.put("Castellano", "es");
		this.idiomas.put("Euskera", "eu");
		
		LOG.debug("idiomas cargados");
	}
	
	@PreDestroy
	public void preDestroy() {
		LOG.trace("preDestroy");
	} 
	
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public HashMap<String, Object> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(HashMap<String, Object> idiomas) {
		this.idiomas = idiomas;
	}
	
	public void cambioIdiomaListener( ValueChangeEvent valueChangeEvent) {
		
		String nuevoIdioma = valueChangeEvent.getNewValue().toString();
		LOG.debug("cambiado idioma " +  nuevoIdioma );
		
		this.locale = nuevoIdioma;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
	}
	

}
