package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.PoblacionDao;
import com.happypets.aplicacion.model.Poblacion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class PoblacionDaoImpl implements PoblacionDao{
	private static Logger logger= 
			LogManager.getLogger(PoblacionDaoImpl.class);
	public PoblacionDaoImpl() {

	}
	@Override
	public Poblacion findById(Connection conection,Long idPoblacion)
			throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Poblacion results=null;
		String sql = null;
		try {

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT POB.IDPOBLACION, POB.NOMBRE, POB.IDPROVINCIA ");
			stringBuilder.append(" FROM POBLACION POB ");
			stringBuilder.append(" WHERE POB.IDPOBLACION= ? ");
			sql = stringBuilder.toString();

			logger.trace("findById:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idPoblacion);
			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new Poblacion();

			if (rs.next()) {			
				results = loadNext(conection, rs); 
			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha encontrado la poblacion ")
			.append(idPoblacion)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	@Override
	public List<Poblacion> findAll(Connection conection) 
			throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Poblacion> results=null;

		String sql = null;
		try {

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT POB.IDPOBLACION, POB.NOMBRE, POB.IDPROVINCIA ");
			stringBuilder.append(" FROM POBLACION POB ");
			sql = stringBuilder.toString();
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);

			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new ArrayList<Poblacion>();
			Poblacion pob= new Poblacion();
			while (rs.next()) {	
				pob=loadNext(conection, rs); 
				results.add(pob);
			}

		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append("las poblaciones ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	@Override
	public List<Poblacion> findByProvincia(Connection conection,
			Long idProvincia) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Poblacion> results=null;

		String sql = null;
		try {
			// Open a connection
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT POB.IDPOBLACION, POB.NOMBRE, POB.IDPROVINCIA ");
			stringBuilder.append(" FROM POBLACION POB INNER JOIN PROVINCIA PR ");
			stringBuilder.append(" ON POB.IDPROVINCIA=PR.IDPROVINCIA ");
			stringBuilder.append(" WHERE PR.IDPROVINCIA= ? ");
			// Execute a query
			sql = stringBuilder.toString();
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idProvincia);
			rs = preparedStatement.executeQuery();
			results = new ArrayList<Poblacion>();
			Poblacion pob= new Poblacion();
			// Extract data from result set
			while (rs.next()) {
				pob=loadNext(conection, rs);	
				results.add(pob);
			}
			// Clean-up environment
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar la poblacion ")
			.append("en la provincia ")
			.append(idProvincia)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}
	private Poblacion loadNext(Connection conection,ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		Poblacion poblacion= new Poblacion ();
		poblacion.setIdPoblacion(resultSet.getLong(i++));
		poblacion.setNombre(resultSet.getString(i++));
		poblacion.setIdProvincia(resultSet.getLong(i++));
		return poblacion;
	}

}
