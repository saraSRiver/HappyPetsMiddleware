package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.IdiomaDao;
import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class IdiomaDaoImpl implements IdiomaDao {
	private static Logger logger= 
			LogManager.getLogger(IdiomaDaoImpl.class);
	public IdiomaDaoImpl() {

	}

	@Override
	public Idioma findByid(Connection conection,String idIdioma)
			throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Idioma results=null;
		String sql = null;
		
		try {
			// Ejecuta la query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT I.IDIDIOMA, I.IDIOMA ");
			stringBuilder.append(" FROM IDIOMA I ");
			stringBuilder.append(" WHERE I.IDIDIOMA= ? ");
			sql = stringBuilder.toString();

			logger.trace("findById:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setString(i++, idIdioma);
			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results = new Idioma();
			while (rs.next()) {			
				results = loadNext( rs); 
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el idioma ")
			.append(idIdioma)
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
	public List<Idioma> findByIdiomasCuidador(Connection conection,Long idCuidador) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Idioma>results= null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT I.IDIDIOMA, I.IDIOMA ");
			stringBuilder.append(" FROM IDIOMA I INNER JOIN IDIOMACUIDADOR CI ");
			stringBuilder.append(" ON I.IDIDIOMA= CI.IDIDIOMA ");
			stringBuilder.append(" WHERE CI.IDCUIDADOR= ? ");
			// Execute a query
			sql = stringBuilder.toString();

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCuidador);
			rs = preparedStatement.executeQuery();
			results = new ArrayList<Idioma>();
			Idioma idioma= new Idioma();

			// Extract data from result set
			while (rs.next()) {
				idioma=loadNext( rs);	
				results.add(idioma);
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar los idiomas ")
			.append("del cuidador ")
			.append(idCuidador)
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
	public List<Idioma> findByIdiomasCliente(Connection conection,Long idCliente) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Idioma>results= null;
		String sql = null;
		try {
			// Open a connection
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT I.IDIDIOMA, I.IDIOMA FROM IDIOMA I ");
			stringBuilder.append(" INNER JOIN IDIOMACLIENTE CLI ");
			stringBuilder.append(" ON I.IDIDIOMA=CLI.IDIDIOMA ");
			stringBuilder.append(" WHERE CLI.IDCLIENTE=? ");
			// Execute a query
			sql = stringBuilder.toString();

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCliente);
			
			rs = preparedStatement.executeQuery();
			results = new ArrayList<Idioma>();
			Idioma idioma= new Idioma();

			// Extract data from result set
			while (rs.next()) {
				idioma=loadNext( rs);	
				results.add(idioma);
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append(" los idiomas del cliente ")
			.append(idCliente)
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
	public List<Idioma> findAll(Connection conection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Idioma> results= null;

		String sql = null;
		try {
			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT I.IDIDIOMA, I.IDIOMA ");
			stringBuilder.append(" FROM IDIOMA I ");
			sql = stringBuilder.toString(); 
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new ArrayList<Idioma>();
			Idioma idioma= new Idioma();
			while (rs.next()) {			
				idioma= loadNext( rs);
				results.add(idioma);
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar los idiomas ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	private Idioma loadNext(ResultSet resultset) throws SQLException {
		int i = 1;
		Idioma idioma= new Idioma();
		idioma.setIdIdioma(resultset.getString(i++));
		idioma.setIdioma(resultset.getString(i++));
		return idioma;
	}

	@Override
	public boolean deleteByCliente(Connection conection,Long idCliente) throws DataException {
		PreparedStatement preparedStatement = null;

		Boolean delete = false;
		try {  

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" DELETE FROM IDIOMACLIENTE ");
			stringBuilder.append(" WHERE IDCLIENTE= ? ");
			String queryString = stringBuilder.toString(); 

			preparedStatement = conection.prepareStatement(queryString);
			preparedStatement.setLong(1, idCliente);

			int deleteRows = preparedStatement.executeUpdate();
			if (deleteRows == 0) {
				throw new SQLException(" No ha sido posible eliminar estos elementos de 'idiomaCliente' ");
			} 

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append(" los idiomas del cliente ")
			.append(idCliente)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return delete;
	}

	@Override
	public boolean deleteByCuidador(Connection conection,Long idCuidador) throws DataException {
		PreparedStatement preparedStatement = null;

		Boolean delete = false;
		try {  

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" DELETE  FROM IDIOMACUIDADOR ");
			stringBuilder.append(" WHERE  IDCUIDADOR= ? ");
			String queryString = stringBuilder.toString(); 

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, idCuidador);

			int deleteRows = preparedStatement.executeUpdate();
			if (deleteRows == 0) {
				throw new SQLException(" No ha sido posible eliminar estos elementos de 'idiomaCuidador' ");
			} 
			else {
				delete = true;
			}
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar los idiomas ")
			.append("del cuidador ")
			.append(idCuidador)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return delete;
	}



}