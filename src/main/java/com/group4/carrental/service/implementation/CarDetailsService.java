package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ICarDetailsDAO;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.ICarDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CarDetailsService")
public class CarDetailsService implements ICarDetailsService {


    private ICarDetailsDAO carDetailsDAO;
    private LoggerInstance loggerInstance;

    public CarDetailsService(@Qualifier("CarDetailsDAO") ICarDetailsDAO carDetailsDAO, LoggerInstance loggerInstance){
        this.carDetailsDAO = carDetailsDAO;
        this.loggerInstance = loggerInstance;
    }


    @Override
    public CarList getCarDetailsById(int carId) {
        loggerInstance.log(0,"Car Details Service - Get Car Details by id - Called");
        return carDetailsDAO.getCarDetailsById(carId);
    }
}
