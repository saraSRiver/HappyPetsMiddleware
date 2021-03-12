package com.happypets.aplicacion.model;



public class ServicioOfrecido extends ValueObject{
	
	private Long idCuidador;
	private Long idServicio;
	private String nombreServicio;
	private Double precio;
	
	
	public ServicioOfrecido() {
		
	}

	public Long getIdCuidador() {
		return idCuidador;
	}

	public void setIdCuidador(Long idCuidador) {
		this.idCuidador = idCuidador;
	}

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}


}
