package com.group4.carrental.service;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarList;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface IUserListedCarService {

    public ArrayList<CarList> getUserListedCars(int userID);
    public void removeCarById(int carId);
    public CarList getCarDetailsById(int carId);
    public ArrayList<CarType> getCarTypeList();
    public ArrayList<City> getCityList();
    public boolean isCarBooked(int carId);
}
