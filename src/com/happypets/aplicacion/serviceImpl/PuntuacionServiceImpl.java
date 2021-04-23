package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.PuntuacionDao;
import com.happypets.aplicacion.dao.jdbc.PuntuacionDaoImpl;
import com.happypets.aplicacion.model.Puntuacion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.PuntuacionService;
import com.happypets.aplicacion.util.DBUtils;

public class PuntuacionServiceImpl implements PuntuacionService{
	private static Logger logger= 
			LogManager.getLogger(PuntuacionServiceImpl.class);
	private PuntuacionDao puntuacionDao= null;
	public PuntuacionServiceImpl() {
		puntuacionDao= new PuntuacionDaoImpl();
	}

	public List<Puntuacion> findByidCliente(Long idCliente)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Puntuacion> putuaciones  = null;
		try {
			conection.setAutoCommit(false);
			putuaciones=puntuacionDao.findByidCliente(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return putuaciones;

	}
	public Double findByMedia (Long idCuidador)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Double puntuacion = null;
		try {
			conection.setAutoCommit(false);
			puntuacion = puntuacionDao.findByMedia(conection, idCuidador);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return puntuacion;

	}
	public void create (Puntuacion pt) throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			puntuacionDao.create(conection, pt);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}

	}
	public boolean update (Puntuacion pt)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean puntuacion = false;
		try {
			conection.setAutoCommit(false);
			puntuacion =puntuacionDao.update(conection, pt);
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

	@Override
	public Puntuacion findPuntuacion(Long idCliente, Long idCuidador) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Puntuacion puntuacion = null;
		try {
			conection.setAutoCommit(false);
			puntuacion = puntuacionDao.findPuntuacion(conection, idCliente, idCuidador);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return puntuacion;
	}

}
