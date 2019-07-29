package com.group4.carrental.dao;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarBooking;

import java.util.ArrayList;

public interface IHomeDAO {

    public ArrayList<Car> getCarForRegion(int carType, int city, int userId);

    public ArrayList<CarBooking> getBookings(String from, String to);

}
