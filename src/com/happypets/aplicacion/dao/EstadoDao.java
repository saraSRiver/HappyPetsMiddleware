package com.happypets.aplicacion.dao;

import java.sql.Connection;

import com.happypets.aplicacion.model.Estado;
import com.happypets.aplicacion.service.DataException;

public interface EstadoDao {
	
	public boolean update (Connection conection,char idEstado)throws DataException;
	//public boolean delete (Long idEstado)throws DataException;
}
