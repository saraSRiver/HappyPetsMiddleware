package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.PoblacionDao;
import com.happypets.aplicacion.dao.jdbc.PoblacionDaoImpl;
import com.happypets.aplicacion.model.Poblacion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.PoblacionService;
import com.happypets.aplicacion.util.DBUtils;

public class PoblacionServiceImpl implements PoblacionService{
	private static Logger logger= 
			LogManager.getLogger(PoblacionServiceImpl.class);
	private PoblacionDao poblacionDao=null;

	public PoblacionServiceImpl() {
		poblacionDao= new PoblacionDaoImpl();
	}


	public Poblacion findById (Long idPoblacion)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Poblacion poblacion  = null;
		try {
			conection.setAutoCommit(false);
			poblacion  =poblacionDao.findById(conection, idPoblacion);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return poblacion;
	}
	public List<Poblacion> findAll() throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Poblacion> poblaciones  = null;

		try {
			conection.setAutoCommit(false);
			poblaciones  = poblacionDao.findAll(conection);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return poblaciones ;

	}
	public List<Poblacion> findByProvincia(Long idProvincia)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Poblacion> poblaciones  = null;
		try {
			conection.setAutoCommit(false);
			poblaciones  =poblacionDao.findByProvincia(conection,idProvincia);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return poblaciones ;
	}
}
