package com.happypets.aplicacion.model;

public class Estado extends ValueObject{
	private char idEstado;

	public Estado() {

	}

	public char getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(char idEstado) {
		this.idEstado = idEstado;
	}

}
