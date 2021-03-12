package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.PaisDao;
import com.happypets.aplicacion.dao.ProvinciaDAO;
import com.happypets.aplicacion.model.Provincia;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class ProvinciaDaoImpl implements ProvinciaDAO{
	private static Logger logger= 
			LogManager.getLogger(ProvinciaDaoImpl.class);
	PaisDao paisDao=null;

	public ProvinciaDaoImpl() {
		paisDao= new PaisDaoImpl();
	}

	@Override
	public Provincia findById(Connection conection,Long idProvincia)
			throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Provincia results=null;
		String sql = null;
		try {

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT PR.IDPROVINCIA, PR.NOMBRE, PR.IDPAIS ");
			stringBuilder.append(" WHERE PR.IDPROVINCIA= ? ");
			sql = stringBuilder.toString();
			logger.trace("findById:"+sql);

			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idProvincia);
			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new Provincia();

			if (rs.next()) {			
				results = loadNext( rs); 
			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append("la provincia ")
			.append(idProvincia)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}	


	@Override
	public List<Provincia> findAll(Connection conection)
			throws DataException {
		PreparedStatement preparedStatement = null;

		ResultSet rs=null;
		List<Provincia>results= null;

		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT PR.IDPROVINCIA, PR.NOMBRE, PR.IDPAIS ");
			stringBuilder.append(" FROM PROVINCIA PR ");
			sql = stringBuilder.toString();
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new ArrayList<Provincia>();
			Provincia prov= new Provincia();

			while (rs.next()) {	
				prov= loadNext( rs); 
				results.add(prov);
			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar las ")
			.append(" provincias ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	private Provincia  loadNext(ResultSet resultset) throws SQLException {
		int i = 1;
		Provincia prov=new Provincia();
		prov.setidProvincia(resultset.getLong(i++));
		prov.setNombre(resultset.getString(i++));
		prov.setIdPais(resultset.getLong(i++));
		return prov;
	}
}