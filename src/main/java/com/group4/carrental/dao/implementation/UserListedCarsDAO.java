package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IUserListedCarsDAO;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

@Repository("UserListedCarsDAO")
public class UserListedCarsDAO implements IUserListedCarsDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    private final String GET_LISTED_CAR = "{call getListedCar(?)}";

    private static final String REMOVE_CAR_QUERY = "{call removeCarById(?)}";
    ArrayList<CarList> carArrayList ;


    private static final String GET_BOOKED_CAR = "{call getBookedCarDetails(?)}";




    @Autowired
    public UserListedCarsDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection,
                             LoggerInstance loggerInstance) {
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }


    @Override
    public ArrayList<CarList> getListedCars(int userId) {

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement=null;
        carArrayList = new ArrayList<>();

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(GET_LISTED_CAR);
            callableStatement.setInt(1,userId);
            resultSet = callableStatement.executeQuery();


            while(resultSet.next()){

                System.out.println("in to listed car ");
                CarList car = new CarList();
                car.setCarId(resultSet.getInt("car_id"));
                car.setCarOwner(resultSet.getInt("owner_id"));
                car.setCarRate(resultSet.getDouble("car_rate"));
                car.setCarTypeName(resultSet.getString("car_type_name"));
                car.setCityName(resultSet.getString("city_name"));
                car.setDescription(resultSet.getString("car_description"));
                car.setCarModel(resultSet.getString("car_model"));
                car.setCityId(resultSet.getInt("car_city"));
                car.setCarTypeId(resultSet.getInt("car_type_id"));

                Blob carImage = resultSet.getBlob("car_image");
                String carImageData = null;
                byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                carImageData = Base64.encodeBase64String(imageBytes);
                car.setImageURL(carImageData);



                carArrayList.add(car);

            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"User Get All Listed Car DAO Error: "+e.toString());
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(callableStatement!=null)
                {
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e){
                loggerInstance.log(2,"User Get All Listed Car DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"User Get All Listed Car DAO: Success");

        return carArrayList;
    }

    @Override
    public void removeCarById(int carId) {

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement=null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(REMOVE_CAR_QUERY);
            callableStatement.setInt(1,carId);
            callableStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Remove Car By ID DAO Error: "+e.toString());
            e.printStackTrace();
        } finally {
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
                loggerInstance.log(2,"Remove Car By ID DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Remove Car By ID DAO: Success");
    }

    @Override
    public CarList getCarDetailsById(int carId) {

        CarList carList= new CarList();
        for(int i=0; i <carArrayList.size(); i++){
            if(carArrayList.get(i).getCarId() == carId){
                carList = carArrayList.get(i);
            }
        }
        loggerInstance.log(0,"Get Car Details By Id DAO: Success");
        return carList;

    }

    @Override
    public boolean isCarBooked(int carId) {

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement=null;
        boolean isBooked = false;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(GET_BOOKED_CAR);
            callableStatement.setInt(1,carId);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                String dateString = resultSet.getString("from_date");
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");


                Date fromDate = format.parse(dateString);
                Date currentDate = new Date();
                System.out.println("from date - "+format.format(fromDate));
                System.out.println("from date - "+format.format(currentDate));
                if(fromDate.after(currentDate)){
                    isBooked = true;
                    System.out.println("is Car booked -"+isBooked);
                }


            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException | ParseException e) {
            loggerInstance.log(2,"IS BOOKED CAR DAO Error: "+e.toString());
            e.printStackTrace();
        } finally {
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
                loggerInstance.log(2,"IS BOOKED CAR DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"IS BOOKED CAR DAO SUCCESS: ");
        return isBooked;
    }


}
