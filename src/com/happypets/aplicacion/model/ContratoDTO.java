package com.happypets.aplicacion.model;

import java.util.Date;

public class ContratoDTO extends ValueObject{
	private String nombreCuidador;
	private String apellidosCuidador;
	private String nombreCliente;
	private String apellidosCliente;
	private String nombreMascota;
	private String nombreEspecie;
	private Long idContrato;
	private Date fechaInicio;
	private Date fechaFinal;
	private Double precioFinal;
	private Long idMascota;
	private Long idCuidador;
	private Long idCliente;
	private char idEstado;
	private Date fechaContrato;
	private Long idServicio;
	private String nombreServicio;
	public ContratoDTO() {

	}
	public Long getIdCuidador() {
		return idCuidador;
	}

	
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getNombreCuidador() {
		return nombreCuidador;
	}
	public void setNombreCuidador(String nombreCuidador) {
		this.nombreCuidador = nombreCuidador;
	}
	public String getApellidosCuidador() {
		return apellidosCuidador;
	}
	public void setApellidosCuidador(String apellidosCuidador) {
		this.apellidosCuidador = apellidosCuidador;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidosCliente() {
		return apellidosCliente;
	}
	public void setApellidosCliente(String apellidosCliente) {
		this.apellidosCliente = apellidosCliente;
	}
	public String getNombreMascota() {
		return nombreMascota;
	}
	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}

	public Long getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public Double getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}
	public Long getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(Long idMascota) {
		this.idMascota = idMascota;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public char getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(char idEstado) {
		this.idEstado = idEstado;
	}
	public Date getFechaContrato() {
		return fechaContrato;
	}
	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}
	public Long getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	public void setIdCuidador(Long idCuidador) {
		this.idCuidador = idCuidador;
	}
	public String getNombreEspecie() {
		return nombreEspecie;
	}
	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
	}

}
