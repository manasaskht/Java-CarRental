package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ICarDetailsDAO;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("CarDetailsDAO")
public class CarDetailsDAO implements ICarDetailsDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    private  final String GET_CAR_DETAILS = "{call getCarDetailById(?)}";

    @Autowired
    public CarDetailsDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
       this.loggerInstance = loggerInstance;
       this.databaseConnection = databaseConnection;
    }

    @Override
    public CarList getCarDetailsById(int carId) {

        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;
        CarList car = new CarList();

        try {
            connection = databaseConnection.getDBConnection();


            callableStatement = connection.prepareCall(GET_CAR_DETAILS);
            callableStatement.setInt(1,carId);
            resultSet = callableStatement.executeQuery();

            if(resultSet.next()){
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
            }


        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car DETAILS BY ID DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Car DETAILS BY ID DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"Car DETAILS BY ID SUCCESS: ");
        return car;
    }


}
