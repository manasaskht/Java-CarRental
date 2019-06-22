package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.connection.implementation.DatabaseConnection;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("UserSignUpDAO")
public class UserSignUpDAO implements IUserSignUpDAO {

    private IDatabaseConnection databaseConnection;

    @Autowired
    public UserSignUpDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void saveUserSignUpDetails(User user) {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement userDetailsSaveStatement=null;
        try {
            connection = databaseConnection.getDBConnection();

            String query = "insert into User (name, email, city_id, password)"
                    + " values (?, ?, ?, ?)";


            userDetailsSaveStatement = connection.prepareStatement(query);
            userDetailsSaveStatement.setString(1, user.getName());
            userDetailsSaveStatement.setString(2, user.getEmail());
            userDetailsSaveStatement.setInt(3, user.getCity_id());
            userDetailsSaveStatement.setString(4, user.getPassword());

            userDetailsSaveStatement.execute();

        }
        catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(userDetailsSaveStatement!=null)
                {
                    userDetailsSaveStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public User getUserDetails(Integer userId) {

        User user = new User();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement getUserDeatailsStatement=null;

        try {
            connection = databaseConnection.getDBConnection();
            String getUserQuery = "select * from User WHERE user_id=?";
            getUserDeatailsStatement = connection.prepareStatement(getUserQuery);
            getUserDeatailsStatement.setInt(1,userId);
            resultSet = getUserDeatailsStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Integer city_id = resultSet.getInt("city_id");
                String emailId = resultSet.getString("email");

                user.setName(name);
                user.setCity_id(city_id);
                user.setEmail(emailId);
                user.setUserId(userId);

            }



        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(getUserDeatailsStatement!=null)
                {
                    getUserDeatailsStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public void updateUserProfileDetails(User user) {

        Connection connection=null;
        PreparedStatement updateStatement=null;
        try {

            connection = databaseConnection.getDBConnection();
            String updateQuery = "UPDATE User SET name=?, city_id=?, email=? WHERE user_id=?";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, user.getName());
            updateStatement.setInt(2, user.getCity_id());
            updateStatement.setString(3, user.getEmail());
            updateStatement.setInt(4, 1);
            updateStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(updateStatement != null){
                    updateStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean isEmailExist(String email)
    {
        Connection connection=null;
        PreparedStatement emailCheckStatement=null;
        ResultSet resultSet=null;
        boolean isEmail=false;
        try {
            connection = databaseConnection.getDBConnection();
            String queryCheck = "SELECT count(*) from User WHERE email = ?";
            emailCheckStatement = connection.prepareStatement(queryCheck);
            emailCheckStatement.setString(1, email);
             resultSet = emailCheckStatement.executeQuery();
            if (resultSet.next())
            {
                final int count = resultSet.getInt(1);
                if(count>0)
                {
                    isEmail=true;
                }

            }

        }
        catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(emailCheckStatement!=null)
                {
                    emailCheckStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isEmail;
    }

    @Override
    public ArrayList<City> getCityList()
    {
        ArrayList<City> cityArrayList=new ArrayList<City>();
        Connection connection=null;
        ResultSet resultSet=null;
        Statement getCityStatement=null;

        try {
            connection = databaseConnection.getDBConnection();
            String query = "SELECT * FROM  City_Name";
            getCityStatement= connection.createStatement();
            resultSet = getCityStatement.executeQuery(query);

            while (resultSet.next())
            {
                City city =new City();
                Integer cityId = resultSet.getInt("city_id");
                String cityName = resultSet.getString("city_name");
                city.setCityId(cityId);
                city.setCityName(cityName);
                cityArrayList.add(city);

            }



        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(getCityStatement!=null)
                {
                    getCityStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cityArrayList;
    }
}
