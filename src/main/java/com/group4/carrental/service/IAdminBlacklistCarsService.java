package com.group4.carrental.service;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.Email;
import com.group4.carrental.model.User;

import java.util.ArrayList;

public interface IAdminBlacklistCarsService {

    public ArrayList<AdminCar> getBlacklistCars();
    public void updateCarStatus(int carId,int carStatus);
    public void sendEmail(Email email);
    public User getUserDetails(int userId);
}
