package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ServicioOfrecidoDAO;
import com.happypets.aplicacion.dao.jdbc.ServicioOfrecidoDaoImpl;
import com.happypets.aplicacion.model.ServicioOfrecido;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ServicioOfrecidoService;
import com.happypets.aplicacion.util.DBUtils;

public class ServicioOfrecidoServiceImpl implements
ServicioOfrecidoService{
	private static Logger logger= 
			LogManager.getLogger(ServicioOfrecidoServiceImpl.class);

	private ServicioOfrecidoDAO servicioOfrecidoDao=null;

	public ServicioOfrecidoServiceImpl() {
		servicioOfrecidoDao= new ServicioOfrecidoDaoImpl();
	}
	public List<ServicioOfrecido> findByCuidador(Long idCuidador) 
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<ServicioOfrecido> serviciosOfrecidos  = null;
		try {
			conection.setAutoCommit(false);
			serviciosOfrecidos=servicioOfrecidoDao.findByCuidador
					(conection, idCuidador);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return serviciosOfrecidos;

	}
	public void create (ServicioOfrecido so)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			servicioOfrecidoDao.create(conection, so);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}

	}
	public boolean update(ServicioOfrecido so) throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean serviciosOfrecido  = false;
		try {
			conection.setAutoCommit(false);
			serviciosOfrecido =servicioOfrecidoDao.update(conection, so);
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
	public boolean deleteByIdCuidador(Long idCuidador)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean serviciosOfrecido= false;
		try {
			conection.setAutoCommit(false);
			serviciosOfrecido =servicioOfrecidoDao.deleteByIdCuidador
					(conection, idCuidador);
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
