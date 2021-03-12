package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.DireccionDTODAO;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class DireccionDTODAOImpl implements DireccionDTODAO{
	private static Logger logger= 
			LogManager.getLogger(DireccionDTODAOImpl.class);
	public DireccionDTODAOImpl() {

	}
	@Override
	public DireccionDTO findByIdCliente(Connection conection, Long idCliente) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		DireccionDTO result=null;
		String sql = null;
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT D.IDDIRECCION, D.CALLE, D.NUMERO_PORTAL, D.CP, D.PISO,  ");
			stringBuilder.append(" D.IDPOBLACION, D.IDCUIDADOR, D.IDCLIENTE, PO.NOMBRE, ");
			stringBuilder.append(" PR.IDPROVINCIA, PR.NOMBRE, ");
			stringBuilder.append(" P.IDPAIS, P.NOMBRE ");
			stringBuilder.append(" FROM DIRECCION D ");
			stringBuilder.append(" INNER JOIN POBLACION PO ON D.IDPOBLACION=PO.IDPOBLACION ");
			stringBuilder.append(" INNER JOIN PROVINCIA PR ON PO.IDPROVINCIA=PR.IDPROVINCIA ");
			stringBuilder.append(" INNER JOIN PAIS P ON PR.IDPAIS=P.IDPAIS");
			stringBuilder.append(" WHERE D.IDCLIENTE = ? ");
			// Execute a query
			sql = stringBuilder.toString();
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idCliente);

			rs = preparedStatement.executeQuery();
			result=new DireccionDTO();
			if (rs.next()) {
				result= loadNext( rs);	
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" la dirección ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return result;
	}
	public DireccionDTO findByIdCuidador(Connection conection, Long idCuidador) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		DireccionDTO result=null;
		String sql = null;
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT D.IDDIRECCION, D.CALLE, D.NUMERO_PORTAL, D.CP, D.PISO,  ");
			stringBuilder.append(" D.IDPOBLACION, D.IDCUIDADOR, D.IDCLIENTE, PO.NOMBRE, ");
			stringBuilder.append(" PR.IDPROVINCIA, PR.NOMBRE, ");
			stringBuilder.append(" P.IDPAIS, P.NOMBRE ");
			stringBuilder.append(" FROM DIRECCION D ");
			stringBuilder.append(" INNER JOIN POBLACION PO ON D.IDPOBLACION=PO.IDPOBLACION ");
			stringBuilder.append(" INNER JOIN PROVINCIA PR ON PO.IDPROVINCIA=PR.IDPROVINCIA ");
			stringBuilder.append(" INNER JOIN PAIS P ON PR.IDPAIS=P.IDPAIS");
			stringBuilder.append(" WHERE D.IDCUIDADOR = ? ");
			// Execute a query
			sql = stringBuilder.toString();
			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idCuidador);

			rs = preparedStatement.executeQuery();
			result=new DireccionDTO();
			if (rs.next()) {
				result= loadNext( rs);	
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" la dirección ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return result;
	}

	private DireccionDTO loadNext(ResultSet resultSet) 
			throws  SQLException {

		DireccionDTO dirDto= new DireccionDTO();
		int i=1;
		dirDto.setIdDireccion(resultSet.getLong(i++));
		dirDto.setCalle(resultSet.getString(i++));
		dirDto.setPortal(resultSet.getInt(i++));
		dirDto.setCp(resultSet.getInt(i++));
		dirDto.setPiso(resultSet.getInt(i++));
		dirDto.setIdPoblacion(resultSet.getLong(i++));
		dirDto.setIdcuidador(resultSet.getLong(i++));
		dirDto.setIdcliente(resultSet.getLong(i++));
		dirDto.setNombrePoblacion(resultSet.getString(i++));
		dirDto.setIdProvincia(resultSet.getLong(i++));
		dirDto.setNombreProvincia(resultSet.getString(i++));
		dirDto.setIdpais(resultSet.getLong(i++));
		dirDto.setNombrePais(resultSet.getString(i++));

		return dirDto;
	}
} 
