package com.group4.carrental.service;

import com.group4.carrental.dao.HomeDAOMock;
import com.group4.carrental.dao.IHomeDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.implementation.CarRentService;
import com.group4.carrental.service.implementation.HomeService;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.UserSignUpService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class HomeServiceTest {

    private IHomeService homeService;
    private IHomeDAO homeDAO;

    @Before
    public void setUp(){
        this.homeDAO = new HomeDAOMock();
        this.homeService = new HomeService(homeDAO,mock(CarRentService.class),mock(UserSignUpService.class),mock(LoggerInstance.class));
    }

    @Test
    public void getCarForRegionTest(){
        int carType = 1;
        int carCity = 1;
        int userId = 1;
        boolean carToTest = false;
        ArrayList<Car> carArrayList = this.homeDAO.getCarForRegion(carType,carCity,userId);
        for(int i=0;i<carArrayList.size();i++){
            Car car = carArrayList.get(i);
            if(car.getCarId() == 2){
                carToTest = true;
            }
        }
        assertTrue(carToTest);
    }

    @Test
    public void validDate(){
        String date = "201-05-04";
        boolean isValid = true;
        isValid = this.homeService.validDate(date);
        assertFalse(isValid);
    }

}
