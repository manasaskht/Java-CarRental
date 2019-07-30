package com.group4.carrental.service;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarBooking;
import com.group4.carrental.model.Email;
import com.group4.carrental.model.User;

public interface IBookCarService {

    public User getUserDetails(Integer userId);
    public boolean isValidCreditCardNumber(String number);
    public void saveCarBookingDetails(CarBooking carBooking);
    public Car getCarDetailsbyId(Integer carId);
    public double calculateTotalRent(String fromDate,String Todate,double carRate);
    public void sendEmailNotification(Email email);
}
