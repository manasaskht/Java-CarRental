package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ICarEditDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.*;

@Repository("CarEditDAO")
public class CarEditDAO implements ICarEditDAO {

    private static final String UPDATE_CAR_QUERY = "update Car SET car_description = ?, car_city = ?, " +
            "car_type_id = ?, car_rate= ?, car_model=? where car_id= ?";

    private static final String UPDATE_CAR_IMAGE = "update Car SET car_image = ? where car_id = ?";

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;


    @Autowired
    public CarEditDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection,LoggerInstance loggerInstance) {
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }
    @Override
    public void updateCar(Car car) {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = databaseConnection.getDBConnection();


                preparedStatement = connection.prepareStatement(UPDATE_CAR_QUERY);
                System.out.println("car description - "+car.getDescription());
                preparedStatement.setString(1,car.getDescription());
                preparedStatement.setInt(2,car.getCity());
                preparedStatement.setInt(3,car.getCarTypeId());
                preparedStatement.setDouble(4,car.getCarRate());
                preparedStatement.setString(5,car.getModel());
                System.out.println("car id - "+car.getCarId());
                preparedStatement.setInt(6,car.getCarId());
                preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Update DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Car Update DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }


        loggerInstance.log(0,"Car Update DAO: Success");

    }

    @Override
    public void updateCarImage(int carID, Blob carImage) {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CAR_IMAGE);
            preparedStatement.setBinaryStream(1,carImage.getBinaryStream());
            preparedStatement.setInt(2,carID);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Image Update DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Car Image Update DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Car Image Update DAO: Success");
    }
}
