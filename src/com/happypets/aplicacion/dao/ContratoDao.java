package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.service.DataException;

public interface ContratoDao {
	public Contrato findByid (Connection conection,Long idContrato)throws DataException;
	public List<Contrato> findByHistorialCuidador(Connection conection,Long idCuidador)throws DataException;
	public List<Contrato> findByHistorialCliente(Connection conection,Long idCliente)throws DataException;
	public Contrato create (Connection conection,Contrato c)throws DataException;
	public boolean updateEstado(Connection conection,Long idContrato, char idEstado) throws DataException;
	

	
	

}
