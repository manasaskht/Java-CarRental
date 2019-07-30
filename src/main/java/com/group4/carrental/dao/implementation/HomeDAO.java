package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IHomeDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarBooking;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("HomeDAO")
public class HomeDAO implements IHomeDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;
    private final String GET_AVAILABLE_CAR="{call getAvailableCar(?,?,?)}";
    private final String GET_CURRENT_BOOKING="{call getCurrentBooking(?,?)}";

    public HomeDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance){
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public ArrayList<Car> getCarForRegion(int carType, int cityId, int userId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Car> carArrayList = new ArrayList<>();
        try{
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(GET_AVAILABLE_CAR);
            callableStatement.setInt(1,carType);
            callableStatement.setInt(2,cityId);
            callableStatement.setInt(3,userId);

            resultSet = callableStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    Car car = new Car();
                    int carId = resultSet.getInt("car_id");
                    car.setCarId(carId);
                    int carOwnerId = resultSet.getInt("owner_id");
                    car.setCarOwner(carOwnerId);
                    int carCity = resultSet.getInt("car_city");
                    car.setCity(carCity);
                    String carDescription = resultSet.getString("car_description");
                    car.setDescription(carDescription);
                    int carTypeId = resultSet.getInt("car_type_id");
                    car.setCarTypeId(carTypeId);
                    double carRate = resultSet.getDouble("car_rate");
                    car.setCarRate(carRate);
                    String carModel = resultSet.getString("car_model");
                    car.setModel(carModel);
                    Blob carImage = resultSet.getBlob("car_image");
                    String carImageData = null;
                    byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                    carImageData = Base64.encodeBase64String(imageBytes);
                    car.setImageURL(carImageData);

                    carArrayList.add(car);
                }
            }


        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Home DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeStatementAndConnection(callableStatement,resultSet);
            } catch (SQLException e) {
                loggerInstance.log(2,"Home DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Home DAO Get Car For Region: Called");
        return carArrayList;
    }

    @Override
    public ArrayList<CarBooking> getBookings(String from, String to) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<CarBooking> carBookingArrayList = new ArrayList<CarBooking>();
        try{
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(GET_CURRENT_BOOKING);
            callableStatement.setString(1,from);
            callableStatement.setString(2,to);

            resultSet = callableStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    CarBooking carBooking = new CarBooking();
                    int customerId = resultSet.getInt("customer_id");
                    carBooking.setUserId(customerId);
                    int carId = resultSet.getInt("car_id");
                    carBooking.setCarId(carId);
                    String fromDate = resultSet.getString("from_date");
                    carBooking.setFromDate(fromDate);
                    String toDate = resultSet.getString("to_date");
                    carBooking.setToDate(toDate);

                    carBookingArrayList.add(carBooking);
                }
            }

        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Home DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeStatementAndConnection(callableStatement,resultSet);
            } catch (SQLException e) {
                loggerInstance.log(2,"Home DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Home DAO Get Booking: Called");
        return carBookingArrayList;
    }
}
