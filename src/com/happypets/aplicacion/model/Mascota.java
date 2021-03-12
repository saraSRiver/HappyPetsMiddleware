package com.happypets.aplicacion.model;


import java.util.Date;


public class Mascota extends ValueObject{
	private Long idMascota;
	private String nombre;
	private String descripcion;
	private Date fechaNacimiento;
	private Boolean vacunado;
	private Boolean buenoConAnimales;
	private Boolean buenoConNinos;
	private Boolean alergia;
	private Boolean tratamiento;
	private Boolean desparasitado;
	private Boolean microchip;
	private Long idTipo;
	private Long idCliente;
	private Date fechaBaja;

	public Mascota() {

	}

	public Long getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(Long idMascota) {
		this.idMascota = idMascota;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Boolean getVacunado() {
		return vacunado;
	}

	public void setVacunado(Boolean vacunado) {
		this.vacunado = vacunado;
	}

	public Boolean getBuenoConAnimales() {
		return buenoConAnimales;
	}

	public void setBuenoConAnimales(Boolean buenoConAnimales) {
		this.buenoConAnimales = buenoConAnimales;
	}

	public Boolean getBuenoConNinos() {
		return buenoConNinos;
	}

	public void setBuenoConNinos(Boolean buenoConNinos) {
		this.buenoConNinos = buenoConNinos;
	}

	public Boolean getAlergia() {
		return alergia;
	}

	public void setAlergia(Boolean alergia) {
		this.alergia = alergia;
	}

	public Boolean getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Boolean tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Boolean getDesparasitado() {
		return desparasitado;
	}

	public void setDesparasitado(Boolean desparasitado) {
		this.desparasitado = desparasitado;
	}

	public Boolean getMicrochip() {
		return microchip;
	}

	public void setMicrochip(Boolean microchip) {
		this.microchip = microchip;
	}


	public Long getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	@Override
	public boolean equals(Object o) {
	System.out.println(" equals"+o);
	//return super.equals(o);
	if(!(o instanceof Mascota)) {
		return false;
	}
	Mascota other= (Mascota)o;
	return this.getNombre().equals(other.getNombre());
	}
	@Override
	public int hashCode() {
		System.out.println(" hashCode "+ super.hashCode());
		//return super.hashCode();
		return getNombre().hashCode();
	}
}
