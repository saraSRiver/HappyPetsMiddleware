package com.happypets.aplicacion.model;

public class Provincia extends ValueObject{
	private Long idProvincia;
	private String nombre; 
	private Long idPais;

	public Provincia() {

	}

	public Long getidProvincia() {
		return idProvincia;
	}

	public void setidProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}
}
