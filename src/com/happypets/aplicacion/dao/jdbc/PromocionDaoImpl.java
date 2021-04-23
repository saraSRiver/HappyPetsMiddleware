package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.MascotaDao;
import com.happypets.aplicacion.dao.PromocionDao;
import com.happypets.aplicacion.model.Promocion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class PromocionDaoImpl implements PromocionDao{
	private static Logger logger= 
			LogManager.getLogger(PromocionDaoImpl.class);
	MascotaDao mascotaDao=null;

	public PromocionDaoImpl() {

	}

	@Override
	public Promocion findById(Connection conection,Integer idPromocion) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Promocion results= null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT PRO.IDPROMOCION, PRO.PORCENTAJE_DESCUENTO, ");
			stringBuilder.append(" PRO.NUMERADOR, PRO.DATA_OBTENCION, PRO.TIPO_PROMOCION ");
			stringBuilder.append(" FROM PROMOCION PRO ");
			stringBuilder.append(" WHERE PRO.IDPROMOCION= ? ");
			sql = stringBuilder.toString();
			logger.trace("findById:"+sql);

			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setInt(i++,idPromocion);
			rs = preparedStatement.executeQuery();	
			// Extract data from result set

			if (rs.next()) {			
				results = loadNext( rs);
	
			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar la promoción ")
			.append(idPromocion)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}
	@Override
	public Promocion findByIdCliente(Connection conection,Long idCliente) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Promocion results= null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT PRO.IDPROMOCION, PRO.PORCENTAJE_DESCUENTO, PRO.NUMERADOR, ");
			stringBuilder.append(" PRO.DATA_OBTENCION, PRO.TIPO_PROMOCION ");
			stringBuilder.append(" FROM PROMOCION PRO INNER JOIN CLIENTE CL ");
			stringBuilder.append(" ON PRO.IDPROMOCION=CL.IDPROMOCION WHERE CL.IDCLIENTE= ? ");
			// Execute a query
			sql = stringBuilder.toString();

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCliente);
			rs = preparedStatement.executeQuery();
			results = new Promocion();

			if (rs.next()) {			
				results = loadNext( rs); 

			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar la promoción ")
			.append(" del cliente ")
			.append(idCliente)
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	private Promocion loadNext(ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		Promocion promocion = new Promocion();
		promocion.setIdpromocion(resultSet.getInt(i++));
		promocion.setPorcentajeDescuento(resultSet.getInt(i++));
		promocion.setNumerador(resultSet.getInt(i++));
		promocion.setDataObtencion(resultSet.getDate(i++));
		promocion.setTipoPromocion(resultSet.getString(i++));
		return promocion;
	}

	@Override
	public List<Promocion> findAll(Connection conection) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		String sql = null;
		List<Promocion> promociones;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" SELECT PRO.IDPROMOCION, PRO.PORCENTAJE_DESCUENTO, ");
			stringBuilder.append(" PRO.NUMERADOR, PRO.DATA_OBTENCION, PRO.TIPO_PROMOCION ");
			stringBuilder.append(" FROM PROMOCION PRO ");
			sql = stringBuilder.toString();
			logger.trace("findById:"+sql);

			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			rs = preparedStatement.executeQuery();	
			// Extract data from result set
			promociones = new ArrayList<Promocion>();
			Promocion promocion;
			while (rs.next()) {			
				 promocion = loadNext( rs);
				promociones.add(promocion);
			}
		}  catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar las promociónes ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return promociones;
	}


}
