package com.group4.carrental.dao;

import com.group4.carrental.model.AdminCar;

import java.util.ArrayList;

public interface IAdminListCarDAO {

    public ArrayList<AdminCar> getAllCars();
    public void blackListCar(int id);
    public String getEmail(int carId);
}
