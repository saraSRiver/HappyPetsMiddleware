package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.EstadoDao;
import com.happypets.aplicacion.dao.jdbc.EstadoDaoImpl;
import com.happypets.aplicacion.model.Estado;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.EstadoService;
import com.happypets.aplicacion.util.DBUtils;

public class EstadoServiceImpl implements EstadoService{
	private static Logger logger= 
			LogManager.getLogger(EstadoServiceImpl.class);
	private EstadoDao estadoDao=null;

	public EstadoServiceImpl() {
		estadoDao= new EstadoDaoImpl();
	}


	public boolean update (char idEstado)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			estadoDao.update(conection, idEstado);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return true;

	}
}
