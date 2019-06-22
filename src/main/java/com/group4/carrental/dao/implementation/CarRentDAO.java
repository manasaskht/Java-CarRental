package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ICarRentDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;

@Repository("CarRentDao")
public class CarRentDAO implements ICarRentDAO {

    private IDatabaseConnection databaseConnection;

    @Autowired
    public CarRentDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }





    @Override
    public void addCar(Car car, Blob carImage) {
        String query = "insert into Car(owner_id, city, description, car_type_id, rate, image, status_id)\n" +
                "values (?,?,?,?,?,?,?);";
        try {
            Connection connection = this.databaseConnection.getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "City");
            preparedStatement.setString(3, car.getDescription());
            preparedStatement.setInt(4, car.getCarTypeId());
            preparedStatement.setString(5, String.valueOf(car.getCarRate()));
            preparedStatement.setBinaryStream(6, carImage.getBinaryStream());
            preparedStatement.setString(7, "12");

            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
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
                    byte[] image = Base64.encode(carImage.getBytes(1, (int) carImage.length()));
                    String carImageData = null;
                    try {
                        carImageData = new String(image, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    car.setImageURL(carImageData);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
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
            e.printStackTrace();
        }


        return carTypes;
    }

}
