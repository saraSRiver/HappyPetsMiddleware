package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ContratoDao;
import com.happypets.aplicacion.dao.PromocionDao;
import com.happypets.aplicacion.dao.ServicioOfrecidoDAO;
import com.happypets.aplicacion.dao.jdbc.ContratoDaoImpl;
import com.happypets.aplicacion.dao.jdbc.PromocionDaoImpl;
import com.happypets.aplicacion.dao.jdbc.ServicioOfrecidoDaoImpl;
import com.happypets.aplicacion.model.Promocion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.PromocionService;
import com.happypets.aplicacion.util.DBUtils;

public class PromocionServiceImpl implements PromocionService{
	private static Logger logger= 
			LogManager.getLogger(PromocionServiceImpl.class);
	PromocionDao promocionDao=null;
	ContratoDao contratoDao=null;
	ServicioOfrecidoDAO servicioOfrecidoDao=null;

	public PromocionServiceImpl() {
		promocionDao= new PromocionDaoImpl();
		contratoDao= new ContratoDaoImpl();
		servicioOfrecidoDao= new ServicioOfrecidoDaoImpl();
	}
	
	@Override
	public Promocion findById(Integer idPromocion) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Promocion promocion = null;
		try {
			conection.setAutoCommit(false);
			promocion =promocionDao.findById(conection, idPromocion);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return promocion;
	}
	@Override
	public Promocion findByIdCliente(Long idCliente) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Promocion promocion = null;
		try {
			conection.setAutoCommit(false);
			promocion=promocionDao.findByIdCliente(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return promocion;
	}

	/*
	@Override
	public Promocion recibirPromocion(Long idCliente, 
			Long idPromocion) throws DataException {
		Promocion promocion=promocionDao.findByIdCliente(idCliente);
		Integer nContratos = 0;
		List<Contrato> contratos=contratoDao.findByHistorialCliente(idCliente);
		Integer numerador=14;
		for (int i=0; i==numerador;i++) {
			i=nContratos;
			if(nContratos==numerador) {
				
			}
		}
		return promocion;
	}

	 */

}
