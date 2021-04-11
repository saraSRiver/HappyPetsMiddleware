package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.ContratoDTO;
import com.happypets.aplicacion.service.DataException;

public interface ContratoDTODAO {
	public List<ContratoDTO> findByIdCliente(Connection conection, Long idCliente)throws DataException;
	public List<ContratoDTO> findByIdCuidador(Connection conection, Long idCuidador)throws DataException;
}
