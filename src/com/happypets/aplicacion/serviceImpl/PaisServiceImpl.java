package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.PaisDao;
import com.happypets.aplicacion.dao.jdbc.PaisDaoImpl;
import com.happypets.aplicacion.model.Pais;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.PaisService;
import com.happypets.aplicacion.util.DBUtils;

public class PaisServiceImpl implements PaisService{
	private static Logger logger= 
			LogManager.getLogger(PaisServiceImpl.class);
	private PaisDao paisDao= null;

	public PaisServiceImpl() {
		paisDao= new PaisDaoImpl();
	}

	public Pais findById (Long idPais)throws DataException, SQLException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Pais pais  = null;
		try {
			conection.setAutoCommit(false);
			pais = paisDao.findById(conection, idPais);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return pais;

	}

}
