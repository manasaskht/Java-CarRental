package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ICarRentDAO;
import com.group4.carrental.service.ICarRentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CarRentService")
public class CarRentService implements ICarRentService {

    private ICarRentDAO carRentDAO;

    public CarRentService(@Qualifier("CarRentDao") ICarRentDAO carRentDAO){
        this.carRentDAO = carRentDAO;
    }

}
