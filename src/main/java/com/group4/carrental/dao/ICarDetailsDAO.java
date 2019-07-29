package com.group4.carrental.dao;

import com.group4.carrental.model.CarList;

public interface ICarDetailsDAO {
    public CarList getCarById(int carId);
    public CarList getBookedCarById(int carId);
}
