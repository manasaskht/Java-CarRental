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

    private static final String GET_LISTEDCAR_QUERY = "select Car.*,Car_Type.*,City_Name.* from Car INNER JOIN City_Name ON Car.car_city = City_Name.city_id"+
            " INNER JOIN Car_Type ON Car.car_type_id = Car_Type.car_type_id where Car.owner_id = ? AND Car.car_status_id = 2 ";

    private static final String REMOVE_CAR_QUERY = "DELETE from Car where car_id = ?";
    ArrayList<CarList> carArrayList ;


    private static final String GET_BOOKED_CAR = "select * from Booking where car_id = ?";




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
        PreparedStatement preparedStatement=null;
        carArrayList = new ArrayList<>();

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(GET_LISTEDCAR_QUERY);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Resultset ");


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
                if(preparedStatement!=null)
                {
                    preparedStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e){
                loggerInstance.log(2,"User Get All Listed Car DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"User Get All Listed Car DAO: Success");

        System.out.println("listed car - array "+carArrayList.size());
        return carArrayList;
    }

    @Override
    public void removeCarById(int carId) {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement=null;

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(REMOVE_CAR_QUERY);
            preparedStatement.setInt(1,carId);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Remove Car By ID DAO Error: "+e.toString());
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement!=null)
                {
                    preparedStatement.close();
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
        PreparedStatement preparedStatement=null;
        boolean isBooked = false;

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(GET_BOOKED_CAR);
            preparedStatement.setInt(1,carId);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String dateString = resultSet.getString("from_date");
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                Date fromDate = format.parse(dateString);
                Date currentDate = new Date();
                if(fromDate.after(currentDate)){
                    isBooked = true;
                }


            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException | ParseException e) {

            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement!=null)
                {
                    preparedStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        System.out.println("is Car Booked -" + isBooked);

        return isBooked;
    }


}
