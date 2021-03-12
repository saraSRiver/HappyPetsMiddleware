package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ExperienciaDao;
import com.happypets.aplicacion.model.Experiencia;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;


public class ExperienciaDaoImpl implements ExperienciaDao{
	private static Logger logger= 
			LogManager.getLogger(ExperienciaDaoImpl.class);
	public ExperienciaDaoImpl() {

	}

	@Override
	public Experiencia findById(Connection conection,Integer idExperiencia, String idioma)throws DataException {
		Experiencia results= null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT E.IDEXPERIENCIA, IE.VALOR FROM EXPERIENCIA E ");
			stringBuilder.append(" INNER JOIN IDIOMA_EXPERIENCIA IE ON ");
			stringBuilder.append(" E.IDEXPERIENCIA=IE.IDEXPERIENCIA ");
			stringBuilder.append(" WHERE E.IDEXPERIENCIA= ? AND IE.IDIDIOMA LIKE ? ");
			sql = stringBuilder.toString();
					

			StringBuilder stringBuilder2 = new StringBuilder();
			stringBuilder2.append("findById:");
			stringBuilder2.append(sql);
			logger.trace(stringBuilder2.toString());

			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++, idExperiencia);
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new Experiencia();

			if (rs.next()) {			
				results = loadNext( rs); 

			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar la ")
			.append("experiencia ")
			.append(idExperiencia)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return results;
	}


	@Override
	public List<Experiencia> findAll(Connection conection, String idioma) throws DataException {
		List<Experiencia> results= null;

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT E.IDEXPERIENCIA, IE.VALOR FROM EXPERIENCIA E ");
			stringBuilder.append(" INNER JOIN IDIOMA_EXPERIENCIA IE ON ");
			stringBuilder.append(" E.IDEXPERIENCIA=IE.IDEXPERIENCIA ");
			stringBuilder.append(" WHERE IE.IDIDIOMA LIKE ? ");
			sql = stringBuilder.toString();

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new ArrayList<Experiencia>();
			Experiencia exp= new Experiencia();

			while (rs.next()) {			
				exp = loadNext( rs); 
				results.add(exp);
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append("la lista ")
			.append("de experiencias")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	private Experiencia loadNext(ResultSet resultSet) throws DataException,
	SQLException {
		int i = 1;
		Experiencia experiencia= new Experiencia();
		experiencia.setIdExperiencia(resultSet.getInt(i++));
		experiencia.setValor(resultSet.getString(i++));
		return experiencia;
	}

}
