package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ClienteDao;
import com.happypets.aplicacion.dao.DireccionDTODAO;
import com.happypets.aplicacion.dao.DireccionDao;
import com.happypets.aplicacion.dao.IdiomaDao;
import com.happypets.aplicacion.dao.MascotaDao;
import com.happypets.aplicacion.dao.PromocionDao;
import com.happypets.aplicacion.model.Cliente;
import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.service.DataException;

import com.happypets.aplicacion.util.DBUtils;

public class ClienteDaoImpl implements ClienteDao{

	private PromocionDao promocionDao=null;
	private IdiomaDao idiomaDao=null;
	private DireccionDao direccionDao=null;
	private DireccionDTODAO direccionDto = null;
	private MascotaDao mascotaDao = null;
	private static Logger logger= 
			LogManager.getLogger(ClienteDaoImpl.class);
	public ClienteDaoImpl() {
		promocionDao = new PromocionDaoImpl();
		idiomaDao = new IdiomaDaoImpl();
		direccionDao = new DireccionDaoImpl();
		direccionDto = new DireccionDTODAOImpl();
		mascotaDao  = new MascotaDaoImpl();


	}

	@Override
	public Cliente findById(Connection conection,Long idCliente)
			throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Cliente result=null;
		String sql = null;
		try {
			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT CL.IDCLIENTE, CL.NOMBRE, CL.APELLIDOS, CL.EMAIL, ");
			stringBuilder.append(" CL.TELEFONO, CL.PASSWORD, CL.IDPROMOCION, CL.ESTADO, ");
			stringBuilder.append(" CL.FECHA_BAJA  ");
			stringBuilder.append(" FROM CLIENTE CL ");
			stringBuilder.append(" WHERE CL.IDCLIENTE = ?");
			sql = stringBuilder.toString();

			logger.trace("findById:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idCliente);
			rs = preparedStatement.executeQuery();

			// Extract data from result set
			result = new Cliente();
			if (rs.next()) {			
				result = loadNext(conection, rs); 
			}
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar")
			.append(" el cliente ")
			.append(idCliente)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return result;		
	}

	@Override
	public Cliente findByEmail(Connection conection,String email) throws DataException{

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Cliente results= null;
		String sql = null;
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT CL.IDCLIENTE, CL.NOMBRE, CL.APELLIDOS, CL.EMAIL, ");
			stringBuilder.append("	CL.TELEFONO, CL.PASSWORD, CL.IDPROMOCION, CL.ESTADO, ");
			stringBuilder.append(" CL.FECHA_BAJA ");
			stringBuilder.append("	FROM CLIENTE CL ");
			stringBuilder.append("	WHERE CL.EMAIL = ?");
			// Execute a query
			sql =(stringBuilder.toString()); 

			logger.trace("findByEmail:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, email.toUpperCase());

			ResultSet resultSet = preparedStatement.executeQuery();
			results = new Cliente();

			// Extract data from result set
			if (resultSet.next()) {
				results= loadNext(conection, resultSet);
			}
			// Clean-up environment

		} catch (SQLException se) {
			logger.error(se);
			 
			throw new DataException(new StringBuilder()
					.append("No se ha podido encontrar")
					.append(" el cliente ")
					.append(email)
					.append(" ")
					.append(se).toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}


	private Cliente loadNext(Connection conection,ResultSet resultSet)
			throws DataException, SQLException {
		int i = 1;
		Cliente cliente = new Cliente();
		cliente.setIdcliente(resultSet.getLong(i++));				
		cliente.setNombre(resultSet.getString(i++));	
		cliente.setApellidos(resultSet.getString(i++));
		cliente.setEmail(resultSet.getString(i++));
		cliente.setTelefono(resultSet.getString(i++));
		cliente.setPassword(resultSet.getString(i++));
		cliente.setPromocion(promocionDao.findById(conection,
				resultSet.getInt(i++)));
		cliente.setEstadoPromocion(resultSet.getBoolean(i++));
		cliente.setIdiomas(idiomaDao.findByIdiomasCliente(conection,
				cliente.getIdcliente()));
		cliente.setMascotas(mascotaDao.findByIdCliente(conection,
				cliente.getIdcliente()));
		cliente.setDireccion(direccionDto.findByIdCliente(conection,
				cliente.getIdcliente()));
		cliente.setFechaBaja(resultSet.getDate(i++));
		return cliente;
	}

	@Override
	public Cliente create(Connection conection,Cliente cliente)
			throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;

		try {
			// Execute a query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO CLIENTE(NOMBRE, APELLIDOS, EMAIL,  ");
			stringBuilder.append("TELEFONO, PASSWORD, IDPROMOCION, ESTADO) ");
			stringBuilder.append("VALUES(? , ? , ?, ?, ?, ?, ? )");
			String queryString = stringBuilder.toString();

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++,cliente.getNombre());
			preparedStatement.setString(i++, cliente.getApellidos());
			preparedStatement.setString(i++,cliente.getEmail());
			preparedStatement.setString(i++,cliente.getTelefono());
			preparedStatement.setString(i++,cliente.getPassword());
			if(cliente.getPromocion()!=null) {
				preparedStatement.setLong(i++, cliente.getPromocion().getIdpromocion());
			}
			else {
				preparedStatement.setNull(i++, Types.JAVA_OBJECT);
			}
			preparedStatement.setBoolean(i++,cliente.getEstadoPromocion());

			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();

			i = 1;
			if(rs.next()) {
				cliente.setIdcliente(rs.getLong(i++));
			}
			cliente.getDireccion().setIdcliente(cliente.getIdcliente());
			direccionDao.createDirCliente(conection, cliente.getDireccion());
			createIdiomaClient(conection, cliente);


		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
					.append("No se ha podido crear el cliente ")
					.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return cliente;
	}

	private void createIdiomaClient(Connection connection, Cliente cliente) throws DataException {

		PreparedStatement preparedStatement = null;

		try {
			for(Idioma idiom : cliente.getIdiomas()) {
				// Execute a query
				logger.trace("Creating statement...");

				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("INSERT INTO IDIOMACLIENTE(IDCLIENTE, IDIDIOMA) ");
				stringBuilder.append(" VALUES (?, ? )");
				String queryString = stringBuilder.toString();
				preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

				int i = 1;
				preparedStatement.setLong(i++, cliente.getIdcliente());
				preparedStatement.setString(i++, idiom.getIdIdioma());
				preparedStatement.executeUpdate();
			}
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el cliente ")
			.append(cliente.getIdcliente())
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
	}

	@Override
	public Cliente update(Connection conection,Cliente cliente)
			throws DataException {
		PreparedStatement preparedStatement = null;
		Cliente cl = null;
		try { 
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("  UPDATE CLIENTE SET NOMBRE = ? , APELLIDOS= ?, ");
			stringBuilder.append(" EMAIL= ?, TELEFONO= ?, PASSWORD= ?, IDPROMOCION= ?, ESTADO= ? ");
			stringBuilder.append(" WHERE IDCLIENTE= ? ");
			String queryString =stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString, 
					Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setString(i++,cliente.getNombre());
			preparedStatement.setString(i++, cliente.getApellidos());
			preparedStatement.setString(i++,cliente.getEmail());
			preparedStatement.setString(i++,cliente.getTelefono());
			preparedStatement.setString(i++,cliente.getPassword());
			if(cliente.getPromocion()!=null) {

				preparedStatement.setLong(i++, cliente.getPromocion().getIdpromocion());
			}
			else {
				preparedStatement.setNull(i++, Types.JAVA_OBJECT);
			}
			preparedStatement.setBoolean(i++,cliente.getEstadoPromocion());
			preparedStatement.setLong(i++, cliente.getIdcliente());
			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				throw new DataException(" No ha sido posible actualizar 'cliente' ");
			}
			cl = new Cliente();
			cl=findById(conection, cliente.getIdcliente());
			//llamada al metodo create para actualizar
			direccionDao.deleteByCliente(conection, cliente.getIdcliente());
			direccionDao.createDirCliente(conection, cliente.getDireccion());
			idiomaDao.deleteByCliente(conection, cliente.getIdcliente());
			createIdiomaClient(conection, cliente);
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el cliente ")
			.append(cliente.getIdcliente())
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return cl;
	}

	@Override
	public boolean updateIdPromocion(Connection conection, Integer idPromocion,
			Long idCliente) throws DataException {
		PreparedStatement preparedStatement = null;

		try { 
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("UPDATE CLIENTE SET IDPROMOCION = ? ");
			stringBuilder.append(" WHERE IDCLIENTE= ? ");
			String queryString =stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString);
			int i = 1;
			preparedStatement.setInt(i++, idPromocion);
			preparedStatement.setLong(i++, idCliente);
			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				StringBuilder stringBuilder2 = new StringBuilder();
				stringBuilder2.append(" No ha sido posible actualizar ");
				stringBuilder2.append("al beneficiario de la Mascota del Mes ");
				throw new DataException(stringBuilder2.toString());
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar la promoción ")
			.append(idPromocion)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;
	}
	@Override
	public boolean UptEstadoPromocion(Connection conection, boolean estado, Long idCliente) throws DataException {
		PreparedStatement preparedStatement = null;

		try { 

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("UPDATE CLIENTE SET ESTADO = ? ");
			stringBuilder.append(" WHERE IDCLIENTE= ? ");
			String queryString =stringBuilder.toString();
			preparedStatement = conection.prepareStatement(queryString);
			int i = 1;

			preparedStatement.setBoolean(i++, estado);
			preparedStatement.setLong(i++, idCliente);

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {
				StringBuilder stringBuilder2 = new StringBuilder();
				stringBuilder2.append(" No ha sido posible actualizar ");
				stringBuilder2.append("al beneficiario de la Mascota del Mes ");
				throw new DataException(stringBuilder2.toString());
			}

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar el cliente ")
			.append(idCliente)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;
	}
	@Override
	public boolean delete(Connection conection,Long id) throws DataException {

		PreparedStatement preparedStatement = null;

		try {
			idiomaDao.deleteByCliente(conection, id);
			direccionDao.deleteByCliente(conection, id);
			mascotaDao.deleteMascotaByCliente(conection, id);

			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" UPDATE CLIENTE SET FECHA_BAJA= ? ");
			stringBuilder.append("WHERE IDCLIENTE = ? ");
			String queryString = stringBuilder.toString(); 

			preparedStatement = conection.prepareStatement(queryString, 
					Statement.RETURN_GENERATED_KEYS);
			int i=1;
			preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
			preparedStatement.setLong(i++, id);

			int deleteRows = preparedStatement.executeUpdate();

			if (deleteRows == 0) {
				throw new DataException(" No ha sido posible dar de baja al 'cliente' ");
			} 

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se podido encontrar al cliente ")
			.append(id)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return true;
	}






}
