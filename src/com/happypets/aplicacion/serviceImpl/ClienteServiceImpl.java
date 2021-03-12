package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.happypets.aplicacion.dao.ClienteDao;
import com.happypets.aplicacion.dao.jdbc.ClienteDaoImpl;
import com.happypets.aplicacion.dao.jdbc.TipoEspecieDaoImpl;
import com.happypets.aplicacion.model.Cliente;
import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.service.ClienteService;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.MailService;
import com.happypets.aplicacion.service.exceptions.IncorrectPasswordException;
import com.happypets.aplicacion.service.exceptions.MailException;
import com.happypets.aplicacion.service.exceptions.UserNotFoundException;
import com.happypets.aplicacion.util.DBUtils;

public class ClienteServiceImpl implements ClienteService{
	private static Logger logger= 
			LogManager.getLogger(ClienteServiceImpl.class);
	ClienteDao clienteDao= null;
	private static final StrongPasswordEncryptor ENCRYPTOR = 
			new StrongPasswordEncryptor();
	private MailService mailService=null;

	public ClienteServiceImpl() {
		clienteDao= new ClienteDaoImpl();
		mailService = new MailServiceImpl();
	}
	@Override
	public Cliente findById(Long idCliente) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Cliente cliente = null;
		try {
			conection.setAutoCommit(false);
			cliente =clienteDao.findById(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return cliente;
	}

	@Override
	public Cliente findByEmail(String email) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Cliente cliente = null;
		try {
			conection.setAutoCommit(false);
			cliente =clienteDao.findByEmail(conection, email);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return cliente;
	}
	@Override
	public Cliente registro(Cliente cliente)throws DataException {
		StringBuilder stringBuilder2 = new StringBuilder();
		stringBuilder2.append("Registrando usuario ");
		stringBuilder2.append(cliente.getEmail());
		logger.info(stringBuilder2.toString());

		String encryptedPassword = ENCRYPTOR.encryptPassword
				(cliente.getPassword());
		cliente.setPassword(encryptedPassword);
		Connection conection = DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false); 
			cliente = clienteDao.create(conection,  cliente);
			logger.info("Usuario creado");

			mailService.sendMail("Bienvenido/a a HappyPets",
					"Vamos a ayudarte a cuidar a tus mascotas....",cliente.getEmail());

			commit = true;			

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Usuario ");
			stringBuilder.append(cliente.getEmail());
			stringBuilder.append(" registrado");
			logger.info(stringBuilder.toString());
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} catch (MailException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conection,commit);

		}
		return cliente;
	}

	@Override
	public Cliente login(String email, String password)
			throws DataException, UserNotFoundException,
			IncorrectPasswordException {
		Cliente cliente = null;
		Connection conection = DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			cliente =clienteDao.findByEmail(conection, email);
			commit = true;

			if (cliente==null) {
				// el cliente no existe
				
				throw new UserNotFoundException(email);
			} else {
				// el cliente existe			
				String encryptedPassword = ENCRYPTOR.encryptPassword
						(password);
		
				if (ENCRYPTOR.checkPassword(password, cliente.getPassword())) {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(" Bienvenido/a ");
					stringBuilder.append(cliente.getNombre());
					logger.info(stringBuilder.toString());
					return cliente;				
				} else {
					throw new IncorrectPasswordException();
				}
			}	
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} finally {
			DBUtils.closeConnection(conection,commit);

		}
	}

	
	@Override
	public boolean baja(Long id) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			clienteDao.delete(conection, id);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return true;
	}

	@Override
	public Cliente update(Cliente cliente) throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			clienteDao.update(conection, cliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return cliente;
	}

	@Override
	public boolean uptPromocion(Integer idPromocion, Long idCliente) 
			throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			clienteDao.updateIdPromocion(conection, 
					idPromocion, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return true;
	}

	@Override
	public boolean uptEstadoPromocion(boolean estado, Long idCliente)
			throws DataException {
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		boolean cliente=false;
		try {
			conection.setAutoCommit(false);
			cliente=clienteDao.UptEstadoPromocion
					(conection, estado, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return true;
	}


}
