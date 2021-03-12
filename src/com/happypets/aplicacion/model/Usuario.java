package com.happypets.aplicacion.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario extends ValueObject{
	private String nombre;
	private String apellidos;
	private String telefono;
	private String password;
	private String email;
	private DireccionDTO direccion;
	private List<Idioma>idiomas;
	private Date fechaBaja;

	public Usuario() {
		idiomas = new ArrayList<Idioma>();
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccionDTO) {
		this.direccion = direccionDTO;
	}


	public List<Idioma> getIdiomas() {
		return idiomas;
	}


	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}


	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public void add(Idioma idioma) {
		this.idiomas.add(idioma);
	}
}
