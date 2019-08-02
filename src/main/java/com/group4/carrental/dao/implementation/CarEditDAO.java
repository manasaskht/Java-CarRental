package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ICarEditDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("CarEditDAO")
public class CarEditDAO implements ICarEditDAO {

    private static final String UPDATE_CAR_QUERY = "{call updateCarQuery(?,?,?,?,?,?)}";

    private static final String UPDATE_CAR_IMAGE = "{call updateCarImage(?,?)}";

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
        CallableStatement callableStatement = null;

        try {
            connection = databaseConnection.getDBConnection();


                callableStatement = connection.prepareCall(UPDATE_CAR_QUERY);
                System.out.println("car description - "+car.getDescription());
                callableStatement.setString(1,car.getDescription());
                callableStatement.setInt(2,car.getCity());
                callableStatement.setInt(3,car.getCarTypeId());
                callableStatement.setDouble(4,car.getCarRate());
                callableStatement.setString(5,car.getModel());
                callableStatement.setInt(6,car.getCarId());
                callableStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Update DAO Error: "+e.toString());
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
        CallableStatement callableStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(UPDATE_CAR_IMAGE);
            callableStatement.setBinaryStream(1,carImage.getBinaryStream());
            callableStatement.setInt(2,carID);
            callableStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Car Image Update DAO Error: "+e.toString());
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
                loggerInstance.log(2,"Car Image Update DAO Error: "+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Car Image Update DAO: Success");
    }
}
