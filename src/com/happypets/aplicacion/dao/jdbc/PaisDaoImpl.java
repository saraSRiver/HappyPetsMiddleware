package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.PaisDao;
import com.happypets.aplicacion.model.Pais;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class PaisDaoImpl implements PaisDao{
	private static Logger logger= 
			LogManager.getLogger(PaisDaoImpl.class);
	public PaisDaoImpl() {

	}
	@Override
	public Pais findById (Connection conection,Long idPais)
			throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Pais result=null;

		String sql = null;
		try {
			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT P.IDPAIS, P.NOMBRE ");
			stringBuilder.append(" FROM PAIS P ");
			stringBuilder.append(" WHERE IDPAIS= ? ");
			sql = stringBuilder.toString();
			logger.trace("findById:"+sql);

			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idPais);
			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			result = new Pais();

			if (rs.next()) {			
				result = loadNext(conection, rs); 
			}

		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar el país ")
			.append(idPais)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return result;
	}

	private Pais  loadNext(Connection conection,ResultSet resultset)
			throws SQLException {
		int i = 1;
		Pais pais= new Pais();
		pais.setIdPais(resultset.getLong(i++));
		pais.setNombre(resultset.getString(i++));
		return pais;

	}
}
