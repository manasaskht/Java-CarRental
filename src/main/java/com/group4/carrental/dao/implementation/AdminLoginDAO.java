package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IAdminLoginDAO;
import com.group4.carrental.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("AdminLoginDao")
public class AdminLoginDAO implements IAdminLoginDAO {

    private IDatabaseConnection databaseConnection;

    @Autowired
    public AdminLoginDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Admin validateLogin(Admin admin) {
        String query = "select * from Admin where admin_username=? and admin_password=?";
        Connection connection = null;
        Admin adminData = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,admin.getUsername());
            preparedStatement.setString(2,admin.getPassword());
            resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    int id = resultSet.getInt("admin_id");
                    adminData = new Admin();
                    adminData.setAdminId(id);
                    adminData.setUsername(admin.getUsername());
                    adminData.setPassword(admin.getPassword());
                }
            }

        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(connection);
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return adminData;
    }
}
