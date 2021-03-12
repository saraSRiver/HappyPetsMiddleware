package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.TipoEspecieDao;
import com.happypets.aplicacion.model.TipoEspecie;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class TipoEspecieDaoImpl implements TipoEspecieDao{
	private static Logger logger= 
			LogManager.getLogger(TipoEspecieDaoImpl.class);
	public TipoEspecieDaoImpl() {

	}
	@Override
	public TipoEspecie findById(Connection conection,Long idTipoEspecie, String idioma) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		TipoEspecie results= null;
		String sql = null;
		try {
			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder()
			.append(" SELECT T.IDTIPO, IT.ESPECIE FROM TIPO T INNER JOIN ")
			.append(" IDIOMA_TIPO IT ON T.IDTIPO=IT.IDTIPO ")
			.append(" WHERE T.IDTIPO= ? AND IT.IDIDIOMA LIKE ?");
			sql = stringBuilder.toString();
					

			logger.trace("findById:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idTipoEspecie);
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results = new TipoEspecie();
			if (rs.next()) {			
				results =loadNext( rs); 
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return results;
	}


	@Override
	public List<TipoEspecie> findByIdCuidador(Connection conection,Long idCuidador, String idioma) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		ArrayList<TipoEspecie> results=null;
		String sql = null;
		try {
			StringBuilder stringBuilder = new StringBuilder()
			.append(" SELECT T.IDTIPO,IT.ESPECIE	FROM TIPO T  ")
			.append("INNER JOIN IDIOMA_TIPO IT  ON T.IDTIPO=IT.IDTIPO ")
			.append("iNNER JOIN CUIDADOR_ATIENDE_TIPO CT ON T.IDTIPO=CT.IDTIPO ")
			.append("WHERE IDCUIDADOR = ? AND IT.IDIDIOMA LIKE ? ");
			// Execute a query
			sql = stringBuilder.toString();
					
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++,idCuidador);
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();
			results = new ArrayList<TipoEspecie>();
			TipoEspecie especie= new TipoEspecie();

			// Extract data from result set
			while (rs.next()) {
				especie = loadNext( rs);				
				results.add(especie);
			}	
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}
	@Override
	public List<TipoEspecie> findAll(Connection conection, String idioma) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		ArrayList<TipoEspecie> results=null;
		String sql = null;
		try {
			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");
			StringBuilder stringBuilder = new StringBuilder()
			.append(" SELECT T.IDTIPO, IT.ESPECIE FROM TIPO T ")
			.append(" INNER JOIN IDIOMA_TIPO IT ")
			.append(" ON T.IDTIPO=IT.IDTIPO WHERE IT.IDIDIOMA LIKE ? ");
			// Execute a query
			sql = stringBuilder.toString();
				
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new ArrayList<TipoEspecie>();
			TipoEspecie especie= new TipoEspecie();

			// Extract data from result set
			while (rs.next()) {
				especie = loadNext( rs);				
				results.add(especie);
			}	
			// Clean-up environment
		}  catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return results;
	}
	private TipoEspecie loadNext(ResultSet resultset) throws SQLException {
		int i = 1;
		TipoEspecie especie=new TipoEspecie();
		especie.setIdTipoEspecie(resultset.getLong(i++));
		especie.setNombre(resultset.getString(i++));
		return especie;
	}


	@Override
	public boolean deleteByIdCuidador(Connection conection,
			Long idCuidador) throws DataException {

		PreparedStatement preparedStatement = null;

		try {  
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder()
			.append(" DELETE FROM CUIDADOR_ATIENDE_TIPO CA ")
			.append(" WHERE IDCUIDADOR = ? ");
			String queryString = stringBuilder.toString(); 

			preparedStatement = conection.prepareStatement(queryString);

			preparedStatement.setLong(1, idCuidador);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				StringBuilder stringBuilder2 = new StringBuilder()
				.append(" No ha sido posible eliminar estos ")
				.append(" elementos de 'especie' ");
				throw new SQLException(stringBuilder2.toString());
			} 
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;
	}
}
