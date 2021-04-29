package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.happypets.aplicacion.dao.ContratoDao;
import com.happypets.aplicacion.dao.CuidadorDao;
import com.happypets.aplicacion.dao.jdbc.ContratoDaoImpl;
import com.happypets.aplicacion.dao.jdbc.CuidadorDaoImpl;
import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.model.Cuidador;
import com.happypets.aplicacion.service.CuidadorCriteria;
import com.happypets.aplicacion.service.CuidadorService;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.MailService;
import com.happypets.aplicacion.service.exceptions.IncorrectPasswordException;
import com.happypets.aplicacion.service.exceptions.MailException;
import com.happypets.aplicacion.service.exceptions.UserNotFoundException;

import com.happypets.aplicacion.util.DBUtils;

public class CuidadorServiceImpl implements CuidadorService {
	private static final StrongPasswordEncryptor ENCRYPTOR = 
			new StrongPasswordEncryptor();
	private MailService mailService=null;
	private CuidadorDao cuidadorDao=null;
	private ContratoDao contratoDao=null;
	private static Logger logger= 
			LogManager.getLogger(CuidadorServiceImpl.class);
	public CuidadorServiceImpl() {
		cuidadorDao= new CuidadorDaoImpl();
		mailService = new MailServiceImpl();
		contratoDao= new ContratoDaoImpl();
	}
	
	public Cuidador findById(Long idCuidador)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Cuidador cuidador = null;
		try {
			conection.setAutoCommit(false);
			cuidador =cuidadorDao.findById(conection, idCuidador);
			commit = true;
			
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return cuidador;
	}
	public Cuidador findByEmail(String email)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Cuidador cuidador = null;
		try {
			conection.setAutoCommit(false);
			cuidador =cuidadorDao.findByEmail(conection, email);
			commit = true;
			
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return cuidador;
	}
	public List<Cuidador> findByCriteria (CuidadorCriteria 
			cuidadorCriteria) throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Cuidador> cuidadores = null;
		try {
			conection.setAutoCommit(false);
			cuidadores =cuidadorDao.findByCriteria
					(conection, cuidadorCriteria);
			commit = true;
			
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return cuidadores;
		
	}
	public Cuidador registro(Cuidador cuidador)throws DataException,
	MailException{
		StringBuilder stringBuilder2 = new StringBuilder();
		stringBuilder2.append("Registrando usuario ");
		stringBuilder2.append(cuidador.getEmail());
		logger.info(stringBuilder2.toString());
		String encryptedPassword = ENCRYPTOR.encryptPassword
				(cuidador.getPassword());
		cuidador.setPassword(encryptedPassword);
		Connection conection = DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false); 
			cuidador = cuidadorDao.create(conection,cuidador);
			logger.info("Usuario creado");
			
			mailService.sendMail("Bienvenido a HappyPets", 
					"Es un honor tenerte con nosotros. Disfruta trabajando con lo que más de gusta: los animales...",cuidador.getEmail() );
		
			commit = true;			

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Usuario ");
			stringBuilder.append(cuidador.getEmail());
			stringBuilder.append(" registrado");
			
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		} catch (MailException e) {
			logger.warn(e);
		} finally {
			DBUtils.closeConnection(conection,commit);

		}
		return cuidador;
	}
	public Cuidador login (String email, String password)
			throws DataException, UserNotFoundException,
			IncorrectPasswordException{
		Cuidador cuidador = null;
		Connection conection = DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			cuidador =cuidadorDao.findByEmail(conection, email);
			commit = true;
			if (cuidador==null) {
				
				// el cuidador no existe
				throw new UserNotFoundException(email);
			} else {
				// el cuidador existe			
				String encryptedPassword = ENCRYPTOR.encryptPassword
						(password);
		
				if (ENCRYPTOR.checkPassword(password, cuidador.getPassword())) {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(" Bienvenido/a ");
					stringBuilder.append(cuidador.getNombre());
					logger.info(stringBuilder.toString());
					return cuidador;				
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
	
	public boolean baja (Long idCuidador)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			cuidadorDao.delete(conection, idCuidador);
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
	
	public Cuidador update (Cuidador cuidador)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		try {
			conection.setAutoCommit(false);
			cuidador =cuidadorDao.update(conection, cuidador);
			commit = true;
			
		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return cuidador;
		
	}


}
