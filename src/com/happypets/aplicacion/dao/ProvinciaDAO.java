package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Provincia;
import com.happypets.aplicacion.service.DataException;

public interface ProvinciaDAO {
	public Provincia findById(Connection conection,Long idProvincia)throws DataException;
	public List<Provincia> findAll(Connection conection) throws DataException;
	//public List<Provincia> findByPais(Long idPais) throws DataException; 
//	public Provincia findByNombre (String nombre);
	
}
