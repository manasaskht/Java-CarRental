package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.model.Password;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("UpdatePasswordDAO")
public class UpdatePasswordDAO implements IUpdatePasswordDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;


    private final String GET_PASSWORD = "{call getUserPassword(?)}";
    private static final String UPDATE_PASSWORD_QUERY = "{call updatePasswordQuery(?,?)}";


    @Autowired
    public UpdatePasswordDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection,
                             LoggerInstance loggerInstance){
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public String getUserOldPassword(int userId) {

        String userPassword = null;
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            connection = databaseConnection.getDBConnection();
            callableStatement =connection.prepareCall(GET_PASSWORD);
            callableStatement.setInt(1,userId);
            resultSet = callableStatement.executeQuery();

            if(resultSet.next()){
                userPassword = resultSet.getString("password");
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Get user Old password DAO Error :"+e.toString());
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(callableStatement != null){
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                loggerInstance.log(2,"Get user Old password DAO Error :"+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Get user Old password DAO : Success");
        return userPassword;
    }

    @Override
    public void updatePassword(int userId,Password password) {

        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(UPDATE_PASSWORD_QUERY);
            callableStatement.setString(1, password.getNewPassword());
            callableStatement.setInt(2,userId);
            callableStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"User update password DAO Error :"+e.toString());
            e.printStackTrace();


        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                loggerInstance.log(2,"User update password DAO Error :"+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"User update password DAO: Success");

    }
}
