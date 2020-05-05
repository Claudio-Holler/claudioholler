package com.claudioholler.claudioholler.services.exceptions;

public class DataIntegrityException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg){
		super(msg);///chama nesse caso a classe RuntimeException
	}
	
	public DataIntegrityException (String msg, Throwable cause) {
		super(msg, cause); 
	}

}
