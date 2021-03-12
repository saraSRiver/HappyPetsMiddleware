package com.happypets.aplicacion.service;

public class CuidadorCriteria {
	// Codigo de los años de experiencia
	private Integer  idExperiencia;
	// ID del tipo de especie que se buscar atender
	private Long idTipoEspecie;
	// ID del tipo de servicio que ofrece el cuidador
	private Long idServicio;
	// límite de salida de precio
	private Double precioDesde;
	// límite final de precio
	private Double precioHasta;
	// ID de la población en la que vive el cuidador
	private Long idPoblacion;
	// ID del idioma que habla el cuidador
	private String idIdioma;
	
	
	public CuidadorCriteria() {
		
	}


	public Integer getIdExperiencia() {
		return idExperiencia;
	}


	public void setIdExperiencia(Integer idExperiencia) {
		this.idExperiencia = idExperiencia;
	}


	public Long getIdTipoEspecie() {
		return idTipoEspecie;
	}


	public void setIdTipoEspecie(Long idTipoEspecie) {
		this.idTipoEspecie = idTipoEspecie;
	}


	public Long getIdServicio() {
		return idServicio;
	}


	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}


	public Double getPrecioDesde() {
		return precioDesde;
	}


	public void setPrecioDesde(Double precioDesde) {
		this.precioDesde = precioDesde;
	}


	public Double getPrecioHasta() {
		return precioHasta;
	}


	public void setPrecioHasta(Double precioHasta) {
		this.precioHasta = precioHasta;
	}


	public Long getIdPoblacion() {
		return idPoblacion;
	}


	public void setIdPoblacion(Long idPoblacion) {
		this.idPoblacion = idPoblacion;
	}


	public String getIdIdioma() {
		return idIdioma;
	}


	public void setIdIdioma(String string) {
		this.idIdioma = string;
	}

	
}
