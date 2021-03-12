package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.DireccionDao;
import com.happypets.aplicacion.model.Direccion;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.BDNullUtils;
import com.happypets.aplicacion.util.DBUtils;

public class DireccionDaoImpl implements DireccionDao{
	private static Logger logger= 
			LogManager.getLogger(DireccionDaoImpl.class);
	public DireccionDaoImpl() {

	}

	@Override
	public Direccion findById(Connection conection,Long idDireccion) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Direccion result= null;
		String sql = null;
		try {
			// Ejecuta la query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT D.IDDIRECCION, D.CALLE, D.NUMERO_PORTAL, D.CP, ");
			stringBuilder.append(" D.PISO, D.IDPOBLACION ");
			stringBuilder.append("	FROM DIRECCION D WHERE D.IDDIRECCION= ?  ");
			sql = stringBuilder.toString();

			logger.trace("findById: "+sql);

			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idDireccion);
			rs = preparedStatement.executeQuery();

			// Extract data from result set
			result = new Direccion();

			if (rs.next()) {			
				result = loadNext(rs); 
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" la direccion ")
			.append(idDireccion)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return result;		
	}

	private Direccion loadNext (ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		Direccion direccion= new Direccion();
		direccion.setIdDireccion(resultSet.getLong(i++));
		direccion.setCalle(resultSet.getString(i++));
		direccion.setPortal(resultSet.getInt(i++));
		direccion.setCp(resultSet.getInt(i++));
		direccion.setPiso(resultSet.getInt(i++));

		return direccion;

	}
	@Override
	public Direccion createDirCuidador(Connection conection,DireccionDTO dirDto) 
			throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Direccion dir=null;
		try {          

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" INSERT INTO DIRECCION (CALLE, NUMERO_PORTAL, ");
			stringBuilder.append("CP, IDPOBLACION, IDCUIDADOR, PISO) VALUES (? , ? , ? , ? , ? , ? )");
			String queryString =stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, dirDto.getCalle());
			preparedStatement.setInt(i++, dirDto.getPortal());
			preparedStatement.setInt(i++,dirDto.getCp());
			preparedStatement.setLong(i++, dirDto.getIdPoblacion());
			preparedStatement.setLong(i++, dirDto.getIdcuidador());
			BDNullUtils.toNull(preparedStatement,i++, dirDto.getPiso());
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			dir  = new Direccion();
			i = 1;
			if(rs.next()) {
				dir.setIdDireccion(rs.getLong(i++));
			}
		}catch (SQLException se) {
			logger.error(se);
			throw new DataException("No se ha podido encontrar "
					+ " la dirección "+se);
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return dir;
	}

	@Override
	public Direccion createDirCliente(Connection conection,DireccionDTO dirDto) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Direccion dir=null;
		try {          

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" INSERT INTO DIRECCION (CALLE, NUMERO_PORTAL, ");
			stringBuilder.append("CP, IDPOBLACION, IDCLIENTE, PISO) VALUES (? , ? , ? , ? , ? , ? )");
			String queryString =stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, dirDto.getCalle());
			preparedStatement.setInt(i++, dirDto.getPortal());
			preparedStatement.setInt(i++,dirDto.getCp());
			preparedStatement.setLong(i++, dirDto.getIdPoblacion());
			preparedStatement.setLong(i++, dirDto.getIdcliente());
			BDNullUtils.toNull(preparedStatement,i++, dirDto.getPiso());

			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			dir  = new Direccion();
			i = 1;
			if(rs.next()) {
				dir.setIdDireccion(rs.getLong(i++));
			}
		}catch (SQLException se) {
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
		return dir;
	}

	@Override
	public Direccion update(Connection conection,DireccionDTO dirDto) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Direccion dir=null;

		try {    
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("UPDATE DIRECCION SET CALLE= ?, NUMERO_PORTAL= ?, ");
			stringBuilder.append(" CP= ?, IDPOBLACION= ?, PISO= ?  where IDDIRECCION = ? ");
			String queryString  =stringBuilder.toString(); 
			preparedStatement = conection.prepareStatement(queryString);
			int i = 1;
			preparedStatement.setString(i++, dirDto.getCalle());
			preparedStatement.setInt(i++, dirDto.getPortal());
			preparedStatement.setInt(i++,dirDto.getCp());
			preparedStatement.setLong(i++, dirDto.getIdPoblacion());
			preparedStatement.setInt(i++, dirDto.getPiso());
			preparedStatement.setLong(i++, dirDto.getIdDireccion());
			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				throw new SQLException(" No ha sido posible añadir filas a 'direccion' ");
			}
			dir=findById(conection, dirDto.getIdDireccion());
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
		return dir;
	}

	@Override
	public boolean deleteByCliente(Connection conection,Long idCliente) throws DataException {
		// 
		PreparedStatement preparedStatement = null;
		Boolean delete = false;
		try {  

			logger.trace("Creating Statement...");
			String queryString = " DELETE FROM DIRECCION  WHERE  IDCLIENTE= ? "; 

			preparedStatement = conection.prepareStatement(queryString);
			preparedStatement.setLong(1, idCliente);

			int deleteRows = preparedStatement.executeUpdate();
			if (deleteRows == 0) {
				throw new SQLException(" No ha sido posible eliminar la dirección del cliente ");
			} 
			else {
				delete = true;
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar la dirección ")
			.append("del cliente ")
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
		// 
		PreparedStatement preparedStatement = null;

		Boolean delete = false;
		try {  

			logger.trace("Creating Statement...");
			String queryString = "  DELETE FROM DIRECCION  WHERE  IDCUIDADOR= ? "; 

			preparedStatement = conection.prepareStatement(queryString);
			preparedStatement.setLong(1, idCuidador);

			int deleteRows = preparedStatement.executeUpdate();
			if (deleteRows == 0) {
				throw new SQLException(" No ha sido posible eliminar la dirección del cuidador ");
			} 
			else {
				delete = true;
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar la dirección ")
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
