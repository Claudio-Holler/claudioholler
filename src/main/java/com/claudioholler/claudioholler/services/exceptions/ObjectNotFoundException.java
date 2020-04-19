package com.claudioholler.claudioholler.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg){
		super(msg);///chama nesse caso a classe RuntimeException
	}
	
	public ObjectNotFoundException (String msg, Throwable cause) {
		super(msg, cause); 
	}

}
