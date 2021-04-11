package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.ContratoDTO;

public interface ContratoDTOService {
	public List<ContratoDTO> findByIdCliente(Long idCliente)throws DataException;
	public List<ContratoDTO> findByIdCuidador(Long idCuidador)throws DataException;
}
