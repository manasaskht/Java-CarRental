package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUserBookedCarsDAO;
import com.group4.carrental.dao.IUserListedCarsDAO;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.IUserBookedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service("UserBookedCarService")
public class UserBookedCarService implements IUserBookedCarService {

    private IUserBookedCarsDAO userBookedCarsDAO;
    private LoggerInstance loggerInstance;
    @Autowired
    public UserBookedCarService(@Qualifier("UserBookedCarsDAO") IUserBookedCarsDAO userBookedCarsDAO,
                                LoggerInstance  loggerInstance){
        this.userBookedCarsDAO = userBookedCarsDAO;
        this.loggerInstance = loggerInstance;
    }


    @Override
    public ArrayList<CarList> getUserBookedCars(int userID) {
        loggerInstance.log(0,"User Service Get All Booked Cars: Called");
        return userBookedCarsDAO.getBookedCars(userID);
    }

    @Override
    public void removeBookedCar(int carId) {
        loggerInstance.log(0,"User Service Remove Car From Booked Car: Called");
        userBookedCarsDAO.removeBookedCar(carId);
    }




}
