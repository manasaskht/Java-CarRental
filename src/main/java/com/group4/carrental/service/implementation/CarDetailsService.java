package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ICarDetailsDAO;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.ICarDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CarDetailsService")
public class CarDetailsService implements ICarDetailsService {


    private ICarDetailsDAO carDetailsDAO;

    public CarDetailsService(@Qualifier("CarDetailsDAO") ICarDetailsDAO carDetailsDAO){
        this.carDetailsDAO = carDetailsDAO;
    }

    @Override
    public CarList getCarById(int carId) {
        return carDetailsDAO.getCarById(carId);
    }
}
