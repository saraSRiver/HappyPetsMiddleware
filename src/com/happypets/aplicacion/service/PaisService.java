package com.happypets.aplicacion.service;



import java.sql.SQLException;

import com.happypets.aplicacion.model.Pais;

public interface PaisService {
	public Pais findById (Long idPais)throws DataException, SQLException;
}
