package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUserBookedCarsDAO;
import com.group4.carrental.dao.IUserListedCarsDAO;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.IUserBookedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("UserBookedCarService")
public class UserBookedCarService implements IUserBookedCarService {

    private IUserBookedCarsDAO userBookedCarsDAO;

    @Autowired
    public UserBookedCarService(@Qualifier("UserBookedCarsDAO") IUserBookedCarsDAO userBookedCarsDAO ){
        this.userBookedCarsDAO = userBookedCarsDAO;
    }


    @Override
    public ArrayList<CarList> getUserBookedCars(int userID) {
        return userBookedCarsDAO.getBookedCars(userID);
    }

    @Override
    public void removeBookedCar(int carId) {
        userBookedCarsDAO.removeBookedCar(carId);
    }




}
