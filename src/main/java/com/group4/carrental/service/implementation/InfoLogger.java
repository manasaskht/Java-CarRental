package com.group4.carrental.service.implementation;

import org.springframework.stereotype.Service;

@Service
public class InfoLogger extends LoggerService {
	public InfoLogger() {
		super(LoggerService.INFO);
	}

	@Override
	protected void logMessage(String message) {
		logInDatabase("INFO", message);
	}
}
