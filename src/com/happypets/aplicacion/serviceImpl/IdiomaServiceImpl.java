package com.happypets.aplicacion.serviceImpl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.IdiomaDao;
import com.happypets.aplicacion.dao.jdbc.IdiomaDaoImpl;
import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.IdiomaService;
import com.happypets.aplicacion.util.DBUtils;

public class IdiomaServiceImpl implements IdiomaService{
	private static Logger logger= 
			LogManager.getLogger(IdiomaServiceImpl.class);
	private IdiomaDao idiomaDao=null;

	public IdiomaServiceImpl() {
		idiomaDao= new IdiomaDaoImpl();
	}

	public Idioma findByid(String idIdioma)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Idioma idioma = null;
		try {
			conection.setAutoCommit(false);
			idioma =idiomaDao.findByid(conection, idIdioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return idioma;
	}
	public List<Idioma> findByIdiomasCuidador (Long idCuidador) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Idioma> idiomas  = null;
		try {
			conection.setAutoCommit(false);
			idiomas=idiomaDao.findByIdiomasCuidador(conection, idCuidador);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return idiomas;


	}
	public List<Idioma> findByIdiomasCliente (Long idCliente) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Idioma> idiomas  = null;
		try {
			conection.setAutoCommit(false);
			idiomas=idiomaDao.findByIdiomasCliente(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return idiomas;
	}
	public List<Idioma> findAll()throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Idioma> idiomas  = null;
		try {
			conection.setAutoCommit(false);
			idiomas=idiomaDao.findAll(conection);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return idiomas;


	}
	public boolean deleteByCliente(Long idCliente)  throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean idiomas  = false;
		try {
			conection.setAutoCommit(false);
			idiomas=idiomaDao.deleteByCliente(conection, idCliente);
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
	public boolean deleteByCuidador(Long idCuidador)  throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean idiomas  = false;
		try {
			conection.setAutoCommit(false);
			idiomas=idiomaDao.deleteByCuidador(conection, idCuidador);
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
