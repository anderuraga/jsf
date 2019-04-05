package org.vitoria.ayp.app.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="conversorDni")
public class DniConverter implements Converter {

	/**
	 * Recibimos de un Input
	 */
	
	public Object getAsObject(FacesContext context, UIComponent component, String value)  {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Renderrizar en HTML
	 */
	
	public String getAsString(FacesContext context, UIComponent component, Object value){
		
		String resul = "-- dni incorrecto --";
		try {
			String dni = (String)value;		
			String separator = (String) component.getAttributes().get("separador");
			String numero = dni.substring(0,7); 
			String letra = dni.substring(8,9);
			resul = numero + separator + letra;
		}catch (Exception e) {		
			e.printStackTrace();
		}	
		return  resul;
	}

}

























