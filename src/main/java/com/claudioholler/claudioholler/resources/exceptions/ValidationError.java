package com.claudioholler.claudioholler.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<CampoMensagem> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<CampoMensagem> getErros() {
		return erros;
	}
	
	public void addError(String nomeCampo, String message){
		erros.add(new CampoMensagem(nomeCampo, message));
	}

	

}
