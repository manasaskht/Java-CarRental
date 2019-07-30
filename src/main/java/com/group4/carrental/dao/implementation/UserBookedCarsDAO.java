package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IUserBookedCarsDAO;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("UserBookedCarsDAO")
public class UserBookedCarsDAO implements IUserBookedCarsDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    private static final String GET_BOOKEDCAR_QUERY = "{call getBookedCars(?)}";



    private static final String REMOVE_BOOKEDCAR_QUERY = "{call removeBookedCarById(?)}";

    @Autowired
    public UserBookedCarsDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection,
                             LoggerInstance loggerInstance) {
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }
    @Override
    public ArrayList<CarList> getBookedCars(int userId) {
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement=null;
        ArrayList<CarList> carArrayList =  new ArrayList<>();

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(GET_BOOKEDCAR_QUERY);
            callableStatement.setInt(1,userId);
            resultSet = callableStatement.executeQuery();



            while(resultSet.next()){

                CarList car = new CarList();
                car.setCarId(resultSet.getInt("car_id"));
                car.setCarOwner(resultSet.getInt("owner_id"));
                car.setCarRate(resultSet.getDouble("car_rate"));
                car.setCarTypeName(resultSet.getString("car_type_name"));
                car.setCityName(resultSet.getString("city_name"));
                car.setDescription(resultSet.getString("car_description"));
                car.setCarModel(resultSet.getString("car_model"));
                car.setBookingId(resultSet.getInt("booking_id"));
                car.setFromDate(resultSet.getString("from_date"));
                car.setToDate(resultSet.getString("to_date"));

                System.out.println("Booked date - "+car.getBookedDate());

                Blob carImage = resultSet.getBlob("car_image");
                String carImageData = null;
                byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                carImageData = Base64.encodeBase64String(imageBytes);
                car.setImageURL(carImageData);

                carArrayList.add(car);

            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Get Booked Car DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Get Booked Car DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"Get Booked Car DAO: Success");
        return carArrayList;
    }

    @Override
    public void removeBookedCar(int carId) {

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(REMOVE_BOOKEDCAR_QUERY);
            callableStatement.setInt(1, carId);
            callableStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"User Remove Bokked Car DAO Error: "+e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (callableStatement != null) {
                    callableStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                loggerInstance.log(2,"User Remove Bokked Car DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"User Remove Bokked Car DAO: Success");
    }

}
