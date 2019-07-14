package com.group4.carrental.service;

import com.group4.carrental.dao.CarRentDAOMock;
import com.group4.carrental.dao.ICarRentDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.implementation.CarRentService;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.UserSignUpService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class CarRentServiceTest {

    private ICarRentService carRentService;
    private ICarRentDAO carRentDAO;

    @Before
    public void setUp(){
        this.carRentDAO = new CarRentDAOMock();
        this.carRentService = new CarRentService(carRentDAO,mock(UserSignUpService.class),mock(LoggerInstance.class));
    }

    @Test
    public void validCarModelTest(){
        String model = "Mode";
        boolean answer = this.carRentService.validCarModel(model);
        assertFalse(answer);

        model = "Model";
        answer = this.carRentService.validCarModel(model);
        assertTrue(answer);
    }

    @Test
    public void validCarPriceTest(){
        double price = 13;
        boolean answer = this.carRentService.validCarPrice(price);
        assertTrue(answer);

        price = -1;
        answer = this.carRentService.validCarPrice(price);
        assertFalse(answer);
    }

    @Test
    public void validCarDescriptionTest(){
        String description = "Model Description 1";
        boolean answer = this.carRentService.validCarDescription(description);
        assertTrue(answer);

        description = "Model";
        answer = this.carRentService.validCarDescription(description);
        assertFalse(answer);
    }

    @Test
    public void validCarTypeTest(){
        int carTypeId = 1;
        boolean answer = this.carRentService.validCarType(carTypeId);
        assertTrue(answer);

        carTypeId = 4;
        answer = this.carRentService.validCarType(carTypeId);
        assertFalse(answer);
    }

    @Test
    public void addCarTest(){
        Car car = new Car();
        car.setImageURL("Abcd");
        car.setDescription("Description");
        car.setCarOwner(1);
        car.setCity(1);
        car.setCarId(4);
        car.setCarRate(12);
        car.setCarTypeId(1);
        car.setModel("Abcd");

        MockMultipartFile mockMultipartFile = new MockMultipartFile("abcd","abcd",null,"abcd".getBytes());
        this.carRentService.addCar(car, mockMultipartFile, 1);
        Car car1 = this.carRentService.getCarById(4);
        assertEquals(car1.getCarId(),car.getCarId());
    }

}
