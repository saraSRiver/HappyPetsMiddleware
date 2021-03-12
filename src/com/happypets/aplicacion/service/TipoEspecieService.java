package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.TipoEspecie;

public interface TipoEspecieService {
	public TipoEspecie findById (Long idTipoEspecie, String idioma)throws DataException;
	public List<TipoEspecie> findAll(String idioma) throws DataException;
	public List<TipoEspecie> findByIdCuidador (Long idCuidador, String idioma)throws DataException;
	
	public boolean deleteByIdCuidador(Long idCuidador) throws DataException;
		
	
}
