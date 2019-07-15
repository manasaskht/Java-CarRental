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

    private static final String GET_BOOKEDCAR_QUERY = "select Car.*,Car_Type.car_type_name,City_Name.city_name from Car INNER JOIN User ON Car.owner_id=User.user_id" +
            " INNER JOIN City_Name ON Car.car_city = City_Name.city_id"+
            " INNER JOIN Car_Type ON Car.car_type_id = Car_Type.car_type_id where User.user_id = ? AND car_status_id = 1 ";

    private static final String REMOVE_BOOKEDCAR_QUERY = "update Car SET car_status_id = 2 where car_id = ?";

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
        PreparedStatement preparedStatement=null;
        ArrayList<CarList> carArrayList =  new ArrayList<>();

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(GET_BOOKEDCAR_QUERY);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();



            while(resultSet.next()){

                CarList car = new CarList();
                car.setCarId(resultSet.getInt("car_id"));
                car.setCarOwner(resultSet.getInt("owner_id"));
                car.setCarRate(resultSet.getDouble("car_rate"));
                car.setCarTypeName(resultSet.getString("car_type_name"));
                car.setCityName(resultSet.getString("city_name"));
                car.setDescription(resultSet.getString("car_description"));
                car.setCarModel(resultSet.getString("car_model"));

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
                if(preparedStatement!=null)
                {
                    preparedStatement.close();
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
        PreparedStatement preparedStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(REMOVE_BOOKEDCAR_QUERY);
            preparedStatement.setInt(1, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"User Remove Bokked Car DAO Error: "+e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
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
