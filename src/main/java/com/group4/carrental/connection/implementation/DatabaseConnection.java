package com.group4.carrental.connection.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository("DatabaseConnection")
@Component
public class DatabaseConnection implements IDatabaseConnection {

    public static Connection dbConnection = null;

    @Override
    public Connection getDBConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(dbConnection != null){
            return dbConnection;
        }else {
            String username = "CSCI5308_4_DEVINT_USER";
            String password = "CSCI5308_4_DEVINT_4202";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            dbConnection = DriverManager.getConnection(
                    "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_4_DEVINT?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
            return dbConnection;
        }
    }

    @Override
    public void closeDBConnection(Connection connection) throws SQLException {
        dbConnection.close();
    }
}
