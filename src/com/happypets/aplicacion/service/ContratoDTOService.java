package com.happypets.aplicacion.service;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.ContratoDTO;

public interface ContratoDTOService {
	public List<ContratoDTO> findByIdCliente(Long idCliente)throws DataException;
}
