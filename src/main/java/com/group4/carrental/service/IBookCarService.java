package com.group4.carrental.service;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarBooking;
import com.group4.carrental.model.User;

public interface IBookCarService {

    public User getUserDetails(Integer userId);
    public boolean isValidCreditCardNumber(String number);
    public void saveCarBookingDetails(CarBooking carBooking);
    public Car getCarDetailsbyId(Integer carId);
}
