package com.group4.carrental.service;

import com.group4.carrental.dao.IUserListedCarsDAO;
import com.group4.carrental.dao.UserListedCarDAOMock;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.implementation.CarRentService;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.UserListedCarService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;

public class UserListedCarServiceTest {

    IUserListedCarsDAO userListedCarsDAO;
    IUserListedCarService userListedCarService;

    @Before
    public void setUp(){
        this.userListedCarsDAO = new UserListedCarDAOMock();
        this.userListedCarService = new UserListedCarService(userListedCarsDAO,mock(CarRentService.class),
                mock(LoggerInstance.class));
    }


    @Test
    public void getListedCarTest(){
        int userId = 10;
        ArrayList<CarList> listOfCar = userListedCarsDAO.getListedCars(userId);
        boolean answer = listOfCar.size() == 2;
        assertTrue(answer);

        userId = 11;
        listOfCar = userListedCarsDAO.getListedCars(userId);
        answer = listOfCar.size() == 2;
        assertFalse(answer);
    }

    @Test
    public void removeCarTest(){
        int carId = 2;
        userListedCarsDAO.removeCarById(carId);
        CarList car = userListedCarsDAO.getCarDetailsById(carId);
        boolean answer = car.getCarId() != carId;
        assertTrue(answer);

    }

    @Test
    public void getCarDetailsTest(){
        int carId = 1;
        CarList car = userListedCarsDAO.getCarDetailsById(carId);
        boolean answer = car.getCarId() == carId;
        assertTrue(answer);

        carId = 5;
        car = userListedCarsDAO.getCarDetailsById(carId);
        answer = car.getCarId() == carId;
        assertFalse(answer);
    }
}
