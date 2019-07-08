package com.group4.carrental.dao;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;

import java.util.ArrayList;

public interface IAdminBlacklistCarsDAO {
    public ArrayList<AdminCar> getBlacklistCars();
    public void updateCarStatus(int carId,int carStatus);
}
