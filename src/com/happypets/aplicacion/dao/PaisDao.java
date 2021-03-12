package com.happypets.aplicacion.dao;

import java.sql.Connection;

import com.happypets.aplicacion.model.Pais;
import com.happypets.aplicacion.service.DataException;

public interface PaisDao {
public Pais findById (Connection conection,Long idPais)throws DataException;

}
