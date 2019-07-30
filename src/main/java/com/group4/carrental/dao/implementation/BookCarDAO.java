package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IBookCarDAO;
import com.group4.carrental.model.CarBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("BookCarDAO")
public class BookCarDAO implements IBookCarDAO {
    private IDatabaseConnection databaseConnection;

    @Autowired
    public BookCarDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection)
    {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void saveCarBookingDetails(CarBooking carBooking) {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement saveCarDetailsStmt=null;

        try {
            connection = databaseConnection.getDBConnection();

            String query = "insert into Booking (customer_id, car_id, from_date, to_date)"
                    + " values (?, ?, ?, ?)";


            saveCarDetailsStmt = connection.prepareStatement(query);
            saveCarDetailsStmt.setInt(1, carBooking.getUserId());
            saveCarDetailsStmt.setInt(2, carBooking.getCarId());
            saveCarDetailsStmt.setString(3, carBooking.getFromDate());
            saveCarDetailsStmt.setString(4, carBooking.getToDate());


            saveCarDetailsStmt.execute();

        }
        catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {

            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(saveCarDetailsStmt!=null)
                {
                    saveCarDetailsStmt.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

    }
}
