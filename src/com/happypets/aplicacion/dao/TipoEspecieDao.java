package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.TipoEspecie;
import com.happypets.aplicacion.service.DataException;


public interface TipoEspecieDao {
	public TipoEspecie findById (Connection conection,Long idTipoEspecie, String idioma)
			throws DataException;
	public List<TipoEspecie> findAll(Connection conection, String idioma)
			throws DataException;
	public List<TipoEspecie> findByIdCuidador (Connection conection,
			Long idCuidador, String idioma)throws DataException;
	
	public boolean deleteByIdCuidador(Connection conection,Long idCuidador)
			throws DataException;
		
	
	
}
