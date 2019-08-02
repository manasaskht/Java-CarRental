package com.group4.carrental.service;

import com.group4.carrental.dao.CarDetailsDAOMock;
import com.group4.carrental.dao.ICarDetailsDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.implementation.CarDetailsService;
import com.group4.carrental.service.implementation.CarEditService;
import com.group4.carrental.service.implementation.LoggerInstance;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class CarDetailsServiceTest {

    private ICarDetailsDAO carDetailsDAO;
    private ICarDetailsService carDetailsService;
    @Before
    public  void setUp(){
        this.carDetailsDAO = new CarDetailsDAOMock();
        this.carDetailsService = new CarDetailsService(carDetailsDAO,mock(LoggerInstance.class));
    }

    @Test
    public void getCarDetails(){
        CarList car = carDetailsService.getCarDetailsById(1);
        boolean answer = car.getCarId() == 1;
        assertTrue(answer);

        answer = car.getCarRate() == 10;
        assertFalse(answer);
    }
}
