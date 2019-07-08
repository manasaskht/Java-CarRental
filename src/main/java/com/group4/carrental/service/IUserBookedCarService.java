package com.group4.carrental.service;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarList;

import java.util.ArrayList;

public interface IUserBookedCarService {

    public ArrayList<CarList> getUserBookedCars(int userID);

    public void removeBookedCar(int carId);


}
