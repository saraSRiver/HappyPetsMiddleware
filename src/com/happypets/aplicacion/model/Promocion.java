package com.happypets.aplicacion.model;

import java.util.Date;

public class Promocion extends ValueObject{
	private Integer idpromocion;
	private Integer porcentajeDescuento;
	private Date dataObtencion;
	private Integer numerador;
	private String TipoPromocion;

	public Promocion() {

	}

	public Integer getIdpromocion() {
		return idpromocion;
	}

	public void setIdpromocion(Integer idpromocion) {
		this.idpromocion = idpromocion;
	}

	public Integer getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Integer porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public Date getDataObtencion() {
		return dataObtencion;
	}

	public void setDataObtencion(Date dataObtencion) {
		this.dataObtencion = dataObtencion;
	}

	public Integer getNumerador() {
		return numerador;
	}

	public void setNumerador(Integer numerador) {
		this.numerador = numerador;
	}

	
	public String getTipoPromocion() {
		return TipoPromocion;
	}

	public void setTipoPromocion(String tipoPromocion) {
		TipoPromocion = tipoPromocion;
	}
}
