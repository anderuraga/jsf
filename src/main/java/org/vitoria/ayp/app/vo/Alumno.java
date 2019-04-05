package org.vitoria.ayp.app.vo;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Alumno {

	private long id;
	
	@NotNull
	@Size(min=2, max=150, message="Nombre no es correcto!!!!")	
	private String nombre;
	
	private String apellido;
	
	@NotNull
	@Min(18)
	@Max(99)
	private int edad;
	
	private float sueldo;
	
	private boolean aprobado;
	
	@Past
	private Date nacimieto;
	
	@Pattern(regexp="\\d{8}[A-Z]", message="8 numeros más letra mayuscula sin espacios ")
	private String dni;

	public Alumno() {
		super();
		this.id = -1;
		this.nombre = "";
		this.apellido = "";
		this.edad = 0;
		this.sueldo = 0;
		this.aprobado = false;
		this.nacimieto = new Date();
		this.dni = "";
	}

	public Alumno(long id, String nombre, String apellido, int edad, float sueldo, boolean aprobado, Date nacimieto, String dni) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.sueldo = sueldo;
		this.aprobado = aprobado;
		this.nacimieto = nacimieto;
		this.dni = dni;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	public Date getNacimieto() {
		return nacimieto;
	}

	public void setNacimieto(Date nacimieto) {
		this.nacimieto = nacimieto;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", sueldo="
				+ sueldo + ", aprobado=" + aprobado + ", nacimieto=" + nacimieto + ", dni=" + dni + "]";
	}

}
