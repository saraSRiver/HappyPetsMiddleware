package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.ServicioOfrecido;
import com.happypets.aplicacion.service.DataException;

public interface ServicioOfrecidoDAO {
		public List<ServicioOfrecido> findByCuidador(Connection conection,Long idCuidador) throws DataException;
		public ServicioOfrecido findByCuidadorServicio(Connection conection,
				Long idCuidador, Long idServicio) throws DataException;
		public void create (Connection conection,ServicioOfrecido so)throws DataException;
		public boolean update(Connection conection,ServicioOfrecido so) throws DataException;
		public boolean deleteByIdCuidador(Connection conection,Long idCuidador)throws DataException;

}
