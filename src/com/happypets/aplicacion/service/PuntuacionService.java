package com.happypets.aplicacion.service;


import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Puntuacion;

public interface PuntuacionService {
	public List<Puntuacion> findByidCliente(Long idCliente)throws DataException;
	public Double findByMedia (Long idCuidador)throws DataException;
	public Puntuacion findPuntuacion( Long idCliente, Long idCuidador)throws DataException;
	public void create (Puntuacion pt) throws DataException;
	public boolean update (Puntuacion pt)throws DataException;
}
