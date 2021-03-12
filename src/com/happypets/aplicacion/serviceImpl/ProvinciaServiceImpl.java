package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ProvinciaDAO;
import com.happypets.aplicacion.dao.jdbc.ProvinciaDaoImpl;

import com.happypets.aplicacion.model.Provincia;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ProvinciaService;
import com.happypets.aplicacion.util.DBUtils;

public class ProvinciaServiceImpl  implements ProvinciaService{
	private static Logger logger= 
			LogManager.getLogger(ProvinciaServiceImpl.class);
	private ProvinciaDAO provinciaDao=null;

	public ProvinciaServiceImpl() {
		provinciaDao= new ProvinciaDaoImpl();
	}
	public Provincia findById(Long idProvincia)throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Provincia provincia  = null;
		try {
			conection.setAutoCommit(false);
			provincia = provinciaDao.findById(conection, idProvincia);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return provincia;

	}
	public List<Provincia> findAll() throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Provincia> provincias  = null;
		try {
			conection.setAutoCommit(false);
			provincias  = provinciaDao.findAll(conection);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return provincias ;

	}

}
