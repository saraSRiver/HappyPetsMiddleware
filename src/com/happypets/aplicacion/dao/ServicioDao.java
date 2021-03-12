package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Servicio;
import com.happypets.aplicacion.service.DataException;

public interface ServicioDao {
	public Servicio findById(Connection conection,Long idServicio, String idioma)  throws DataException;
	public List<Servicio> findAll(Connection conection, String idioma) throws DataException;
	public List<Servicio> findByIdCuidador (Connection conection,Long idCuidador, String idioma)  throws DataException;
	
}
