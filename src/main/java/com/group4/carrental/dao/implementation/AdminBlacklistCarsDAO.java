package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IAdminBlacklistCarsDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.City;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("AdminBlacklistCarsDAO")
public class AdminBlacklistCarsDAO implements IAdminBlacklistCarsDAO {

    private IDatabaseConnection databaseConnection;
    @Autowired
    private LoggerInstance log;

    @Autowired
    public AdminBlacklistCarsDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private final String blackList_cars="{call getBlacklistCars(?)}";
    private final String update_carStatus="{call updateCarStatus(?,?)}";

    @Override
    public ArrayList<AdminCar> getBlacklistCars() {

        log.log(0,"In DAO:get blacklist cars list from database");
        ArrayList<AdminCar> carArrayList = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        int status=3;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(blackList_cars);
            callableStatement.setInt(1,status);
            rs = callableStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    AdminCar car = new AdminCar();
                    int id = rs.getInt("car_id");
                    String ownerName = rs.getString("owner_name");
                    String ownerEmail = rs.getString("owner_email");
                    String carCity = rs.getString("car_city");
                    String description = rs.getString("car_description");
                    double carRate = rs.getDouble("car_rate");
                    String model = rs.getString("car_model");
                    Blob carImage = rs.getBlob("car_image");
                    String carImageData = null;
                    byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                    carImageData = Base64.encodeBase64String(imageBytes);
                    car.setCarModel(model);
                    car.setCarRate(carRate);
                    car.setCarImage(carImageData);
                    car.setCarDescription(description);
                    car.setCarCity(carCity);
                    car.setCarOwnerMail(ownerEmail);
                    car.setCarOwnerName(ownerName);
                    car.setCarId(id);
                    carArrayList.add(car);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.log(2,"exception in getBlacklistCars method"+e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(connection);
                if(callableStatement != null) {
                    callableStatement.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return carArrayList;
    }

    @Override
    public void updateCarStatus(int carId,int carStatus) {

        log.log(0,"updateCarStatus method to update status of car in database");
        Connection connection=null;
        CallableStatement callableStatement = null;
        try {

            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(update_carStatus);
            callableStatement.setInt(1,carId);
            callableStatement.setInt(2,carStatus);

            callableStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.log(2,"exception in updateCarStatus method"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(callableStatement != null){
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                log.log(2,"exception in updateCarStatus method"+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
