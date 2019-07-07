package com.group4.carrental.service;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.ArrayList;

public interface ICarEditService {

    public boolean validCarModel(String model);
    public boolean validCarDescription(String description);
    public boolean validCarPrice(double rate);
    public boolean validCarType(int carTypeId);
    public boolean validCarCity(int cityId);
    public boolean validCarImageSize(MultipartFile carImage);
    public void updateCar(Car car);
    public void updateCarImage(int carID,MultipartFile carImage);
    public Car getCarById(int id);
    public ArrayList<CarType> getCarTypeList();
    public ArrayList<City> getCityList();
}
