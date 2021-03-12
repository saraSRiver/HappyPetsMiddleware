package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.DireccionDTODAO;
import com.happypets.aplicacion.dao.jdbc.DireccionDTODAOImpl;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.DireccionDTOService;
import com.happypets.aplicacion.util.DBUtils;


public class DireccionDTOServiceImpl implements DireccionDTOService{
	private static Logger logger= 
			LogManager.getLogger(DireccionDTOServiceImpl.class);
	private DireccionDTODAO direccionDTODAO=null;

	public DireccionDTOServiceImpl() {
		direccionDTODAO= new DireccionDTODAOImpl();
	}
	public DireccionDTO findByIdCliente (Long idCliente)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		DireccionDTO direccionDTO  = null;
		try {
			conection.setAutoCommit(false);
			direccionDTO= direccionDTODAO.findByIdCliente
					(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return direccionDTO;

	}
	public DireccionDTO findByIdCuidador (Long idCuidador)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		DireccionDTO direccionDTO  = null;
		try {
			conection.setAutoCommit(false);
			direccionDTO= direccionDTODAO.findByIdCuidador
					(conection, idCuidador);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return direccionDTO;

	}
}
