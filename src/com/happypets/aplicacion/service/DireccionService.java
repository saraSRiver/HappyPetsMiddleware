package com.happypets.aplicacion.service;

import com.happypets.aplicacion.model.Direccion;
import com.happypets.aplicacion.model.DireccionDTO;

public interface DireccionService {
	public Direccion findById (Long idDireccion) throws DataException;
	public Direccion createDirCuidador (DireccionDTO dirDto)
			throws DataException;
	public Direccion createDirCliente (DireccionDTO dirDto)
			throws DataException;
	public Direccion update (DireccionDTO dirDto)
			throws DataException; 
	public boolean deleteByCliente(Long idCliente)throws DataException;
	public boolean deleteByCuidador(Long idCuidador)throws DataException;
}

