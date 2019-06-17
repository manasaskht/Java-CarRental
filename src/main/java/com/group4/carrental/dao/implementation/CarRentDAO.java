package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ICarRentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("CarRentDao")
public class CarRentDAO implements ICarRentDAO {

    private IDatabaseConnection databaseConnection;

    @Autowired
    public CarRentDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }



}
