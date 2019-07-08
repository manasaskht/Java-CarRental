package com.group4.carrental.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.connection.implementation.DatabaseConnection;

@Repository("LoggerDao")
public class LoggerDao {
	 DatabaseConnection databaseConnection = new DatabaseConnection() ;
	/*private IDatabaseConnection databaseConnection;

    @Autowired
    public LoggerDao(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }*/
	
	Date currentLoggerTime;
	SimpleDateFormat formatter;

	public void logInDatabase(String logLevel, String message) {
    	currentLoggerTime = new Date();
		Connection dbconnect = null;
	
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		String datetime = formatter.format(currentLoggerTime);
		PreparedStatement stmt = null;
		try {
			
			dbconnect = databaseConnection.getDBConnection();
			stmt = dbconnect.prepareStatement("Insert into Logging (datetime,loglevel,logMessage) values (?,?, ?)");
			stmt.setString(1, datetime);
			stmt.setString(2, logLevel);
			stmt.setString(3, message);
			stmt.execute();
			
			
			
		} catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
