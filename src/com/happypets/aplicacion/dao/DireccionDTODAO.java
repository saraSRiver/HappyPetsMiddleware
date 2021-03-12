package com.happypets.aplicacion.dao;

import java.sql.Connection;

import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DataException;

public interface DireccionDTODAO {
	public DireccionDTO findByIdCliente (Connection conection, Long idCliente)throws DataException;
	public DireccionDTO findByIdCuidador (Connection conection, Long idCuidador)throws DataException;
}
