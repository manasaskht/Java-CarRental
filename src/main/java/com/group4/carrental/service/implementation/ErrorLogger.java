package com.group4.carrental.service.implementation;

import org.springframework.stereotype.Service;

@Service
public class ErrorLogger extends LoggerService{
	public ErrorLogger() {
		super(LoggerService.ERROR);
	}

	@Override
	protected void logMessage(String message) {
		
		logInDatabase("ERROR", message);
	}

}
