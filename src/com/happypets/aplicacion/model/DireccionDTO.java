package com.happypets.aplicacion.model;

public class DireccionDTO extends ValueObject{
	private Long idDireccion;
	private Long idPoblacion;
	private Long idcliente;
	private Long idcuidador;
	private Long idProvincia;
	private Long idpais;
	private String calle;
	private Integer portal;
	private Integer cp;
	private Integer piso;
	private String nombrePoblacion;
	private String nombreProvincia;
	private String nombrePais;
	
	public DireccionDTO() {
		
	}
	
	public Long getIdDireccion() {
		return idDireccion;
	}


	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}


	public Long getIdPoblacion() {
		return idPoblacion;
	}


	public void setIdPoblacion(Long idPoblacion) {
		this.idPoblacion = idPoblacion;
	}


	public Long getIdcliente() {
		return idcliente;
	}


	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}


	public Long getIdcuidador() {
		return idcuidador;
	}


	public void setIdcuidador(Long idcuidador) {
		this.idcuidador = idcuidador;
	}


	public Long getIdProvincia() {
		return idProvincia;
	}


	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}


	public Long getIdpais() {
		return idpais;
	}


	public void setIdpais(Long idpais) {
		this.idpais = idpais;
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
	public String getNombrePoblacion() {
		return nombrePoblacion;
	}
	public void setNombrePoblacion(String nombrePoblacion) {
		this.nombrePoblacion = nombrePoblacion;
	}
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	public String getNombrePais() {
		return nombrePais;
	}
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
}
