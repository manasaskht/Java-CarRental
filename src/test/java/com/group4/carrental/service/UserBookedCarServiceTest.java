package com.group4.carrental.service;

import com.group4.carrental.dao.IUserBookedCarsDAO;
import com.group4.carrental.dao.IUserListedCarsDAO;
import com.group4.carrental.dao.UserBookedCarDAOMock;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.UserBookedCarService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class UserBookedCarServiceTest {

    IUserBookedCarsDAO userBookedCarsDAO;
    IUserBookedCarService userBookedCarService;

    @Before
    public void setUp(){
        this.userBookedCarsDAO = new UserBookedCarDAOMock();
        this.userBookedCarService = new UserBookedCarService(userBookedCarsDAO,mock(LoggerInstance.class));
    }

    @Test
    public void getBookedCarTest(){
        int userId = 10;
        ArrayList<CarList> listOfCar = userBookedCarsDAO.getBookedCars(userId);
        boolean answer = listOfCar.size() == 2;
        assertTrue(answer);

        userId = 11;
        listOfCar = userBookedCarsDAO.getBookedCars(userId);
        answer = listOfCar.size() == 2;
        assertFalse(answer);
    }

}
