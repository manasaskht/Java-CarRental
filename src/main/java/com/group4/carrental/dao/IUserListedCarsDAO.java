package com.group4.carrental.dao;

import com.group4.carrental.model.CarList;

import java.util.ArrayList;

public interface IUserListedCarsDAO {

    ArrayList<CarList> getListedCars(int userId);
    public void removeCarById(int carId);
    public CarList getCarDetailsById(int carId);
    public boolean isCarBooked(int carId);

}
