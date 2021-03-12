package com.happypets.aplicacion.model;

public class Experiencia extends ValueObject{
	private Integer idExperiencia;
	private String valor;
	
	public Experiencia() {
		
	}

	public Integer getIdExperiencia() {
		return idExperiencia;
	}

	public void setIdExperiencia(Integer idExperiencia) {
		this.idExperiencia = idExperiencia;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
