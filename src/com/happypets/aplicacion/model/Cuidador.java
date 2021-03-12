package com.happypets.aplicacion.model;

import java.util.ArrayList;
import java.util.List;

public class Cuidador extends Usuario{
	private Long idcuidador;
	private Experiencia experiencia;
	private List<TipoEspecie> especies;
	private List<ServicioOfrecido> serviciosOfrecidos;
	private double puntuacionMedia;
	private boolean estadoActivo;
	
	
	public Cuidador() {
		especies = new ArrayList<TipoEspecie>();
		serviciosOfrecidos= new ArrayList<ServicioOfrecido>();

	}
	public Long getIdcuidador() {
		return idcuidador;
	}
	public void setIdcuidador(Long idcuidador) {
		this.idcuidador = idcuidador;
	}
	public Experiencia getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(Experiencia experiencia) {
		this.experiencia = experiencia;
	}
	

	public List<TipoEspecie> getEspecies() {
		return especies;
	}
	public void setEspecies(List<TipoEspecie> especies) {
		this.especies = especies;
	}
	public List<ServicioOfrecido> getServiciosOfrecidos() {
		return serviciosOfrecidos;
	}
	public void setServiciosOfrecidos(List<ServicioOfrecido> serviciosOfrecidos) {
		this.serviciosOfrecidos = serviciosOfrecidos;
	}
	public double getPuntuacionMedia() {
		return puntuacionMedia;
	}
	public void setPuntuacionMedia(double puntuacionMedia) {
		this.puntuacionMedia = puntuacionMedia;
	}
	public void add(TipoEspecie especie) {
		this.especies.add(especie);
	}
	public void add(ServicioOfrecido servicioOfrecido) {
		this.serviciosOfrecidos.add(servicioOfrecido);
	}
	public boolean isEstadoActivo() {
		return estadoActivo;
	}
	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}

}
