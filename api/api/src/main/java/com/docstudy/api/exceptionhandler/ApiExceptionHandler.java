package com.docstudy.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.docstudy.api.service.exception.ExceptionService;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource; //Api ultilizada para resolver mensagens ela ultiliza as mensagens
										//configuradas la no messages.properties em resources
	
	@ExceptionHandler(ExceptionService.class) // passando o nome da minha classe de excepion la do service
	public ResponseEntity<Object> handleSerivice(ExceptionService ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionModel problema = new ExceptionModel();
		
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(LocalDateTime.now());
		
		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionModel problema = new ExceptionModel();
		List<ExceptionModel.Campo> campos = new ArrayList<ExceptionModel.Campo>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			
								//Fazendo um cast para fildError e pegando o nome do error			
			String nomeCampo = ((FieldError) error).getField(); 
			String mensagem =  error.getDefaultMessage();//   Trazer a mensagem do arquivo de configuração -> messageSource.getMessage(error, LocaleContextHolder.getLocale());  //  
			
			campos.add(new ExceptionModel.Campo(nomeCampo, mensagem));
		}
		
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão invalidos." + 
				"Faça o preenchimento correto e tente novamente");
		problema.setDataHora(LocalDateTime.now());
		
		problema.setCampos(campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
}
