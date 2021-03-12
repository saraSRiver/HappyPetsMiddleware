package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.service.DataException;

public interface IdiomaDao {
	public Idioma findByid(Connection conection,String idIdioma)throws DataException;
	public List<Idioma> findByIdiomasCuidador (Connection conection,Long idCuidador) throws DataException;
	public List<Idioma> findByIdiomasCliente (Connection conection,Long idCliente) throws DataException;
	public List<Idioma> findAll(Connection conection)throws DataException;
	public boolean deleteByCliente(Connection conection,Long idCliente)throws DataException;
	public boolean deleteByCuidador(Connection conection,Long idCuidador)throws DataException;
	
}
