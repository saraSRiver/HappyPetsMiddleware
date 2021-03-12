package com.happypets.aplicacion.dao;

import java.sql.Connection;

import com.happypets.aplicacion.model.Cliente;
import com.happypets.aplicacion.model.Promocion;
import com.happypets.aplicacion.service.DataException;

public interface ClienteDao {
public Cliente findById(Connection conection, Long idCliente)throws DataException;
public Cliente findByEmail (Connection conection,String email)throws DataException;
public Cliente create (Connection conection,Cliente cliente)throws DataException;
public Cliente update (Connection conection,Cliente cliente)throws DataException;
public boolean updateIdPromocion (Connection conection, Integer idPromocion, Long idCliente)throws DataException;
public boolean UptEstadoPromocion (Connection conection, boolean estado, Long idCliente)throws DataException;
public boolean delete (Connection conection,Long id)throws DataException;

}
