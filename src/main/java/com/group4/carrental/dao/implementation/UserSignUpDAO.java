package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("UserSignUpDAO")
public class UserSignUpDAO implements IUserSignUpDAO {

    private IDatabaseConnection databaseConnection;
    @Autowired
    private LoggerInstance log;

    @Autowired
    public UserSignUpDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private final String save_user_details="{call saveUserSignUpDetails(?,?,?,?)}";
    private final String user_details="{call getUserDetails(?)}";
    private final String update_user_profile="{call updateUserProfileDetails(?,?,?)}";
    private final String email_exist="{call isEmailExist(?)}";
    private final String city_list="{call getCityList()}";


    @Override
    public void saveUserSignUpDetails(User user) {
        log.log(0,"In Dao:save user details in database");

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(save_user_details);

            callableStatement.setString(1, user.getName());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setInt(3, user.getCity_id());
            callableStatement.setString(4, user.getPassword());

            callableStatement.execute();

        }
        catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.log(2,"In Dao: saveUserSignUpDetails"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(callableStatement!=null)
                {
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                log.log(2,"In Dao:saveUserSignUpDetails"+e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public User getUserDetails(Integer userId) {

        log.log(0,"In Dao:getUserDetails from database");
        User user = new User();
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(user_details);

            callableStatement.setInt(1,userId);
            resultSet = callableStatement.executeQuery();

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
            log.log(2,"In Dao:getUserDetails"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(callableStatement!=null)
                {
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                log.log(2,"In Dao:getUserDetails"+e.getMessage());
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public void updateUserProfileDetails(User user) {


        Connection connection=null;
        CallableStatement callableStatement = null;
        try {

            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(update_user_profile);
            callableStatement.setString(1, user.getName());
            callableStatement.setInt(2, user.getCity_id());
            callableStatement.setInt(3,user.getUserId());
            callableStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.log(2,"In Dao:updateUserProfileDetails"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(callableStatement != null){
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                log.log(2,"In Dao:updateUserProfileDetails"+e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean isEmailExist(String email)
    {
        Connection connection=null;
        CallableStatement callableStatement = null;
        ResultSet resultSet=null;
        boolean isEmail=false;
        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(email_exist);
            callableStatement.setString(1, email);
            resultSet = callableStatement.executeQuery();
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
            log.log(2,"In Dao:isEmailExist"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(callableStatement!=null)
                {
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                log.log(2,"In Dao:isEmailExist"+e.getMessage());
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
        CallableStatement callableStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(city_list);
            resultSet = callableStatement.executeQuery();

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
            log.log(2,"In Dao:getCityList"+e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(callableStatement!=null)
                {
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
                log.log(2,"In Dao:getCityList"+e.getMessage());
            }
        }

        return cityArrayList;
    }
}
