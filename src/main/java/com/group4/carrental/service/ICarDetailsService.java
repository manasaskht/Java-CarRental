package com.group4.carrental.service;

import com.group4.carrental.model.CarList;

public interface ICarDetailsService {
    public CarList getCarById(int carId);
    public CarList getBookedCarById(int carId);

}
