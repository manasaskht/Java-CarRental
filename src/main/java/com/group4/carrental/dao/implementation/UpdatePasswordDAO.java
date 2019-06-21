package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.model.Password;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("UpdatePasswordDAO")
public class UpdatePasswordDAO implements IUpdatePasswordDAO {

    private IDatabaseConnection databaseConnection;

    private static final String USER_TABLE = "User";
    private static final String PASSWORD_FIELD = "password";
    private static final String GET_PASSWORD_QUERY = "select password from User where user_id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "update "+USER_TABLE + " SET password = ? where user_id = ?";


    @Autowired
    public UpdatePasswordDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String getUserOldPassword(String userId) {

        String userPassword = null;
        Connection connection = null;
        try {
             connection = databaseConnection.getDBConnection();
            PreparedStatement getPasswordStatement =connection.prepareStatement(GET_PASSWORD_QUERY);
            getPasswordStatement.setString(1,userId);
            ResultSet resultSet = getPasswordStatement.executeQuery();

            if(resultSet.next()){
                userPassword = resultSet.getString(PASSWORD_FIELD);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userPassword;
    }

    @Override
    public void updatePassword(Password password) {

        Connection connection = null;
        try {
            connection = databaseConnection.getDBConnection();
            PreparedStatement updateStattement = connection.prepareStatement(UPDATE_PASSWORD_QUERY);
            updateStattement.setString(1, password.getNewPassword());
            updateStattement.setString(2,"1");
            updateStattement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
