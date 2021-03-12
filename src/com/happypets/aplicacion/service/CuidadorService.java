package com.happypets.aplicacion.service;


import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Cuidador;
import com.happypets.aplicacion.service.exceptions.IncorrectPasswordException;
import com.happypets.aplicacion.service.exceptions.MailException;
import com.happypets.aplicacion.service.exceptions.UserNotFoundException;

public interface CuidadorService {
	public Cuidador findById(Long idCuidador)throws DataException;
	public Cuidador findByEmail(String email)throws DataException;
	public List<Cuidador> findByCriteria (CuidadorCriteria cuidadorCriteria)
			throws DataException;
	public Cuidador registro(Cuidador cuidador)throws DataException, MailException;
	public Cuidador login (String email, String password)
			throws DataException, UserNotFoundException, IncorrectPasswordException;
	public boolean baja (Long idCuidador)throws DataException;

	public Cuidador update (Cuidador cuidador)throws DataException;
}
