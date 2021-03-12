package com.happypets.aplicacion.dao;

import java.sql.Connection;

import com.happypets.aplicacion.model.Direccion;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DataException;


public interface DireccionDao {
	public Direccion findById (Connection conection,Long idDireccion) throws DataException;
	public Direccion createDirCuidador (Connection conection,DireccionDTO dirDto)throws DataException;
	public Direccion createDirCliente (Connection conection,DireccionDTO dirDto)throws DataException;
	public Direccion update (Connection conection,DireccionDTO dirDto)throws DataException; 
	public boolean deleteByCliente(Connection conection,Long idCliente)throws DataException;
	public boolean deleteByCuidador(Connection conection,Long idCuidador)throws DataException;
}
