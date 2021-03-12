package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ServicioDao;
import com.happypets.aplicacion.dao.jdbc.ServicioDaoImpl;

import com.happypets.aplicacion.model.Servicio;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ServicioService;
import com.happypets.aplicacion.util.DBUtils;

public class ServicioServiceImpl implements ServicioService{
	private static Logger logger= 
			LogManager.getLogger(ServicioServiceImpl.class);
	private ServicioDao servicioDao= null;

	public ServicioServiceImpl() {
		servicioDao=new ServicioDaoImpl();
	}
	public Servicio findById(Long idServicio, String idioma)  throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Servicio servicio  = null;
		try {
			conection.setAutoCommit(false);
			servicio  =servicioDao.findById(conection, idServicio,idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return servicio;
	}

	public List<Servicio> findAll( String idioma) throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Servicio> servicios  = null;

		try {
			conection.setAutoCommit(false);
			servicios = servicioDao.findAll(conection,idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return servicios;
	}
	public List<Servicio> findByIdCuidador (Long idCuidador, String idioma)  
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Servicio> servicios  = null;

		try {
			conection.setAutoCommit(false);
			servicios = servicioDao.findByIdCuidador(conection, idCuidador,idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return servicios;
	}
}
