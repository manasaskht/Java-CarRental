package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ICarRentDAO;

import com.group4.carrental.model.City;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;

import com.group4.carrental.service.ICarRentService;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

@Service("CarRentService")
public class CarRentService implements ICarRentService {

    private ICarRentDAO carRentDAO;
    private IUserSignUpService userSignUpService;
    private LoggerInstance loggerInstance;

    public CarRentService(@Qualifier("CarRentDao") ICarRentDAO carRentDAO, @Qualifier("UserSignUpService") IUserSignUpService userSignUpService, LoggerInstance loggerInstance) {
        this.carRentDAO = carRentDAO;
        this.userSignUpService = userSignUpService;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public boolean validCarModel(String model) {
        if (model != null && !model.isEmpty()) {
            if ((model.length() >= 5) && (model.length() <= 50)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean validCarDescription(String description) {
        if (description != null && !description.isEmpty()) {
            if ((description.length() >= 10) && (description.length() <= 300)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean validCarPrice(double rate) {
        if (rate > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validCarType(int carTypeId) {
        ArrayList<CarType> carTypeArrayList = this.getCarTypeList();
        for (CarType carType : carTypeArrayList) {
            if (carType.getCarTypeId() == carTypeId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validCarCity(int cityId) {
        ArrayList<City> cityArrayList = this.getCityList();
        for(City city: cityArrayList){
            if(city.getCityId() == cityId){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validCarImageSize(MultipartFile carImage) {
        if (carImage.getSize() < 1048576) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addCar(Car car, MultipartFile carImage,int userId) {
        loggerInstance.log(0,"Car Rent Service Add Car: Called");
        byte[] image;
        try {
            image = carImage.getBytes();
            Blob carImageBlob = new SerialBlob(image);
            carRentDAO.addCar(car, carImageBlob,userId);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Car getCarById(int id) {
        loggerInstance.log(0,"Car Rent Service Get Car: Called");
        return carRentDAO.getCarById(id);
    }

    @Override
    public ArrayList<CarType> getCarTypeList() {
        loggerInstance.log(0,"Car Rent Service Get Car List: Called");
        return carRentDAO.getCarType();
    }

    @Override
    public ArrayList<City> getCityList() {
        return this.userSignUpService.getCityList();
    }

}
