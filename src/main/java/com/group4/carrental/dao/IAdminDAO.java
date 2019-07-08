package com.group4.carrental.dao;

import com.group4.carrental.model.AdminCar;

import java.util.ArrayList;

public interface IAdminDAO {

    public ArrayList<AdminCar> getAllCars(int status);
    public void blackListCar(int id);
    public String getEmail(int carId);
}
