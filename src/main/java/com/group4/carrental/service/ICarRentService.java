package com.group4.carrental.service;



public interface ICarRentService {


import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ICarRentService {

    public boolean validCarModel(String string);
    public boolean validCarDescription(String string);
    public boolean validCarPrice(double rate);
    public boolean validCarType(int carTypeId);
    public boolean validCarCity(int cityId);
    public boolean validCarImageSize(MultipartFile carImage);
    public void addCar(Car car, MultipartFile carImage);
    public Car getCarById(int id);
    public ArrayList<CarType> getCarType();



}
