package com.daiyida.api.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceUtil {
	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String code, Object[] args){
		return messageSource.getMessage(code, args, Locale.ROOT);
	}
}
