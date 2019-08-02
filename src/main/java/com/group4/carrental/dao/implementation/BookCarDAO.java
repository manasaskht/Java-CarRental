package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IBookCarDAO;
import com.group4.carrental.model.CarBooking;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("BookCarDAO")
public class BookCarDAO implements IBookCarDAO {
    private IDatabaseConnection databaseConnection;
    private final String save_CarBooking_Details="{call saveCarBookingDetails(?,?,?,?)}";

    @Autowired
    private LoggerInstance log;
    @Autowired
    public BookCarDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection)
    {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void saveCarBookingDetails(CarBooking carBooking) {

        log.log(0,"In BookCarDAO:saveCarBookingDetails");
        Connection connection = null;
        ResultSet resultSet = null;
        CallableStatement callableStatement = null;

        try {
            connection = databaseConnection.getDBConnection();
            callableStatement = connection.prepareCall(save_CarBooking_Details);
            callableStatement.setInt(1, carBooking.getUserId());
            callableStatement.setInt(2, carBooking.getCarId());
            callableStatement.setString(3, carBooking.getFromDate());
            callableStatement.setString(4, carBooking.getToDate());


            callableStatement.execute();

        }
        catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {

            e.printStackTrace();
        }
        finally {
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
                log.log(2,"In BookCarDAO: saveCarBookingDetails"+e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
