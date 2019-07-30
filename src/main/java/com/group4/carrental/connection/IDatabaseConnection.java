package com.group4.carrental.connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDatabaseConnection {

    public Connection getDBConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;

    public void closeDBConnection(Connection connection) throws SQLException;

    public void closeStatementAndConnection(CallableStatement callableStatement, ResultSet resultSet) throws SQLException;

}
