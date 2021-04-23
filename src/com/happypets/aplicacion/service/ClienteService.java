package com.happypets.aplicacion.service;



import java.sql.SQLException;

import com.happypets.aplicacion.model.Cliente;
import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.service.exceptions.IncorrectPasswordException;
import com.happypets.aplicacion.service.exceptions.MailException;
import com.happypets.aplicacion.service.exceptions.UserNotFoundException;

public interface ClienteService {
	public Cliente findById(Long idCliente)throws DataException;
	public Cliente findByEmail (String email)throws DataException;
	public Cliente registro (Cliente cliente)throws DataException;
	public Cliente login (String email, String password)
			throws DataException, MailException,
			UserNotFoundException, IncorrectPasswordException;
	
	public boolean baja(Long id)throws DataException;
	public Cliente update (Cliente cliente)throws DataException, IncorrectPasswordException;
	public boolean uptPromocion (Integer idPromocion, Long idCliente)
			throws DataException;
	public boolean uptEstadoPromocion (boolean estado, Long idCliente)
			throws DataException;
	
}
