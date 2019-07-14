package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ICarEditDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.service.ICarEditService;
import com.group4.carrental.service.ICarRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

@Service("CarEditService")
public class CarEditService implements ICarEditService {


    private ICarRentService carRentService;
    private ICarEditDAO carEditDAO;
    private LoggerInstance loggerInstance;

    @Autowired
    public CarEditService(@Qualifier("CarRentService") ICarRentService carRentService,
                          @Qualifier("CarEditDAO") ICarEditDAO carEditDAO,LoggerInstance loggerInstance){
        this.carRentService = carRentService;
        this.carEditDAO = carEditDAO;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public boolean validCarModel(String model) {
        loggerInstance.log(0,"Car Edit Service - Valid Car Model: Called");
        return carRentService.validCarModel(model);
    }

    @Override
    public boolean validCarDescription(String description) {
        loggerInstance.log(0,"Car Edit Service - Valid car Description: Called");
        return carRentService.validCarDescription(description);
    }

    @Override
    public boolean validCarPrice(double rate) {
        loggerInstance.log(0,"Car Edit Service - Valid Car Price: Called");
        return carRentService.validCarPrice(rate);
    }

    @Override
    public boolean validCarType(int carTypeId) {
        loggerInstance.log(0,"Car Edit Service - Valid Car Type: Called");
        return carRentService.validCarType(carTypeId);
    }

    @Override
    public boolean validCarCity(int cityId) {
        loggerInstance.log(0,"Car Edit Service - Valid Car City: Called");
        return carRentService.validCarCity(cityId);
    }

    @Override
    public boolean validCarImageSize(MultipartFile carImage) {
        loggerInstance.log(0,"Car Edit Service - Valid Car Image Size: Called");
        return carRentService.validCarImageSize(carImage);
    }

    @Override
    public void updateCar(Car car) {
        loggerInstance.log(0,"Car Edit Service - Update Car: Called");
        carEditDAO.updateCar(car);
    }

    @Override
    public void updateCarImage(int carID,MultipartFile carImage) {
        loggerInstance.log(0,"Car Edit Service - Update Car Image: Called");
        if(!carImage.isEmpty()){

            byte[] image;
            try {
                image = carImage.getBytes();
                Blob carImageBlob = new SerialBlob(image);
                carEditDAO.updateCarImage(carID,carImageBlob);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Car getCarById(int id) {
        loggerInstance.log(0,"Car Edit Service - Get Car Details By Id: Called");
        return carRentService.getCarById(id);
    }

    @Override
    public ArrayList<CarType> getCarTypeList() {
        loggerInstance.log(0,"Car Edit Service - Get All Car Type: Called");
        return carRentService.getCarTypeList();
    }

    @Override
    public ArrayList<City> getCityList() {
        loggerInstance.log(0,"Car Edit Service - Get All City: Called");
        return carRentService.getCityList();
    }
}
