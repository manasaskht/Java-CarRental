package com.group4.carrental.dao;

import com.group4.carrental.model.CarList;

import java.util.ArrayList;

public interface IUserBookedCarsDAO {

    ArrayList<CarList> getBookedCars(int userId);

    public void removeBookedCar(int carId);
}
