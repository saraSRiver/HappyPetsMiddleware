package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Cuidador;
import com.happypets.aplicacion.service.CuidadorCriteria;
import com.happypets.aplicacion.service.DataException;

public interface CuidadorDao {
	public Cuidador findById(Connection conection,Long idCuidador)throws DataException;
	public Cuidador findByEmail(Connection conection,String email)throws DataException;
	public List<Cuidador> findByCriteria (Connection conection,CuidadorCriteria cuidadorCriteria)throws DataException;
	public Cuidador create (Connection conection,Cuidador cuidador)throws DataException;
	public Cuidador update (Connection conection,Cuidador cuidador)throws DataException;
	public boolean delete (Connection conection,Long idCuidador)throws DataException;
	
}
