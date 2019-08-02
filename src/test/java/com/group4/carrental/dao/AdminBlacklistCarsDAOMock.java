package com.group4.carrental.dao;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;

import java.util.ArrayList;

public class AdminBlacklistCarsDAOMock implements IAdminBlacklistCarsDAO {

    private ArrayList<AdminCar> carList;

    public AdminBlacklistCarsDAOMock()
    {
        this.carList= new ArrayList<AdminCar>();

        AdminCar adminCar1 = new AdminCar();
        adminCar1.setCarId(1);
        adminCar1.setCarDescription("My car");
        adminCar1.setCarModel("abc");
        adminCar1.setCarOwnerMail("abc@dal.ca");
        adminCar1.setCarRate(15);
        adminCar1.setCarStatus(3);
        adminCar1.setCarCity("halifax");

        AdminCar adminCar2 = new AdminCar();
        adminCar2.setCarId(2);
        adminCar2.setCarDescription("My car 2");
        adminCar2.setCarModel("abcd");
        adminCar2.setCarStatus(3);
        adminCar2.setCarOwnerMail("xyz@dal.ca");
        adminCar2.setCarRate(20);
        adminCar2.setCarCity("brunswick");



        carList.add(adminCar1);
        carList.add(adminCar2);
    }

    @Override
    public ArrayList<AdminCar> getBlacklistCars() {

        return this.carList;
    }

    @Override
    public void updateCarStatus(int carId, int carStatus) {

        for(int i=0;i<carList.size();i++)
        {
            if(carList.get(i).getCarId()==carId)
            {
                carList.get(i).setCarStatus(carStatus);
            }
        }

    }
}
