package com.group4.carrental.service;

import com.group4.carrental.dao.AdminListCarDAOMock;
import com.group4.carrental.dao.IAdminListCarDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.service.implementation.AdminListCarService;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.SendMailService;
import com.group4.carrental.service.implementation.UserListedCarService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AdminListCarServiceTest {

    private IAdminListCarService adminListCarService;
    private IAdminListCarDAO adminListCarDAO;

    @Before
    public void setUp(){
        this.adminListCarDAO = new AdminListCarDAOMock();
        this.adminListCarService = new AdminListCarService(adminListCarDAO,mock(SendMailService.class),mock(LoggerInstance.class),mock(UserListedCarService.class));
    }

    @Test
    public void getAllCarsTest(){
        ArrayList<AdminCar> adminCars = this.adminListCarService.getAllCars();
        int carId = 1;
        String modelToTest = "Model 1";
        String modelGot = "";
        for(int i=0;i<adminCars.size();i++){
            AdminCar adminCar = adminCars.get(i);
            if(adminCar.getCarId() == carId){
                modelGot = adminCar.getCarModel();
            }
        }
        assertEquals(modelGot,modelToTest);
    }

    @Test
    public void blackListCarTest(){
        int carId = 1;
        this.adminListCarService.blackListCar(carId);
        ArrayList<AdminCar> adminCars = this.adminListCarService.getAllCars();
        int carStatusToTest = 3;
        int carStatusGot = 0;
        for(int i=0;i<adminCars.size();i++){
            AdminCar adminCar = adminCars.get(i);
            if(adminCar.getCarId() == carId){
                carStatusGot = adminCar.getCarStatus();
            }
        }
        assertEquals(carStatusToTest,carStatusGot);
    }


}
