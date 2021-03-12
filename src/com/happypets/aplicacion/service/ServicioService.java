package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.Servicio;

public interface ServicioService {
	public Servicio findById(Long idServicio, String idioma)  throws DataException;
	public List<Servicio> findAll( String idioma) throws DataException;
	public List<Servicio> findByIdCuidador (Long idCuidador, String idioma)  throws DataException;
}
