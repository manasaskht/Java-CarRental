package com.group4.carrental.connection.implementation;

import com.group4.carrental.config.DatabaseConfig;
import com.group4.carrental.connection.IDatabaseConnection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("DatabaseConnection")
@Component
public class DatabaseConnection implements IDatabaseConnection {

    public static Connection dbConnection = null;
    private DatabaseConfig databaseConfig;

    public DatabaseConnection(){
        this.databaseConfig = DatabaseConfig.getDatabaseConfig();
    }

    @Override
    public Connection getDBConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if(dbConnection != null){
            return dbConnection;
        }else {
            String username = this.databaseConfig.getUserName();
            String password = this.databaseConfig.getPassword();
            String databaseUrl = this.databaseConfig.getDatabaseUrl();
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            dbConnection = DriverManager.getConnection(databaseUrl,username,password);
            return dbConnection;
        }
    }

    @Override
    public void closeDBConnection(Connection connection) throws SQLException {
        if(dbConnection != null) {
            dbConnection.close();
            dbConnection = null;
        }
    }

    @Override
    public void closeStatementAndConnection(CallableStatement callableStatement, ResultSet resultSet) throws SQLException {
        if(callableStatement != null) {
            callableStatement.close();
        }
        if(resultSet != null) {
            resultSet.close();
        }
        this.closeDBConnection(dbConnection);
    }
}
