package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ICarRentDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("CarRentDao")
public class CarRentDAO implements ICarRentDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    private final String GET_CAR_TYPE="{call getCarType()}";
    private final String ADD_CAR="{call addCar(?,?,?,?,?,?,?)}";
    private final String GET_CAR_BY_ID="{call getCarById(?)}";

    @Autowired
    public CarRentDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public void addCar(Car car, Blob carImage, int userId) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = this.databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(ADD_CAR);
            callableStatement.setInt(1,userId);
            callableStatement.setInt(2,car.getCity());
            callableStatement.setString(3, car.getDescription());
            callableStatement.setInt(4, car.getCarTypeId());
            callableStatement.setDouble(5, car.getCarRate());
            callableStatement.setBinaryStream(6, carImage.getBinaryStream());
            callableStatement.setString(7, car.getModel());

            callableStatement.execute();
            loggerInstance.log(0,"Car Rent DAO Add Car: Called");

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeStatementAndConnection(callableStatement,null);
            } catch (SQLException e) {
                loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public Car getCarById(int id) {
        Car car = new Car();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(GET_CAR_BY_ID);
            callableStatement.setInt(1,id);
            resultSet = callableStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    int carId = resultSet.getInt("car_id");
                    car.setCarId(carId);
                    int ownerId = resultSet.getInt("owner_id");
                    car.setCarOwner(ownerId);
                    int city = resultSet.getInt("car_city");
                    car.setCity(city);
                    String description = resultSet.getString("car_description");
                    car.setDescription(description);
                    double carRate = resultSet.getDouble("car_rate");
                    car.setCarRate(carRate);
                    Blob carImage = resultSet.getBlob("car_image");
                    String carImageData = null;
                    byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                    carImageData = Base64.encodeBase64String(imageBytes);
                    car.setImageURL(carImageData);
                    String carModel = resultSet.getString("car_model");
                    car.setModel(carModel);
                    int carTypeId = resultSet.getInt("car_type_id");
                    car.setCarTypeId(carTypeId);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeStatementAndConnection(callableStatement,resultSet);
            } catch (SQLException e) {
                loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Car Rent DAO Get Car: Called");
        return car;
    }

    @Override
    public ArrayList<CarType> getCarType() {
        ArrayList<CarType> carTypes = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(GET_CAR_TYPE);
            resultSet = callableStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    int carTypeId = resultSet.getInt("car_type_id");
                    String carTypeName = resultSet.getString("car_type_name");

                    CarType carType = new CarType();
                    carType.setCarTypeId(carTypeId);
                    carType.setCarTypeName(carTypeName);

                    carTypes.add(carType);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeStatementAndConnection(callableStatement,resultSet);
            } catch (SQLException e) {
                loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Car Rent DAO Get Car Type: Called");
        return carTypes;
    }

}
