package com.group4.carrental.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseConnection {

    public Connection getDBConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;

    public void closeDBConnection(Connection connection) throws SQLException;

}
