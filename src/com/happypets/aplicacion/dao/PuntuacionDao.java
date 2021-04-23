package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Puntuacion;
import com.happypets.aplicacion.service.DataException;

public interface PuntuacionDao {
	public List<Puntuacion> findByidCliente(Connection conection,Long idCliente)throws DataException;
	public Double findByMedia (Connection conection,Long idCuidador)throws DataException;
	public Puntuacion findPuntuacion(Connection conection, Long idCliente, Long idCuidador)throws DataException;
	public void create (Connection conection,Puntuacion pt) throws DataException;
	public boolean update (Connection conection,Puntuacion pt)throws DataException;


}
