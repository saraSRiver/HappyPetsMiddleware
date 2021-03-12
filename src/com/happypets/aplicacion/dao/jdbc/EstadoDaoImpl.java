package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.EstadoDao;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class EstadoDaoImpl implements EstadoDao{
	private static Logger logger= 
			LogManager.getLogger(EstadoDaoImpl.class);
	public EstadoDaoImpl() {

	}

	@Override
	public boolean update(Connection conection,char idEstado) throws DataException {
		PreparedStatement preparedStatement = null;

		try {          
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" UPDATE ESTADO ");
			stringBuilder.append(" SET IDESTADO = ? ");
			stringBuilder.append(" WHERE IDESTADO = ? ");
			String sql = stringBuilder.toString(); 
			logger.trace("sql = "+sql);
			preparedStatement = conection.prepareStatement(sql);

			int i = 1;
			preparedStatement.setString(i++,String.valueOf(idEstado));

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {

				throw new DataException(" No ha sido posible cambiar el estado ");
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar el estado ")
			.append(idEstado)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return true;
	}

}
