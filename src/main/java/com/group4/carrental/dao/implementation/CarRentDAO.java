package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ICarRentDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("CarRentDao")
public class CarRentDAO implements ICarRentDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    @Autowired
    public CarRentDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public void addCar(Car car, Blob carImage, int userId) {
        String query = "insert into Car(owner_id, car_city, car_description, car_type_id, car_rate, car_image,car_model)\n" +
                "values (?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, car.getCity());
            preparedStatement.setString(3, car.getDescription());
            preparedStatement.setInt(4, car.getCarTypeId());
            preparedStatement.setDouble(5, car.getCarRate());
            preparedStatement.setBinaryStream(6, carImage.getBinaryStream());
            preparedStatement.setString(7, car.getModel());

            preparedStatement.execute();
            loggerInstance.log(0,"Car Rent DAO Add Car: Called");
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(connection);
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public Car getCarById(int id) {
        String query = "select * from Car where car_id = ?";
        Car car = new Car();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    int ownerId = resultSet.getInt("owner_id");
                    car.setCarOwner(ownerId);
                    int city = resultSet.getInt("city");
                    car.setCity(city);
                    String description = resultSet.getString("description");
                    car.setDescription(description);
                    Blob carImage = resultSet.getBlob("image");
                    String carImageData = null;
                    carImageData = new String(carImage.getBytes(1l, (int) carImage.length()));
                    car.setImageURL(carImageData);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Car Rent DAO Get Car: Called");
        return car;
    }

    @Override
    public ArrayList<CarType> getCarType() {
        String query = "select * from Car_Type";
        ArrayList<CarType> carTypes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

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
                databaseConnection.closeDBConnection(connection);
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                loggerInstance.log(2,"Car Rent DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Car Rent DAO Get Car Type: Called");
        return carTypes;
    }

}
