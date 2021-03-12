package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.Poblacion;

public interface PoblacionService {
	public Poblacion findById (Long idPoblacion)throws DataException;
	public List<Poblacion> findAll() throws DataException;
	public List<Poblacion> findByProvincia(Long idProvincia)throws DataException;
}
