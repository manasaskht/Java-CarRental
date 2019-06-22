package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ICarRentDAO;

import com.group4.carrental.model.User;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;

import com.group4.carrental.service.ICarRentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

@Service("CarRentService")
public class CarRentService implements ICarRentService {

    private ICarRentDAO carRentDAO;

    public CarRentService(@Qualifier("CarRentDao") ICarRentDAO carRentDAO) {
        this.carRentDAO = carRentDAO;
    }



    @Override
    public boolean validCarModel(String string) {
        if (string != null && !string.isEmpty()) {
            if (string.length() > 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean validCarDescription(String string) {
        if (string != null && !string.isEmpty()) {
            if (string.length() > 10) {
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
        ArrayList<CarType> carTypeArrayList = this.getCarType();
        for (CarType carType : carTypeArrayList) {
            if (carType.getCarTypeId() == carTypeId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validCarCity(int cityId) {
        return true;
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
    public void addCar(Car car, MultipartFile carImage) {
        byte[] image;
        try {
            image = carImage.getBytes();
            Blob carImageBlob = new SerialBlob(image);
            carRentDAO.addCar(car, carImageBlob);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Car getCarById(int id) {
        return carRentDAO.getCarById(id);
    }

    @Override
    public ArrayList<CarType> getCarType() {
        return carRentDAO.getCarType();
    }



}
