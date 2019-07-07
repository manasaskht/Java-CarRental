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

    @Autowired
    public CarEditService(@Qualifier("CarRentService") ICarRentService carRentService,
                          @Qualifier("CarEditDAO") ICarEditDAO carEditDAO){
        this.carRentService = carRentService;
        this.carEditDAO = carEditDAO;
    }

    @Override
    public boolean validCarModel(String model) {
        return carRentService.validCarModel(model);
    }

    @Override
    public boolean validCarDescription(String description) {
        return carRentService.validCarDescription(description);
    }

    @Override
    public boolean validCarPrice(double rate) {
        return carRentService.validCarPrice(rate);
    }

    @Override
    public boolean validCarType(int carTypeId) {
        return carRentService.validCarType(carTypeId);
    }

    @Override
    public boolean validCarCity(int cityId) {
        return carRentService.validCarCity(cityId);
    }

    @Override
    public boolean validCarImageSize(MultipartFile carImage) {
        return carRentService.validCarImageSize(carImage);
    }

    @Override
    public void updateCar(Car car) {
        carEditDAO.updateCar(car);
    }

    @Override
    public void updateCarImage(int carID,MultipartFile carImage) {
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
        return carRentService.getCarById(id);
    }

    @Override
    public ArrayList<CarType> getCarTypeList() {
        return carRentService.getCarTypeList();
    }

    @Override
    public ArrayList<City> getCityList() {
        return carRentService.getCityList();
    }
}
