package com.happypets.aplicacion.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.configuration.ConfigurationManager;
import com.happypets.aplicacion.service.DataException;


public class DBUtils {
	private static Logger logger = LogManager.getLogger(DBUtils.class);
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "jdbc.driver.classname";
	static final String DB_URL = "jdbc.url";

	// Database credentials
	static final String USER = "jdbc.user";
	static final String PASS = "jdbc.password";
	private static ConfigurationManager cfg = ConfigurationManager.getInstance();

	static {
		try {
			// Register JDBC driver	
			Class.forName(cfg.getParameter(JDBC_DRIVER));
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static final Connection getConnection()
			throws  DataException {		
		try{
			return DriverManager.getConnection(cfg.getParameter(DB_URL), cfg.getParameter(USER), cfg.getParameter(PASS));
		} catch (SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}
	}


	public static final void closeResultSet (ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException se) {
			logger.error(se);
		}
	}

	public static final void closePreparedStatement (PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException se) {
			logger.error(se);
		}
	}

	public static final void closeConnection (Connection conection ) throws DataException {
		try {

			if (!conection.getAutoCommit()) {
				throw new DataException("Autocommit disabled. Commit or Rollback should be done explicitly.");
			}			

			conection.close();
		} catch (SQLException e) {
			throw new DataException(e);
		}
	}

	public static final void closeStatement(Statement statement) {
		try {
			if(statement!=null) {
				statement.close();
			}
		}catch (SQLException se) {
			logger.error(se);
		}
	}

	public static void closeConnection(Connection connection, boolean commitOrRollback)
			throws DataException {
		if (connection != null) {
			try {

				if (commitOrRollback) {
					connection.commit();
				} else {
					connection.rollback();                        
				}
			} catch (SQLException e) { 
				throw new DataException(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DataException(e);
				}
			}
		}
	}
	public static final int getTotalRows(ResultSet resultSet) throws SQLException {
		int totalRows = 0;
		if(resultSet.last()) {
			totalRows = resultSet.getRow();
		}
		resultSet.beforeFirst();
		return totalRows;
	}
}



