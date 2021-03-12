package com.happypets.aplicacion.model;

public class Servicio extends ValueObject{
	private Long idServicio;
	private String nombreServicio;
	
	
	public Servicio () {
		
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
	
	}
