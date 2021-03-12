package com.happypets.exception;


public class HappyPetsException extends Exception{
	
	public HappyPetsException() {
		super();
	}
	
	public HappyPetsException(String message) {
		super(message);
		
	}
	public HappyPetsException(String message, Throwable cause) {
		super(message, cause);
	}
	public HappyPetsException(Throwable cause) {
		super(cause);
	}

}
