package com.happypets.aplicacion.service;



import com.happypets.aplicacion.model.DireccionDTO;

public interface DireccionDTOService {
	public DireccionDTO findByIdCliente (Long idCliente)throws DataException;
	public DireccionDTO findByIdCuidador (Long idCuidador)throws DataException;
}
