package com.group4.carrental.service.implementation;

import org.springframework.stereotype.Service;

@Service
public class WarningLogger extends LoggerService{
	public WarningLogger() {
		super(LoggerService.WARN);
	}

	@Override
	protected void logMessage(String message) {
		logInDatabase("WARN", message);		
	}
}
