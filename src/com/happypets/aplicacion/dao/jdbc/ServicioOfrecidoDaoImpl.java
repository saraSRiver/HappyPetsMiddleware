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

import com.happypets.aplicacion.dao.ServicioDao;
import com.happypets.aplicacion.dao.ServicioOfrecidoDAO;
import com.happypets.aplicacion.model.ServicioOfrecido;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class ServicioOfrecidoDaoImpl implements ServicioOfrecidoDAO{

	private ServicioDao servicioDao= null;
	private static Logger logger= 
			LogManager.getLogger(ServicioOfrecidoDaoImpl.class);
	public ServicioOfrecidoDaoImpl() {
		servicioDao= new ServicioDaoImpl();
	}

	@Override
	public List<ServicioOfrecido> findByCuidador(Connection conection,Long idCuidador) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<ServicioOfrecido> results=null;

		String sql = null;
		try {


			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder()
			.append("SELECT SO.IDCUIDADOR, SO.IDSERVICIO, ")
			.append(" S.NOMBRE_SERVICIO, SO.PRECIO ")
			.append(" FROM SERVICIOOFRECIDO SO ")
			.append(" INNER JOIN SERVICIO S ON SO.IDSERVICIO=S.IDSERVICIO ")
			.append(" WHERE SO.IDCUIDADOR = ? ");
			sql = stringBuilder.toString();

			logger.trace("findById:"+idCuidador);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCuidador);

			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results =new ArrayList<ServicioOfrecido>();
			while (rs.next()) {			
				results.add(loadNext(conection, rs)); 

			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No es posible encontrar al cuidador ")
			.append(idCuidador)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} 	
		finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;				
	}
	public ServicioOfrecido findByCuidadorServicio(Connection conection,
			Long idCuidador, Long idServicio) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		ServicioOfrecido results=null;
		String sql = null;
		try {
			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder()
			.append("SELECT SO.IDCUIDADOR, SO.IDSERVICIO, ")
			.append(" S.NOMBRE_SERVICIO, SO.PRECIO ")
			.append(" FROM SERVICIOOFRECIDO SO ")
			.append(" INNER JOIN SERVICIO S ON SO.IDSERVICIO=S.IDSERVICIO ")
			.append(" WHERE SO.IDCUIDADOR = ? AND SO.IDSERVICIO= ? ");
			sql = stringBuilder.toString();

			logger.trace("findById:"+idCuidador+ idServicio);
			preparedStatement = conection.prepareStatement(sql,
			ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCuidador);
			preparedStatement.setLong(i++,idServicio);

			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results =new ServicioOfrecido();
			if (rs.next()) {
				results= loadNext(conection, rs);
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No es posible encontrar al cuidador ")
			.append(idCuidador)
			.append(" o al servicio ")
			.append(idServicio)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} 	
		finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return results;				
	}

	private ServicioOfrecido loadNext(Connection conection,ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		ServicioOfrecido servOfre = new ServicioOfrecido();
		servOfre.setIdCuidador(resultSet.getLong(i++));
		servOfre.setIdServicio(resultSet.getLong(i++));
		servOfre.setNombreServicio(resultSet.getString(i++));
		servOfre.setPrecio(resultSet.getDouble(i++));
		return servOfre;

	}
	@Override
	public void create(Connection conection,ServicioOfrecido so) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;

		try {          
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder()
			.append(" INSERT INTO SERVICIOOFRECIDO(IDCUIDADOR,")
			.append(" IDSERVICIO, PRECIO) ")
			.append("  VALUES (?, ?, ?) ");
			String sql = stringBuilder.toString(); 
			logger.trace("sql = "+sql);
			preparedStatement = conection.prepareStatement(sql);

			int i = 1;
			preparedStatement.setLong(i++, so.getIdCuidador());
			preparedStatement.setLong(i++, so.getIdServicio());
			preparedStatement.setDouble(i++, so.getPrecio());

			preparedStatement.executeUpdate();


			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No es posible crear los servicios")
			.append(" del cuidador ")
			.append(so.getIdCuidador())
			.append(se);
			throw new DataException(stringBuilder.toString());
		} 
		finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public boolean update(Connection conection,ServicioOfrecido so) 
			throws DataException{

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;

		try {      
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder()
			.append(" UPDATE SERVICIOOFRECIDO ")
			.append(" SET PRECIO = ? ")
			.append(" WHERE IDCUIDADOR = ? AND IDSERVICIO = ? ");
			String sql = stringBuilder.toString(); 
			logger.trace("sql = "+sql);
			preparedStatement = conection.prepareStatement(sql);

			int i = 1;
			preparedStatement.setDouble(i++, so.getPrecio());
			preparedStatement.setLong(i++, so.getIdCuidador());
			preparedStatement.setLong(i++, so.getIdServicio());

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {

				StringBuilder stringBuilder2 = new StringBuilder()
				.append("Servicio ")
				.append(so.getIdServicio())
				.append(" o Cuidador ")
				.append(so.getIdCuidador())
				.append(" no encontrado");
				throw new DataException(stringBuilder2.toString());
			} 

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No es posible actualizar los servicios del cuidador ")
			.append(so.getIdCuidador())
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;
	}

	@Override
	public boolean deleteByIdCuidador(Connection conection,Long idCuidador) throws DataException {

		PreparedStatement preparedStatement = null;

		Boolean delete = false;
		try {  

			logger.trace("Creating Statement...");
			String queryString = " DELETE FROM SERVICIOOFRECIDO WHERE IDCUIDADOR= ? "; 

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, idCuidador);

			int deleteRows = preparedStatement.executeUpdate();
			if (deleteRows == 0) {
				StringBuilder stringBuilder = new StringBuilder()
				.append(" No ha sido posible eliminar estos ")
				.append(" elementos de 'ServiciosOfrecidos' ");
				throw new SQLException(stringBuilder.toString());
			} 
			else {
				delete = true;
			}
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No es posible eliminar ")
			.append("los servicios del cuidador")
			.append(idCuidador)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {

			DBUtils.closePreparedStatement(preparedStatement);

		}
		return delete;
	}

}
