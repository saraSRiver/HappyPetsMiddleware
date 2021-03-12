package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.MascotaDao;
import com.happypets.aplicacion.model.Mascota;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;


public class MascotaDaoImpl implements MascotaDao{
	private static Logger logger= 
			LogManager.getLogger(MascotaDaoImpl.class);
	public MascotaDaoImpl() {

	}
	@Override
	public Mascota findById(Connection conection,Long idMascota)throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Mascota results=null;
		String sql = null;
		try {
			// Ejecuta la query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT M.IDMASCOTA, M.NOMBRE, M.DESCRIPCION, M.IDTIPO, ");
			stringBuilder.append("M.FECHANACIMIENTO, M.VACUNADO, M.BUENOCONANIMALES, ");
			stringBuilder.append("M.BUENOCONNIÑOS, M.ALERGIA, M.TRATAMIENTO, ");
			stringBuilder.append("M.DESPARASITADO, M.MICROCHIP, M.IDCLIENTE, M.FECHA_BAJA ");
			stringBuilder.append(" FROM MASCOTA M ");
			stringBuilder.append(" WHERE M.IDMASCOTA= ? ");
			sql = stringBuilder.toString();

			logger.trace("findById:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idMascota);
			rs = preparedStatement.executeQuery();	

			// Extract data from result set
			results = new Mascota();
			if (rs.next()) {			
				results = loadNext(conection, rs); 
			}

		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar la mascota ")
			.append(idMascota)
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
	public List<Mascota> findByIdCliente(Connection conection,Long idCliente) throws DataException{

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Mascota> results= null;
		String sql = null;
		try {
			// Open a connection
			logger.trace("Connecting to database...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT M.IDMASCOTA, M.NOMBRE, M.DESCRIPCION, M.IDTIPO, ");
			stringBuilder.append("M.FECHANACIMIENTO, M.VACUNADO, M.BUENOCONANIMALES, ");
			stringBuilder.append("M.BUENOCONNIÑOS, M.ALERGIA, M.TRATAMIENTO, ");
			stringBuilder.append("M.DESPARASITADO, M.MICROCHIP, M.IDCLIENTE, M.FECHA_BAJA ");
			stringBuilder.append(" FROM MASCOTA M ");
			stringBuilder.append(" WHERE M.IDCLIENTE= ? ");
			stringBuilder.append(" ORDER BY M.IDMASCOTA ");
			// Execute a query
			sql = stringBuilder.toString(); 

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setLong(i++,idCliente);
			rs = preparedStatement.executeQuery();
			results = new ArrayList<Mascota>();
			Mascota mascota= new Mascota();

			// Extract data from result set
			while (rs.next()) {
				mascota = loadNext(conection, rs);				
				results.add(mascota);
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar las mascotas ")
			.append(" del cliente ")
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

	private Mascota loadNext(Connection conection,ResultSet resultset)
			throws SQLException {
		int i = 1;
		Mascota mascota= new Mascota();
		mascota.setIdMascota(resultset.getLong(i++));
		mascota.setNombre(resultset.getString(i++));
		mascota.setDescripcion(resultset.getString(i++));
		mascota.setIdTipo(resultset.getLong(i++));
		mascota.setFechaNacimiento(resultset.getDate(i++));
		mascota.setVacunado(resultset.getBoolean(i++));
		mascota.setBuenoConAnimales(resultset.getBoolean(i++));
		mascota.setBuenoConNinos(resultset.getBoolean(i++));
		mascota.setAlergia(resultset.getBoolean(i++));
		mascota.setTratamiento(resultset.getBoolean(i++));
		mascota.setDesparasitado(resultset.getBoolean(i++));
		mascota.setMicrochip(resultset.getBoolean(i++));
		mascota.setIdCliente(resultset.getLong(i++));
		mascota.setFechaBaja(resultset.getDate(i++));

		return mascota;
	}

	@Override
	public Mascota create(Connection conection,Mascota mascota)throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Mascota m= null;
		try {
			// Open a connection
			logger.trace("Connecting to database...");

			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO MASCOTA(NOMBRE, ");
			stringBuilder.append(" DESCRIPCION, IDTIPO, FECHANACIMIENTO, VACUNADO, ");
			stringBuilder.append(" BUENOCONANIMALES, BUENOCONNIÑOS, ALERGIA, TRATAMIENTO, ");
			stringBuilder.append(" DESPARASITADO, MICROCHIP, IDCLIENTE) ");
			stringBuilder.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			String queryString = stringBuilder.toString();

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++,mascota.getNombre());
			preparedStatement.setString(i++,mascota.getDescripcion());
			preparedStatement.setLong(i++,mascota.getIdTipo());
			preparedStatement.setDate(i++, new java.sql.Date(mascota.getFechaNacimiento().getTime()));
			preparedStatement.setBoolean(i++, mascota.getVacunado());
			preparedStatement.setBoolean(i++, mascota.getBuenoConAnimales());
			preparedStatement.setBoolean(i++, mascota.getBuenoConNinos());
			preparedStatement.setBoolean(i++, mascota.getAlergia());
			preparedStatement.setBoolean(i++, mascota.getTratamiento());
			preparedStatement.setBoolean(i++, mascota.getDesparasitado());
			preparedStatement.setBoolean(i++, mascota.getMicrochip());
			preparedStatement.setLong(i++, mascota.getIdCliente());
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			m  = new Mascota();
			i = 1;
			if(rs.next()) {
				m.setIdMascota(rs.getLong(i++));
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido crear la mascota ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return m;
	}

	@Override
	public Mascota update(Connection conection,Mascota mascota) throws DataException {

		PreparedStatement preparedStatement = null;
		Mascota m;
		try { 

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("  UPDATE MASCOTA SET NOMBRE= ? , DESCRIPCION= ?, ");
			stringBuilder.append(" IDTIPO= ?, FECHANACIMIENTO= ?, VACUNADO= ?, BUENOCONANIMALES= ?, ");
			stringBuilder.append(" BUENOCONNIÑOS= ?, ALERGIA= ?, TRATAMIENTO= ?, ");
			stringBuilder.append(" DESPARASITADO= ?, MICROCHIP= ? ");
			stringBuilder.append(" WHERE IDMASCOTA= ?");
			String queryString = stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setString(i++, mascota.getNombre());
			preparedStatement.setString(i++, mascota.getDescripcion());
			preparedStatement.setLong(i++, mascota.getIdTipo());
			preparedStatement.setDate(i++, new java.sql.Date(mascota.getFechaNacimiento().getTime()));
			preparedStatement.setBoolean(i++, mascota.getVacunado());
			preparedStatement.setBoolean(i++, mascota.getBuenoConAnimales());
			preparedStatement.setBoolean(i++, mascota.getBuenoConNinos());
			preparedStatement.setBoolean(i++, mascota.getAlergia());
			preparedStatement.setBoolean(i++, mascota.getTratamiento());
			preparedStatement.setBoolean(i++, mascota.getDesparasitado());
			preparedStatement.setBoolean(i++, mascota.getMicrochip());
			preparedStatement.setLong(i++, mascota.getIdMascota());

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				throw new SQLException(" No ha sido posible añadir filas a 'mascota' ");
			}
			m=new Mascota();
			m=findById(conection, mascota.getIdMascota());

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha encontrado la mascota ")
			.append(mascota.getIdMascota())
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return m;
	}
	@Override
	public boolean deleteMascota(Connection conection,Long id) throws DataException {

		PreparedStatement preparedStatement = null;

		try {
			logger.trace("Creating Statement...");
			String queryString = " UPDATE MASCOTA SET FECHA_BAJA= ? WHERE IDMASCOTA = ? "; 

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
			preparedStatement.setLong(i++, id);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new SQLException(" No ha sido posible eliminar 'mascota' ");
			} 
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido dar de baja ")
			.append(" a la mascota ")
			.append(id)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;

	}
	@Override
	public boolean deleteMascotaByCliente(Connection conection,Long idCliente) throws DataException {

		PreparedStatement preparedStatement = null;

		try {
			logger.trace("Creating Statement...");
			String queryString = " UPDATE MASCOTA SET FECHA_BAJA= ? WHERE IDCLIENTE = ? "; 

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
			preparedStatement.setLong(i++, idCliente);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new SQLException(" No ha sido posible eliminar 'mascota' ");
			} 

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido dar de baja ")
			.append(" a la mascota ")
			.append(idCliente)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;

	}
}
