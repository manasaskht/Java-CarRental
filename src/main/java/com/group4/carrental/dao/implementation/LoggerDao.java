package com.group4.carrental.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Repository;
import com.group4.carrental.connection.implementation.DatabaseConnection;
import com.group4.carrental.dao.ILoggerDAO;

@Repository("LoggerDao")
public class LoggerDao implements ILoggerDAO {
	 DatabaseConnection databaseConnection = new DatabaseConnection() ;
	 private final String Logging="{call Logging(?,?,?)}";
	Date currentLoggerTime;
	SimpleDateFormat formatter;

	public void logInDatabase(String logLevel, String message) {
    	currentLoggerTime = new Date();
		Connection dbconnect = null;
		 CallableStatement callableStatement = null;
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		String datetime = formatter.format(currentLoggerTime);
		try {			
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(Logging);
			callableStatement.setString(1,datetime);
            callableStatement.setString(2,logLevel);
            callableStatement.setString(3,message);
            callableStatement.execute();
						
		} catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} finally {
			try {
                databaseConnection.closeStatementAndConnection(callableStatement,null);
            }
			 catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
