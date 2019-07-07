package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IAdminDAO;
import com.group4.carrental.model.Car;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("AdminDao")
public class AdminDAO implements IAdminDAO {

    private IDatabaseConnection databaseConnection;

    @Autowired
    public AdminDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<Car> getAllCars() {
        String query = "select * from Car where car_status_id = 1";
        ArrayList<Car> carArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Car car = new Car();
                    int id = resultSet.getInt("car_id");
                    car.setCarId(id);
                    int ownerId = resultSet.getInt("owner_id");
                    car.setCarOwner(ownerId);
                    int city = resultSet.getInt("car_city");
                    car.setCity(city);
                    String description = resultSet.getString("car_description");
                    car.setDescription(description);
                    Double carRate = resultSet.getDouble("car_rate");
                    car.setCarRate(carRate);
                    String model = resultSet.getString("car_model");
                    car.setModel(model);
                    Blob carImage = resultSet.getBlob("car_image");
                    String carImageData = null;
                    byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                    carImageData = Base64.encodeBase64String(imageBytes);
                    car.setImageURL(carImageData);
                    carArrayList.add(car);
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
        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(connection);
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
