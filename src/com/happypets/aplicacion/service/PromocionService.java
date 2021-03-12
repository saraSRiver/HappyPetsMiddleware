package com.happypets.aplicacion.service;



import java.sql.Connection;


import com.happypets.aplicacion.model.Promocion;

public interface PromocionService {

	public Promocion findById (Integer idPromocion)throws DataException;
	public Promocion findByIdCliente (Long idCliente)throws DataException;
}
