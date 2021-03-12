package com.happypets.aplicacion.model;

public class Poblacion extends ValueObject{
	private Long idPoblacion;
	private String nombre;
	private Long idProvincia;

	public Poblacion() {

	}

	public Long getIdPoblacion() {
		return idPoblacion;
	}

	public void setIdPoblacion(Long idPoblacion) {
		this.idPoblacion = idPoblacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

}
