package com.group4.carrental.dao;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;

import java.sql.Blob;
import java.util.ArrayList;

public interface ICarRentDAO {

    public void addCar(Car car, Blob carImage, int userId);
    public Car getCarById(int id);
    public ArrayList<CarType> getCarType();

}
