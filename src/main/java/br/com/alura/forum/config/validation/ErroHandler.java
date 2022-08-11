package br.com.alura.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

public abstract class ErroHandler {
	
	@Autowired
	protected MessageSource messageSource;
	
	protected List<ErroDTO> erros = new ArrayList<>();
	
	protected List<ErroDTO> execute(Exception exception) {
		
		List<FieldError> fieldError = ((BindException) exception).getBindingResult().getFieldErrors();
		fieldError.forEach(e -> {			
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDTO errosDTO = new ErroDTO(e.getField(), mensagem);
			erros.add(errosDTO);
		});	
		
		return erros;
	}
	
}
