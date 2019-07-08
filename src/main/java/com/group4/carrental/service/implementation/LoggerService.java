package com.group4.carrental.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;	
import java.util.Date;
import com.group4.carrental.dao.implementation.LoggerDao;

/* ref: design patterns lecture samples */
@Service("LoggerService")
public abstract class LoggerService {
	LoggerDao loggerDao = new LoggerDao();

	/*
	 * private LoggerDao loggerDao ;
	 * 
	 * @Autowired public LoggerService(@Qualifier("LoggerDao")LoggerDao loggerDao ){
	 * this.loggerDao = loggerDao;
	 * 
	 * }
	 */

	public static final int INFO = 0;
	public static final int WARN = 1;
	public static final int ERROR = 2;
	public static final int FATAL = 3;
	SimpleDateFormat formatter;
	Date currentLoggerTime;
	protected int logLevel;
	protected LoggerService nextLogger;
	protected static final String LOG_FORMAT = "%s\t%s\t%s\t%n";

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
	protected void logInConsole(String logLevel, String msg) {
		currentLoggerTime = new Date();
		System.out.printf(LoggerService.LOG_FORMAT, currentLoggerTime, logLevel, msg);
	}

	protected void logInDatabase(String logLevel, String message) {
		loggerDao.logInDatabase(logLevel, message);
	}

}
