package com.group4.carrental.dao;

import com.group4.carrental.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

public interface ICarEditDAO {

    public void updateCar(Car car);
    public void updateCarImage(int carID,Blob carImage);
}
