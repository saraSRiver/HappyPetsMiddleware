package com.happypets.aplicacion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.happypets.aplicacion.service.DataException;

public class DBUtils {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.53.124.206:3306/happypets?serverTimezone=UTC&useSSL=false";
//10.53.124.206
//127.0.0.1:3306
	// Database credentials
	static final String USER = "HappypetDVA";
	static final String PASS = "promesa93";

	static {
		try {
			// Register JDBC driver	
			Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final Connection getConnection()
			throws  DataException {		
		try{
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException(e);
		}
	}


	public static final void closeResultSet (ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public static final void closePreparedStatement (PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException se) {
			se.printStackTrace();
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
			se.printStackTrace();
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
}



