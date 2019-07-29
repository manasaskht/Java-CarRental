package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ICarRentDAO;
import com.group4.carrental.dao.IUserListedCarsDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarList;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.service.ICarRentService;
import com.group4.carrental.service.IUserListedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service("UserListedCarService")
public class UserListedCarService implements IUserListedCarService {

    private IUserListedCarsDAO userListedCarsDAO;
    private ICarRentService carRentService;
    private LoggerInstance loggerInstance;

    @Autowired
    public UserListedCarService(@Qualifier("UserListedCarsDAO") IUserListedCarsDAO userListedCarsDAO,
                                @Qualifier("CarRentService") ICarRentService carRentService,LoggerInstance loggerInstance){
        this.userListedCarsDAO = userListedCarsDAO;
        this.carRentService = carRentService;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public ArrayList<CarList> getUserListedCars(int userID) {
        loggerInstance.log(0,"User Service Get All Listed Cars: Called");
        return userListedCarsDAO.getListedCars(userID);
    }

    @Override
    public void removeCarById(int carId) {
        loggerInstance.log(0,"User Service Remove Car By Id: Called");
        userListedCarsDAO.removeCarById(carId);
    }

    @Override
    public CarList getCarDetailsById(int carId) {
        loggerInstance.log(0,"User Get Car Details By Id: Called");
        return userListedCarsDAO.getCarDetailsById(carId);
    }

    @Override
    public ArrayList<CarType> getCarTypeList() {
        loggerInstance.log(0,"User Get All Car Type List: Called");
        return carRentService.getCarTypeList();
    }

    @Override
    public ArrayList<City> getCityList() {
        loggerInstance.log(0,"User Service Get All City Type List: Called");
        return carRentService.getCityList();
    }

    @Override
    public boolean isCarBooked(int carId) {
        boolean isBooked = userListedCarsDAO.isCarBooked(carId);
        return isBooked;
    }

}
