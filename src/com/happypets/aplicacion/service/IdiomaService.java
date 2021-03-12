package com.happypets.aplicacion.service;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Idioma;

public interface IdiomaService {
	public Idioma findByid(String idIdioma)throws DataException;
	public List<Idioma> findByIdiomasCuidador (Long idCuidador) 
			throws DataException;
	public List<Idioma> findByIdiomasCliente (Long idCliente) 
			throws DataException;
	public List<Idioma> findAll()throws DataException;
	public boolean deleteByCliente(Long idCliente)
			throws DataException;
	public boolean deleteByCuidador(Long idCuidador)
			throws DataException;
	
}
