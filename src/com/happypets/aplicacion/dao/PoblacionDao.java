package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Poblacion;
import com.happypets.aplicacion.service.DataException;

public interface PoblacionDao {
	public Poblacion findById (Connection conection,Long idPoblacion)throws DataException;
	public List<Poblacion> findAll(Connection conection) throws DataException;
	public List<Poblacion> findByProvincia(Connection conection,Long idProvincia)throws DataException;

}
