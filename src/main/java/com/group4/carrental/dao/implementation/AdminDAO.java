package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IAdminDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("AdminDao")
public class AdminDAO implements IAdminDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    @Autowired
    public AdminDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public ArrayList<AdminCar> getAllCars(int status) {
        String query = "select car_id,car_model,car_description,car_rate,(select city_name from City_Name where city_id = car_city) as car_city,(select name from User where user_id = owner_id) as owner_name, (select email from User where user_id = owner_id) as owner_email, car_image from Car where car_status_id=?";
        ArrayList<AdminCar> carArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,status);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    AdminCar car = new AdminCar();
                    int id = resultSet.getInt("car_id");
                    car.setCarId(id);
                    String ownerName = resultSet.getString("owner_name");
                    car.setCarOwnerName(ownerName);
                    String ownerEmail = resultSet.getString("owner_email");
                    car.setCarOwnerMail(ownerEmail);
                    String carCity = resultSet.getString("car_city");
                    car.setCarCity(carCity);
                    String description = resultSet.getString("car_description");
                    car.setCarDescription(description);
                    double carRate = resultSet.getDouble("car_rate");
                    car.setCarRate(carRate);
                    String model = resultSet.getString("car_model");
                    car.setCarModel(model);
                    Blob carImage = resultSet.getBlob("car_image");
                    String carImageData = null;
                    byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                    carImageData = Base64.encodeBase64String(imageBytes);
                    car.setCarImage(carImageData);
                    carArrayList.add(car);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Admin DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Admin DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Admin DAO fetch Cars Success");
        return carArrayList;
    }

    @Override
    public void blackListCar(int id) {
        String query = "update Car set car_status_id = 3 where car_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            loggerInstance.log(0,"Admin DAO BlackList Car Success");
        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Admin DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(connection);
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                loggerInstance.log(2,"Admin DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getEmail(int carId) {
        String query = "select (select email from User where user_id = owner_id) from Car where car_id =?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String email="";
        try{
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,carId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    email = resultSet.getString(1);
                }
            }

        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Admin DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Admin DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Admin DAO Email Success");
        return email;
    }
}
