package com.happypets.aplicacion.model;

public class Idioma extends ValueObject{
	private String idIdioma;
	private String idioma;

	public Idioma() {

	}

	public String getIdIdioma() {
		return idIdioma;
	}


	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}



}
