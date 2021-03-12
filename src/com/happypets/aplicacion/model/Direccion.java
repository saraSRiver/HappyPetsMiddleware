package com.happypets.aplicacion.model;

public class Direccion extends ValueObject{
	private Long idDireccion;
	private String calle;
	private Integer portal;
	private Integer cp;
	private Integer piso;


	public Direccion() {

	}

	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getPortal() {
		return portal;
	}

	public void setPortal(Integer portal) {
		this.portal = portal;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}


}
