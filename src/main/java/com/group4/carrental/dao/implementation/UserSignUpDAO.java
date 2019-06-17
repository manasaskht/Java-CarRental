package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.connection.implementation.DatabaseConnection;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository("UserSignUpDAO")
public class UserSignUpDAO implements IUserSignUpDAO {

    private IDatabaseConnection databaseConnection;
    @Autowired
    public UserSignUpDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void SaveUserSignUpDetails(User user) {

        try{
            Connection connection= databaseConnection.getDBConnection();

            String query= "insert into User (name, email, city, password)"
                    + " values (?, ?, ?, ?)";


            PreparedStatement userDetailsSave = connection.prepareStatement(query);
            userDetailsSave.setString (1,user.getName());
            userDetailsSave.setString (2,user.getEmail());
            userDetailsSave.setString   (3,user.getCity());
            userDetailsSave.setString(4,user.getPassword());

            userDetailsSave.execute();
            connection.close();
        }
        catch(Exception e)
        {

        }

    }
}
