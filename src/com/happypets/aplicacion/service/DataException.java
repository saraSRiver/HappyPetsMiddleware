package com.happypets.aplicacion.service;

import com.happypets.exception.HappyPetsException;

public class DataException extends HappyPetsException{
	
	public DataException(String message) {
		super(message);
		
	}
	public DataException(String message, Throwable cause) {
		super(message, cause);
	}
	public DataException(Throwable cause) {
		super(cause);
	}

}
