package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ContratoDTODAO;
import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.model.ContratoDTO;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class ContratoDTODAOImpl implements ContratoDTODAO{
	private static Logger logger= 
			LogManager.getLogger(ContratoDTODAOImpl.class);

	public ContratoDTODAOImpl () {

	}

	@Override
	public List<ContratoDTO> findByIdCliente(Connection conection, Long idCliente) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<ContratoDTO> results=null;
		String sql = null;
		try {

			// Open a connection
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT C.NOMBRE, C.APELLIDOS, CL.NOMBRE, CL.APELLIDOS, M.NOMBRE, E.NOMBRE_ESPECIE, S.NOMBRE_SERVICIO, CO.PRECIO_FINAL, ");
			stringBuilder.append(" CO.FECHA_CONTRATO, CO.IDESTADO, CO.IDCONTRATO, CO.IDMASCOTA, CO.IDSERVICIO, ");
			stringBuilder.append(" CO.IDCUIDADOR, CO.IDCLIENTE, CO.FECHA_INICIO, CO.FECHA_FIN ");
			stringBuilder.append("  FROM CONTRATO CO INNER JOIN ");
			stringBuilder.append("	SERVICIO S ON CO.IDSERVICIO=S.IDSERVICIO INNER JOIN MASCOTA M ON M.IDMASCOTA=CO.IDMASCOTA ");
			stringBuilder.append(" INNER JOIN TIPO E ON M.IDTIPO=E.IDTIPO  INNER JOIN CUIDADOR C ON CO.IDCUIDADOR=C.IDCUIDADOR INNER JOIN CLIENTE CL ON CO.IDCLIENTE=CL.IDCLIENTE ");
			stringBuilder.append(" WHERE CO.IDCLIENTE = ? ");
			// Execute a query
			sql = stringBuilder.toString(); 

			logger.trace("findById:"+idCliente);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCliente);

			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results =new ArrayList<ContratoDTO>();
			ContratoDTO contrato= new ContratoDTO();
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
	@Override
	public List<ContratoDTO> findByIdCuidador(Connection conection, Long idCuidador) throws DataException {
		
		List<ContratoDTO> results=null;
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		String sql = null;
		try {
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT C.NOMBRE, C.APELLIDOS, CL.NOMBRE, CL.APELLIDOS, M.NOMBRE, E.NOMBRE_ESPECIE, S.NOMBRE_SERVICIO, CO.PRECIO_FINAL, ");
			stringBuilder.append(" CO.FECHA_CONTRATO, CO.IDESTADO, CO.IDCONTRATO, CO.IDMASCOTA, CO.IDSERVICIO, ");
			stringBuilder.append(" CO.IDCUIDADOR, CO.IDCLIENTE, CO.FECHA_INICIO, CO.FECHA_FIN ");
			stringBuilder.append("  FROM CONTRATO CO INNER JOIN ");
			stringBuilder.append("	SERVICIO S ON CO.IDSERVICIO=S.IDSERVICIO INNER JOIN MASCOTA M ON M.IDMASCOTA=CO.IDMASCOTA ");
			stringBuilder.append(" INNER JOIN TIPO E ON M.IDTIPO=E.IDTIPO  INNER JOIN CUIDADOR C ON CO.IDCUIDADOR=C.IDCUIDADOR INNER JOIN CLIENTE CL ON CO.IDCLIENTE=CL.IDCLIENTE ");
			stringBuilder.append(" WHERE CO.IDCUIDADOR = ? ");
			// Execute a query
			sql = stringBuilder.toString();

			logger.trace("findById:"+idCuidador);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCuidador);

			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results =new ArrayList<ContratoDTO>();
			ContratoDTO contrato= new ContratoDTO();
			while (rs.next()) {			
				contrato = loadNext( rs); 
				results.add(contrato);
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
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
	private ContratoDTO loadNext(ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		ContratoDTO contratoDto= new ContratoDTO();

		contratoDto.setNombreCuidador(resultSet.getString(i++));
		contratoDto.setApellidosCuidador(resultSet.getString(i++));
		contratoDto.setNombreCliente(resultSet.getString(i++));
		contratoDto.setApellidosCliente(resultSet.getString(i++));
		contratoDto.setNombreMascota(resultSet.getString(i++));
		contratoDto.setNombreEspecie(resultSet.getString(i++));
		contratoDto.setNombreServicio(resultSet.getString(i++));
		contratoDto.setPrecioFinal(resultSet.getDouble(i++));
		contratoDto.setFechaContrato(resultSet.getDate(i++));
		contratoDto.setIdEstado(resultSet.getString(i++).charAt(0));
		contratoDto.setIdContrato(resultSet.getLong(i++));
		contratoDto.setIdMascota(resultSet.getLong(i++));
		contratoDto.setIdServicio(resultSet.getLong(i++));
		contratoDto.setIdCuidador(resultSet.getLong(i++));
		contratoDto.setIdCliente(resultSet.getLong(i++));
		contratoDto.setFechaInicio(resultSet.getDate(i++));
		contratoDto.setFechaFinal(resultSet.getDate(i++));

		return contratoDto;
	}

}
