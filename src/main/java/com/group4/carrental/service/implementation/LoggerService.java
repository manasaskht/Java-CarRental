package com.group4.carrental.service.implementation;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import com.group4.carrental.dao.ILoggerDAO;
import com.group4.carrental.dao.implementation.LoggerDao;

/* reference: design patterns lecture samples */

@Service("LoggerService")
public abstract class LoggerService {
	ILoggerDAO loggerDao = new LoggerDao();

	public static final int INFO = 0;
	public static final int WARN = 1;
	public static final int ERROR = 2;
	
	SimpleDateFormat formatter;
	protected int logLevel;
	protected LoggerService nextLogger;


	public LoggerService(int level) {
		nextLogger = null;
		logLevel = level;
		formatter = new SimpleDateFormat("dd-MM-yyyy");
	}

	public void setNextLogger(LoggerService nextLogger) {
		this.nextLogger = nextLogger;
	}

	public void log(int level, String message) {
		if (logLevel == level) {
			logMessage(message);
		} else if (null != nextLogger) {
			nextLogger.log(level, message);
		} else if (null == nextLogger) {
			log(INFO, "Invalid log level information. Setting it to INFO");
			log(INFO, message);
		}
	}

	protected abstract void logMessage(String message);
	protected void logInDatabase(String logLevel, String message) {
		loggerDao.logInDatabase(logLevel, message);
	}

}
