package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.Provincia;

public interface ProvinciaService {
	public Provincia findById(Long idProvincia)throws DataException;
	public List<Provincia> findAll() throws DataException;
}
