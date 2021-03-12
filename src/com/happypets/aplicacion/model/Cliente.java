package com.happypets.aplicacion.model;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Cliente extends Usuario{
	private Long idcliente;
	private Boolean estadoPromocion;
	private Promocion promocion;
	private List<Mascota>mascotas;
	private boolean estadoActivo;

	public Cliente() {

	}
	public Cliente(Long idcliente) {
		mascotas=new ArrayList<Mascota>();
	}
	public Long getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}
	public Boolean getEstadoPromocion() {
		return estadoPromocion;
	}
	public void setEstadoPromocion(Boolean estadoPromocion) {
		this.estadoPromocion = estadoPromocion;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion Promocion) {
		this.promocion = Promocion;
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}
	public boolean isEstadoActivo() {
		return estadoActivo;
	}
	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoActivo = estadoActivo;
	}
	
	



}
