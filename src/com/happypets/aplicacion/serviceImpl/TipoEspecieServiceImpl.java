package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.TipoEspecieDao;
import com.happypets.aplicacion.dao.jdbc.TipoEspecieDaoImpl;

import com.happypets.aplicacion.model.TipoEspecie;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.TipoEspecieService;
import com.happypets.aplicacion.util.DBUtils;

public class TipoEspecieServiceImpl implements TipoEspecieService{
	private static Logger logger= 
			LogManager.getLogger(TipoEspecieServiceImpl.class);
	private TipoEspecieDao tipoEspecieDao= null;

	public TipoEspecieServiceImpl() {
		tipoEspecieDao= new TipoEspecieDaoImpl();
	}
	public TipoEspecie findById (Long idTipoEspecie, String idioma)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		TipoEspecie especie = null;
		try {
			conection.setAutoCommit(false);
			especie =tipoEspecieDao.findById(conection, idTipoEspecie,idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return especie;
	}
	public List<TipoEspecie> findAll( String idioma) throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<TipoEspecie> especies  = null;
		try {
			conection.setAutoCommit(false);
			especies=tipoEspecieDao.findAll(conection,idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return especies;

	}
	public List<TipoEspecie> findByIdCuidador (Long idCuidador, String idioma)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<TipoEspecie> especies  = null;
		try {
			conection.setAutoCommit(false);
			especies=tipoEspecieDao.findByIdCuidador
					(conection, idCuidador,idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return especies;

	}
	public boolean deleteByIdCuidador(Long idCuidador)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			tipoEspecieDao.deleteByIdCuidador
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
