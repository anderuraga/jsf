package org.vitoria.ayp.app.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vitoria.ayp.app.vo.Alumno;

@Named
@SessionScoped
public class AlumnoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getRootLogger();

	private List<Alumno> alumnos;

	public static final String VIEW_FORM_ALTA = "/alumno/form-nuevo";
	public static final String VIEW_FORM_MODIFICAR = "/alumno/form-modificar";
	public static final String VIEW_LISTADO = "/clase";

	@Valid
	private Alumno alumno;

	public AlumnoBean() {
		super();
		LOG.trace("instanciado");
		this.alumnos = new ArrayList<Alumno>();
		this.alumno = new Alumno();
		mockAlumno();
		getAllAlumnos();

	}

	private void mockAlumno() {

		// TODO quitar luego estos datos harcodeados
		this.alumno = new Alumno();
		this.alumno.setNombre("Pepe");
		this.alumno.setApellido("Gotera");
		this.alumno.setAprobado(false);
		this.alumno.setDni("11111111H");
		this.alumno.setEdad(34);
		this.alumno.setNacimieto(new Date());
		this.alumno.setSueldo(1000f);
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	private void getAllAlumnos() {
		LOG.trace("getAllAlumnos");
		alumnos.add(new Alumno(0, "Ander", "Uraga", 37, 2000f, true, new Date(), "24441215B"));
		alumnos.add(new Alumno(1, "Potele", "De la Herran", 18, 300f, false, new Date(), "1234"));
		alumnos.add(new Alumno(2, "Edurne", "", 57, 2800f, false, new Date(), "12345678G"));
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alummnos) {
		this.alumnos = alummnos;
	}

	/**
	 * Accion crear nuevo Alumno
	 * 
	 * @return view o nombre del xhtml a renderizar
	 */
	public String nuevo() {

		LOG.info("Submitado formulario, accion nuevo");
		String view = "/alumno/form";

		// las validaciones son correctas, por lo cual los datos del formulario estan en
		// el atributo alumno

		// TODO simular index de la bbdd
		int id = this.alumnos.size();
		this.alumno.setId(id);

		LOG.debug("alumno: " + this.alumno);

		// TODO comprobar edad y fecha

		alumnos.add(alumno);
		view = VIEW_LISTADO;
		mockAlumno();

		// mensaje flash
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.getFlash().put("alertTipo", "success");
		externalContext.getFlash().put("alertMensaje", "Alumno " + this.alumno.getNombre() + " creado con exito");

		return view + "?faces-redirect=true";
	}

	public String volver() {

		LOG.info("accion volver listado alumnos");

		return VIEW_LISTADO;
	}
	
	
	public void checkNombre( AjaxBehaviorEvent ajaxBehaviorEvent ) {
		
		LOG.trace("checkNombre");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();		
		HtmlOutputText outputText = (HtmlOutputText) uiViewRoot.findComponent("alumnoForm:nombreOutput");
		
		if ( ! "admin".equals(this.alumno.getNombre()) ) {
			outputText.setValue("YEAhhhhhhhhhhh  Nombre Disponible");
			outputText.setStyleClass("text-success");
		}else {		
			outputText.setValue("Opppppss Nombre NO Disponible");
			outputText.setStyleClass("text-danger");
		}	
		
	}
	

	public void cambioApellidoListener(ValueChangeEvent valueChangeEvent) {

		LOG.trace("apellido modificado");

		final String APELLIDO_NO_DISPONIBLE = "manolez";

		String apelldioOld = valueChangeEvent.getOldValue().toString();
		String apelldioNew = valueChangeEvent.getNewValue().toString();

		LOG.debug(String.format("Apellido Viejo= %s  Apellido Nuevo=%s", apelldioOld, apelldioNew));

		// buscar componente por su id, recordar poner id tambien al formulario
		// formularioID:inputID
		String idInput = "alumnoForm:apellido";
		String idOutput = "alumnoForm:apellidoOutput";

		FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();

		UIInput uiInput = (UIInput) uiViewRoot.findComponent(idInput);
		HtmlOutputText outputText = (HtmlOutputText) uiViewRoot.findComponent(idOutput);

		uiInput.setValue("Apellido Cambiado por el ValueChangeLsitener");
		uiInput.setSubmittedValue("SUBMITADO: Apellido Cambiado por el ValueChangeLsitener");

		
		if ( APELLIDO_NO_DISPONIBLE.equalsIgnoreCase(apelldioNew) ) {
		
			outputText.setValue("Lo sentimos pero el Apellido NO esta disponible");
			facesContext.renderResponse();
			
		}else {
			outputText.setValue("");
			LOG.debug("Apellido disponible");
		}
		
		
		

	}

	/**
	 * Escucha el Evento de Submit del formulario
	 * 
	 * @param actionEvent
	 */
	public void modificarListener(ActionEvent actionEvent) {

		LOG.trace("ActionEvent modificar alumno");

	}

	/**
	 * Accion para poder ir a una vista u otra
	 * 
	 * @return String view a mostrar
	 */
	public String modificar() {

		LOG.info("accion crear alumno");
		String view = VIEW_LISTADO;
		view = VIEW_FORM_ALTA;
		this.alumnos.add(this.alumno);
		return view;
	}

	public String modificar(Alumno alumno) {

		LOG.info("accion modificar alumno " + alumno);

		this.alumno = alumno;

		return VIEW_FORM_MODIFICAR;
	}

	public String eliminar(Alumno alumno) {

		LOG.info("accion eliminar alumno " + alumno);

		this.alumnos.remove(alumno);
		return VIEW_LISTADO;
	}

}
