package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IAdminListCarDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("AdminDao")
public class AdminListCarDAO implements IAdminListCarDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    private final String LIST_ALL_CAR = "{call listAllCarsAdmin()}";
    private final String BLACKLIST_CAR = "{call blackListCar(?)}";
    private final String GET_OWNER_EMAIL = "{call getOwnerEmail(?)}";

    @Autowired
    public AdminListCarDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public ArrayList<AdminCar> getAllCars() {
        ArrayList<AdminCar> carArrayList = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(LIST_ALL_CAR);
            resultSet = callableStatement.executeQuery();

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
                databaseConnection.closeStatementAndConnection(callableStatement,resultSet);
            } catch (SQLException e) {
                loggerInstance.log(2,"Admin DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Admin List Car DAO fetch Cars Success");
        return carArrayList;
    }

    @Override
    public void blackListCar(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(BLACKLIST_CAR);
            callableStatement.setInt(1,id);
            callableStatement.execute();

            loggerInstance.log(0,"Admin DAO BlackList Car Success");

        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Admin DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeStatementAndConnection(callableStatement,null);
            } catch (SQLException e) {
                loggerInstance.log(2,"Admin DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getEmail(int carId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String email="";

        try{
            connection = databaseConnection.getDBConnection();
            callableStatement =connection.prepareCall(GET_OWNER_EMAIL);
            callableStatement.setInt(1,carId);
            resultSet = callableStatement.executeQuery();

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
                databaseConnection.closeStatementAndConnection(callableStatement,resultSet);
            } catch (SQLException e) {
                loggerInstance.log(2,"Admin DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Admin DAO Email Success");
        return email;
    }

}
