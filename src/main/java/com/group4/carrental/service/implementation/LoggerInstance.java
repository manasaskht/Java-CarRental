package com.group4.carrental.service.implementation;

import org.springframework.stereotype.Service;

/* reference: design patterns lecture samples */
@Service
public class LoggerInstance {

	private LoggerService logger;
	
	private static LoggerInstance loggerInstance;
	
	private LoggerInstance() {
		logger = new InfoLogger();
		ErrorLogger errorLogger = new ErrorLogger();
		logger.setNextLogger(errorLogger);
		WarningLogger warnLogger = new WarningLogger();
		errorLogger.setNextLogger(warnLogger);
	}
	
	public static LoggerInstance getInstance() {
		if(null == loggerInstance) {
			loggerInstance = new LoggerInstance();
		}
		return loggerInstance;
	}
	
	public void log(int level,String msg) {
		logger.log(level, msg);		
	}
}
