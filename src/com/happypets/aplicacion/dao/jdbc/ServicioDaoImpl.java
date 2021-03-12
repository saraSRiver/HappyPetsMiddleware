package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ServicioDao;
import com.happypets.aplicacion.model.Servicio;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;



public class ServicioDaoImpl implements ServicioDao{
	private static Logger logger= 
			LogManager.getLogger(ServicioDaoImpl.class);
	public ServicioDaoImpl() {

	}

	@Override
	public Servicio findById(Connection conection,Long idServicio, String idioma)
			throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Servicio results= null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT  S.IDSERVICIO, SI.NOMBRE FROM SERVICIO S ");
			stringBuilder.append("INNER JOIN IDIOMA_SERVICIO SI ON S.IDSERVICIO=SI.IDSERVICIO ");
			stringBuilder.append("WHERE SI.IDSERVICIO= ? AND SI.IDIDIOMA LIKE ? ");
			sql = stringBuilder.toString();
					

			logger.trace("findById:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idServicio);
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results = new Servicio();
			if (rs.next()) {			
				results = loadNext( rs); 
			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No ha sido posible encontrar")
			.append(" el servicio ")
			.append(idServicio)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;	
	}

	@Override
	public List<Servicio> findAll(Connection conection, String idioma) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		ArrayList<Servicio> results=null;

		String sql = null;
		try {
			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT S.IDSERVICIO, SI.NOMBRE FROM SERVICIO S ");
			stringBuilder.append("INNER JOIN IDIOMA_SERVICIO SI ON S.IDSERVICIO=SI.IDSERVICIO ");
			stringBuilder.append("WHERE SI.IDIDIOMA LIKE ? ");
			sql = stringBuilder.toString();
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			results = new ArrayList<Servicio>();
			Servicio serv= new Servicio();
			while (rs.next()) {	
				serv=loadNext( rs); 
				results.add(serv);
			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No ha sido posible mostrar ")
			.append(" los servicios")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return results;
	}

	@Override
	public List<Servicio> findByIdCuidador (Connection conection,Long idCuidador, String idioma) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Servicio>results = null;

		String sql = null;
		try {

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT  S.IDSERVICIO, SI.NOMBRE FROM SERVICIO S ");
			stringBuilder.append("INNER JOIN IDIOMA_SERVICIO SI ON S.IDSERVICIO=SI.IDSERVICIO ");
			stringBuilder.append("	INNER JOIN SERVICIOOFRECIDO SO  ");
			stringBuilder.append(" ON S.IDSERVICIO=SO.IDSERVICIO INNER JOIN CUIDADOR C ");
			stringBuilder.append(" ON SO.IDCUIDADOR=C.IDCUIDADOR ");
			stringBuilder.append(" WHERE SO.IDCUIDADOR= ? AND SI.IDIDIOMA LIKE ? ");
			sql = stringBuilder.toString();


			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			preparedStatement.setLong(i++,idCuidador);
			preparedStatement.setString(i++, idioma);
			rs = preparedStatement.executeQuery();
			results = new ArrayList<Servicio>();
			Servicio servicio= new Servicio();
			// Extract data from result set

			while (rs.next()) {
				servicio = loadNext( rs);				
				results.add(servicio);
			}
			// Clean-up environment
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No ha posible encontrar el servicio ")
			.append("que ofrece el cuidador ")
			.append(idCuidador)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}
	private Servicio loadNext(ResultSet resultset) throws SQLException {
		int i = 1;
		Servicio servicio= new Servicio();
		servicio.setIdServicio(resultset.getLong(i++));
		servicio.setNombreServicio(resultset.getString(i++));
		return servicio;
	}
}