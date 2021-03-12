package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ExperienciaDao;
import com.happypets.aplicacion.dao.jdbc.ExperienciaDaoImpl;
import com.happypets.aplicacion.model.Experiencia;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ExperienciaService;
import com.happypets.aplicacion.util.DBUtils;

public class ExperienciaServiceImpl implements ExperienciaService {
	private static Logger logger= 
			LogManager.getLogger(ExperienciaServiceImpl.class);
	private ExperienciaDao experienciaDao=null;

	public ExperienciaServiceImpl() {
		experienciaDao= new ExperienciaDaoImpl();
	}

	public Experiencia findById(Integer idExperiencia, String idioma)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Experiencia experiencia = null;
		try {
			conection.setAutoCommit(false);
			experiencia =experienciaDao.findById(conection, idExperiencia, idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return experiencia;

	}
	public List<Experiencia> findAll(String idioma)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Experiencia> experiencias = null;
		try {
			conection.setAutoCommit(false);
			experiencias =experienciaDao.findAll(conection, idioma);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return experiencias;

	}
}
