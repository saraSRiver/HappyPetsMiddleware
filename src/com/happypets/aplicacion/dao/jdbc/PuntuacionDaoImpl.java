package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.PuntuacionDao;
import com.happypets.aplicacion.model.Puntuacion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.BDNullUtils;
import com.happypets.aplicacion.util.DBUtils;

public class PuntuacionDaoImpl implements PuntuacionDao{
	private static Logger logger= 
			LogManager.getLogger(PuntuacionDaoImpl.class);
	public PuntuacionDaoImpl() {


	}
	@Override
	public List<Puntuacion> findByidCliente
	(Connection conection,Long idCliente) throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Puntuacion> results= null;
		String sql = null;
		try {
			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT PU.PUNTUACION, PU.COMENTARIO, ");
			stringBuilder.append("PU.IDCUIDADOR, PU.IDCLIENTE ");
			stringBuilder.append(" FROM PUNTUACION PU ");
			stringBuilder.append(" WHERE PU.IDCLIENTE = ? ");
			sql = stringBuilder.toString();

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCliente);

			rs = preparedStatement.executeQuery();

			// Extract data from result set
			results = new ArrayList<Puntuacion>();
			Puntuacion pt= new Puntuacion();
			while (rs.next()) {
				pt = loadNext( rs);				
				results.add(pt);
			}

		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append("las puntuaciones del cliente ")
			.append(idCliente)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	@Override
	public Double findByMedia(Connection conection,Long idCuidador) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Double result = null;
		try {
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT AVG(PU.PUNTUACION)");
			stringBuilder.append(" FROM PUNTUACION PU ");
			stringBuilder.append(" WHERE PU.IDCUIDADOR = ? ");
			String sql = stringBuilder.toString();

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;			
			preparedStatement.setLong(i++,idCuidador);

			// Ejecutamos la query
			rs = preparedStatement.executeQuery();

			// Extract data from result set
			i = 1;
			if (rs.next()) {
				result = rs.getDouble(i++); 
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append("la media del cuidador ")
			.append(idCuidador)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return result;
	}
	@Override
	public Puntuacion findPuntuacion(Connection conection, Long idCliente, Long idCuidador) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Puntuacion pt = null;
		String sql = null;
		try {
			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT PU.PUNTUACION, PU.COMENTARIO, ");
			stringBuilder.append("PU.IDCUIDADOR, PU.IDCLIENTE ");
			stringBuilder.append(" FROM PUNTUACION PU ");
			stringBuilder.append(" WHERE PU.IDCLIENTE = ? AND PU.IDCUIDADOR = ?  ");
			sql = stringBuilder.toString();

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCliente);
			preparedStatement.setLong(i++,idCuidador);
			rs = preparedStatement.executeQuery();

			// Extract data from result set
		
			
			if (rs.next()) {
				pt = loadNext(rs);
			}

		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append("las puntuaciones del cliente ")
			.append(idCliente)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return pt;
	}
	private Puntuacion  loadNext(ResultSet resultset) throws SQLException {
		int i = 1;
		Puntuacion puntuacion= new Puntuacion();
		puntuacion.setPuntuacion(resultset.getDouble(i++));
		puntuacion.setComentario(resultset.getString(i++));
		puntuacion.setIdCuidador(resultset.getLong(i++));
		puntuacion.setIdCliente(resultset.getLong(i++));

		return puntuacion;
	}
	@Override
	public void create(Connection conection,Puntuacion pt) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;

		try {
			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO PUNTUACION (PUNTUACION, ");
			stringBuilder.append("COMENTARIO, IDCUIDADOR, IDCLIENTE) ");
			stringBuilder.append(" VALUES (?, ?, ?, ? )");
			String queryString = stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setDouble(i++, pt.getPuntuacion());
			BDNullUtils.setNull(preparedStatement, i++, pt.getComentario());
			preparedStatement.setLong(i++, pt.getIdCuidador());
			preparedStatement.setLong(i++, pt.getIdCliente());
			preparedStatement.executeUpdate();

		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido crear la ")
			.append(pt.getPuntuacion())
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
	}
	@Override
	public boolean update(Connection conection,Puntuacion pt) throws DataException {
		PreparedStatement preparedStatement = null;
		try { 

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" UPDATE PUNTUACION SET PUNTUACION = ?, ");
			stringBuilder.append("COMENTARIO= ? WHERE IDCLIENTE= ? ");
			stringBuilder.append(" AND IDCUIDADOR= ? ");
			String queryString = stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString);
			int i = 1;
			preparedStatement.setDouble(i++, pt.getPuntuacion());
			preparedStatement.setString(i++, pt.getComentario());
			preparedStatement.setLong(i++, pt.getIdCliente());
			preparedStatement.setLong(i++, pt.getIdCuidador());

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				throw new SQLException(" No ha sido posible modificar 'puntuacion' ");

			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar la ")
			.append(pt.getPuntuacion())
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;
	}

}
