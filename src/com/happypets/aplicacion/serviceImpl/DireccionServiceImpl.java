package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.DireccionDao;
import com.happypets.aplicacion.dao.jdbc.DireccionDaoImpl;
import com.happypets.aplicacion.model.Direccion;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.DireccionService;
import com.happypets.aplicacion.util.DBUtils;

public class DireccionServiceImpl implements DireccionService{
	private static Logger logger= 
			LogManager.getLogger(DireccionServiceImpl.class);
	private DireccionDao direccionDao=null;

	public DireccionServiceImpl() {
		direccionDao= new DireccionDaoImpl();
	}

	public Direccion findById (Long idDireccion) throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Direccion direccion = null;
		try {
			conection.setAutoCommit(false);
			direccion =direccionDao.findById(conection, idDireccion);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return direccion;
	}
	public Direccion createDirCuidador (DireccionDTO dirDto)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Direccion direccion = null;
		try {
			conection.setAutoCommit(false);
			direccion =direccionDao.createDirCuidador(conection, dirDto);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return direccion;

	}
	public Direccion createDirCliente (DireccionDTO dirDto)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Direccion direccion = null;
		try {
			conection.setAutoCommit(false);
			direccion =direccionDao.createDirCliente(conection, dirDto);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return direccion;

	}
	public Direccion update (DireccionDTO dirDto)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Direccion direccion = null;
		try {
			conection.setAutoCommit(false);
			direccion =direccionDao.update(conection, dirDto);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return direccion;

	}
	public boolean deleteByCliente(Long idCliente)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean direccion = false;
		try {
			conection.setAutoCommit(false);
			direccion =direccionDao.deleteByCliente(conection, idCliente);
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
	public boolean deleteByCuidador(Long idCuidador)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean direccion = false;
		try {
			conection.setAutoCommit(false);
			direccion =direccionDao.deleteByCuidador(conection, idCuidador);
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
