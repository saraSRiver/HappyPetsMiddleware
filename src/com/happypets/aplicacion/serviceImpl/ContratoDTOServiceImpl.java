package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ContratoDTODAO;
import com.happypets.aplicacion.dao.jdbc.ContratoDTODAOImpl;
import com.happypets.aplicacion.model.ContratoDTO;
import com.happypets.aplicacion.service.ContratoDTOService;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class ContratoDTOServiceImpl implements ContratoDTOService{
	private static Logger logger= 
			LogManager.getLogger(ContratoDTOServiceImpl.class);
	private ContratoDTODAO contratoDTODAO=null;
	
	public ContratoDTOServiceImpl() {
		contratoDTODAO= new ContratoDTODAOImpl();
	}
	
	@Override
	public List<ContratoDTO> findByIdCliente(Long idCliente) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<ContratoDTO> contratosDto;
		try {
			conection.setAutoCommit(false);
			contratosDto=contratoDTODAO.findByIdCliente(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return contratosDto;

	}

	@Override
	public List<ContratoDTO> findByIdCuidador(Long idCuidador) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<ContratoDTO> contratosDto;
		try {
			conection.setAutoCommit(false);
			contratosDto=contratoDTODAO.findByIdCuidador(conection, idCuidador);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return contratosDto;
	}

}
