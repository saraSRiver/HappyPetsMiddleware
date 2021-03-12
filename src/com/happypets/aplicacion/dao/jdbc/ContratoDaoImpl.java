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

import com.happypets.aplicacion.dao.ContratoDao;
import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class ContratoDaoImpl implements ContratoDao{
	private static Logger logger= 
			LogManager.getLogger(ContratoDaoImpl.class);

	public ContratoDaoImpl() {

	}

	@Override
	public Contrato findByid(Connection conection,Long idContrato) throws DataException {
		Contrato results=null;

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		String sql = null;
		try {
			// Ejecuta la query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT CO.IDCONTRATO, CO.FECHA_CONTRATO, CO.FECHA_INICIO, CO.FECHA_FIN, ");
			stringBuilder.append("CO.PRECIO_FINAL, CO.IDMASCOTA, CO.IDSERVICIO, ");
			stringBuilder.append("CO.IDCUIDADOR, CO.IDESTADO, CO.IDCLIENTE ");
			stringBuilder.append(" FROM CONTRATO CO ");
			stringBuilder.append("WHERE CO.IDCONTRATO= ? ");
			sql = stringBuilder.toString();

			logger.trace("findById:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idContrato);
			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results = new Contrato();
			if (rs.next()) {			
				results = loadNext( rs); 
			}

		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar")
			.append(" el contrato ")
			.append(idContrato)
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
	public List<Contrato> findByHistorialCuidador(Connection conection,Long idCuidador) throws DataException {
		List<Contrato> results=null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		String sql = null;
		try {
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT CO.IDCONTRATO, CO.FECHA_CONTRATO, CO.FECHA_INICIO, CO.FECHA_FIN, ");
			stringBuilder.append("	 CO.PRECIO_FINAL, CO.IDMASCOTA, CO.IDSERVICIO,  ");
			stringBuilder.append("	 CO.IDCUIDADOR, CO.IDESTADO, CO.IDCLIENTE ");
			stringBuilder.append(" FROM CONTRATO CO ");
			stringBuilder.append("	 WHERE CO.IDCUIDADOR= ? ");
			// Execute a query
			sql = stringBuilder.toString();

			logger.trace("findById:"+idCuidador);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCuidador);

			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results =new ArrayList<Contrato>();
			Contrato contrato= new Contrato();
			while (rs.next()) {			
				contrato = loadNext( rs); 
				results.add(contrato);
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encotnrar ")
			.append(" los contratos del cuidador ")
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
	public List<Contrato> findByHistorialCliente(Connection conection,Long idCliente) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Contrato> results=null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT CO.IDCONTRATO, CO.FECHA_CONTRATO, CO.FECHA_INICIO, CO.FECHA_FIN, ");
			stringBuilder.append("	 CO.PRECIO_FINAL, CO.IDMASCOTA, CO.IDSERVICIO,  ");
			stringBuilder.append("	 CO.IDCUIDADOR, CO.IDESTADO, CO.IDCLIENTE ");
			stringBuilder.append(" FROM CONTRATO CO ");
			stringBuilder.append("	 WHERE CO.IDCLIENTE= ? ");
			// Execute a query
			sql = stringBuilder.toString(); 

			logger.trace("findById:"+idCliente);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCliente);

			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results =new ArrayList<Contrato>();
			Contrato contrato= new Contrato();
			while (rs.next()) {			
				contrato = loadNext( rs); 
				results.add(contrato);
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append(" los contratos del cliente ")
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
	private Contrato loadNext(ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		Contrato contrato= new Contrato();
		contrato.setIdContrato(resultSet.getLong(i++));
		contrato.setFechaContrato(resultSet.getDate(i++));
		contrato.setFechaInicio(resultSet.getDate(i++));
		contrato.setFechaFinal(resultSet.getDate(i++));
		contrato.setPrecioFinal(resultSet.getDouble(i++));
		contrato.setIdMascota(resultSet.getLong(i++));
		contrato.setIdServicio(resultSet.getLong(i++));
		contrato.setIdCuidador(resultSet.getLong(i++));
		contrato.setIdEstado(resultSet.getString(i++).charAt(0));
		contrato.setIdCliente(resultSet.getLong(i++));

		return contrato;
	}


	@Override
	public Contrato create(Connection conection,Contrato c) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;

		Contrato co= null;
		try {

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO CONTRATO (FECHA_CONTRATO, FECHA_INICIO, ");
			stringBuilder.append("FECHA_FIN, PRECIO_FINAL, IDMASCOTA, IDSERVICIO, ");
			stringBuilder.append("IDCUIDADOR, IDESTADO, IDCLIENTE) ");
			stringBuilder.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			String queryString = stringBuilder.toString();

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(c.getFechaContrato().getTime()));
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(c.getFechaInicio().getTime()));
			preparedStatement.setTimestamp(i++, new java.sql.Timestamp(c.getFechaFinal().getTime()));
			preparedStatement.setDouble(i++, c.getPrecioFinal());
			preparedStatement.setLong(i++, c.getIdMascota());
			preparedStatement.setLong(i++, c.getIdServicio());
			preparedStatement.setLong(i++, c.getIdCuidador());
			preparedStatement.setString(i++, String.valueOf(c.getIdEstado()));
			preparedStatement.setLong(i++, c.getIdCliente());
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			co = new Contrato();
			i = 1;
			if(rs.next()) {
				co.setIdContrato(rs.getLong(i++));
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido crear el contrato ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return co;
	}

	@Override
	public boolean updateEstado(Connection conection,Long idContrato, char idEstado) throws DataException {
		PreparedStatement preparedStatement = null;

		try {          
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" UPDATE CONTRATO ");
			stringBuilder.append(" SET IDESTADO = ? ");
			stringBuilder.append(" WHERE IDCONTRATO = ? ");
			String sql = stringBuilder.toString(); 
			logger.trace("sql = "+sql);
			preparedStatement = conection.prepareStatement(sql /*, Statement.RETURN_GENERATED_KEYS*/);

			int i = 1;
			preparedStatement.setString(i++,String.valueOf(idEstado));
			preparedStatement.setLong(i++,idContrato);

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				
				throw new DataException(" No ha sido posible cambiar el estado de 'contrato' ");
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar el contrato ")
			.append(idContrato)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return true;
	}


}
