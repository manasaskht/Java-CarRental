package com.group4.carrental.service;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.model.Search;

import java.util.ArrayList;

public interface IHomeService {

    public ArrayList<Car> getAvailableCars(Search search, int userId);

    public ArrayList<CarType> getCarTypeList();

    public ArrayList<City> getCityList();

    public boolean validDate(String date);

    public boolean validCarType(int carTypeId);

    public boolean validCarCity(int cityId);

}
