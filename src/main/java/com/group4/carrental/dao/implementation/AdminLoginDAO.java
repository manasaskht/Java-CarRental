package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IAdminLoginDAO;
import com.group4.carrental.model.Admin;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("AdminLoginDao")
public class AdminLoginDAO implements IAdminLoginDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    private final String VALIDATE_ADMIN = "{call validateAdmin(?,?)}";

    @Autowired
    public AdminLoginDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
        this.loggerInstance = loggerInstance;
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Admin validateLogin(Admin admin) {
        Admin adminData = null;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try{
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(VALIDATE_ADMIN);
            callableStatement.setString(1,admin.getUsername());
            callableStatement.setString(2,admin.getPassword());
            resultSet = callableStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    int id = resultSet.getInt("admin_id");
                    adminData = new Admin();
                    adminData.setAdminId(id);
                    adminData.setUsername(admin.getUsername());
                }
            }

        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Admin Login DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeStatementAndConnection(callableStatement,resultSet);
            } catch (SQLException e) {
                loggerInstance.log(2,"Admin DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Admin Login DAO Success");
        return adminData;
    }

}
