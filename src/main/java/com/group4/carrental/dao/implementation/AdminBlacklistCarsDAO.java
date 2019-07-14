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

    @Override
    public ArrayList<AdminCar> getBlacklistCars() {

        log.log(0,"In DAO:get blacklist cars list from database");
        ArrayList<AdminCar> carArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int status=3;
        String getCarListQuery = "select car_id,car_model,car_description,car_rate,(select city_name from City_Name where city_id = car_city) as car_city,(select name from User where user_id = owner_id) as owner_name, (select email from User where user_id = owner_id) as owner_email, car_image from Car where car_status_id=?";

        try {
            connection = databaseConnection.getDBConnection();
            ps = connection.prepareStatement(getCarListQuery);
            ps.setInt(1,status);
            rs = ps.executeQuery();

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
                if(ps != null) {
                    ps.close();
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
        PreparedStatement updateCarStatement=null;
        try {

            connection = databaseConnection.getDBConnection();
            String updateQuery = "UPDATE Car SET car_status_id=? WHERE car_id=?";
            updateCarStatement = connection.prepareStatement(updateQuery);
            updateCarStatement.setInt(1,carStatus);
            updateCarStatement.setInt(2,carId);
            updateCarStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.log(2,"exception in updateCarStatus method"+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                if(updateCarStatement != null){
                    updateCarStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                log.log(2,"exception in updateCarStatus method"+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
