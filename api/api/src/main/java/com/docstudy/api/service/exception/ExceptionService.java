package com.docstudy.api.service.exception;


//Classe para representar um erro de negócio este mesmo erro será tratado no na classe ExceptionHandler
public class ExceptionService extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExceptionService(String message) {
		super(message);
	}

}
