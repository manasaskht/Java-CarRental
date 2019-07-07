package com.group4.carrental.service;

import com.group4.carrental.model.Car;

import java.util.ArrayList;

public interface IAdminService {
    public ArrayList<Car> getAllCars();
    public void blackListCar(int id);
}
