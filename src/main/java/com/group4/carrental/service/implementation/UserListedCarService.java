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

    @Autowired
    public UserListedCarService(@Qualifier("UserListedCarsDAO") IUserListedCarsDAO userListedCarsDAO,
                                @Qualifier("CarRentService") ICarRentService carRentService){
        this.userListedCarsDAO = userListedCarsDAO;
        this.carRentService = carRentService;
    }

    @Override
    public ArrayList<CarList> getUserListedCars(int userID) {
        return userListedCarsDAO.getListedCars(userID);
    }

    @Override
    public void removeCarById(int carId) {
        userListedCarsDAO.removeCarById(carId);
    }

    @Override
    public CarList getCarDetailsById(int carId) {

        return userListedCarsDAO.getCarDetailsById(carId);
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
