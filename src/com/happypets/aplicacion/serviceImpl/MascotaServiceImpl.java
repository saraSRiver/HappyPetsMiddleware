package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.MascotaDao;
import com.happypets.aplicacion.dao.jdbc.MascotaDaoImpl;
import com.happypets.aplicacion.model.Mascota;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.MascotaService;
import com.happypets.aplicacion.util.DBUtils;

public class MascotaServiceImpl implements MascotaService{
	private static Logger logger= 
			LogManager.getLogger(MascotaServiceImpl.class);
	private MascotaDao mascotaDao;

	public MascotaServiceImpl() {
		mascotaDao= new MascotaDaoImpl();
	}
	public Mascota findById (Long idMascota)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Mascota  mascota;
		try {
			conection.setAutoCommit(false);
			mascota=mascotaDao.findById(conection, idMascota);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return mascota;

	}
	public List<Mascota> findByIdCliente(Long idCliente)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Mascota> mascotas;
		try {
			conection.setAutoCommit(false);
			mascotas=mascotaDao.findByIdCliente(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return  mascotas;
	}
	public Mascota create (Mascota mascota)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			mascotaDao.create(conection, mascota);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return mascota;

	}
	public Mascota update (Mascota mascota)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			mascotaDao.update(conection, mascota);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return mascota;

	}
	public boolean deleteMascota(Long id) throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			mascotaDao.deleteMascota(conection, id);
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
	public boolean deleteMascotaByCliente(Long idCliente) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			mascotaDao.deleteMascotaByCliente(conection, idCliente);
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
