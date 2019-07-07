package com.group4.carrental.dao;

import com.group4.carrental.model.Car;

import java.util.ArrayList;

public interface IAdminDAO {

    public ArrayList<Car> getAllCars();
    public void blackListCar(int id);

}
