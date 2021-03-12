package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Promocion;
import com.happypets.aplicacion.service.DataException;


public interface PromocionDao {
	public Promocion findById (Connection conection,Integer idPromocion)throws DataException;
	public Promocion findByIdCliente (Connection conection,Long idCliente)throws DataException;
	public List<Promocion> findAll(Connection conection)throws DataException;
	
}
