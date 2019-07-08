package com.group4.carrental.service.implementation;

import org.springframework.stereotype.Service;

@Service
public class FatalLogger extends LoggerService {
	public FatalLogger() {
		super(LoggerService.FATAL);
	}

	@Override
	protected void logMessage(String message) {
		logInDatabase("FATAL", message);
	}
}
