package com.group4.carrental.service;

import com.group4.carrental.model.AdminCar;

import java.util.ArrayList;

public interface IAdminListCarService {
    public ArrayList<AdminCar> getAllCars();
    public void blackListCar(int id);
    public void sendEmail(int carId);
    public boolean isCarBooked(int carId);
}
