package com.happypets.aplicacion.model;

public class TipoEspecie extends ValueObject{
	private Long idTipoEspecie;
	private String nombre;

	public TipoEspecie() {

	}

	public Long getIdTipoEspecie() {
		return idTipoEspecie;
	}

	public void setIdTipoEspecie(Long idTipoEspecie) {
		this.idTipoEspecie = idTipoEspecie;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
