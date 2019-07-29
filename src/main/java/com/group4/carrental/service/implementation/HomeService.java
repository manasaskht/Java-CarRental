package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IHomeDAO;
import com.group4.carrental.model.*;
import com.group4.carrental.service.ICarRentService;
import com.group4.carrental.service.IHomeService;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("HomeService")
public class HomeService implements IHomeService {

    private IHomeDAO homeDAO;
    private ICarRentService carRentService;
    private IUserSignUpService userSignUpService;
    private LoggerInstance loggerInstance;

    public HomeService(@Qualifier("HomeDAO") IHomeDAO homeDAO, @Qualifier("CarRentService") ICarRentService carRentService, @Qualifier("UserSignUpService") IUserSignUpService userSignUpService, LoggerInstance loggerInstance) {
        this.homeDAO = homeDAO;
        this.userSignUpService = userSignUpService;
        this.loggerInstance = loggerInstance;
        this.carRentService = carRentService;
    }

    public ArrayList<Car> getCarForRegion(int carType, int city, int userId) {
        return this.homeDAO.getCarForRegion(carType, city, userId);
    }

    public ArrayList<CarBooking> getBookings(String from, String to) {
        return this.homeDAO.getBookings(from, to);
    }

    @Override
    public ArrayList<Car> getAvailableCars(Search search, int userId) {
        ArrayList<Car> allCars = new ArrayList<>();
        ArrayList<CarBooking> currentCarBooking = new ArrayList<>();
        ArrayList<Car> availableCars = new ArrayList<>();

        allCars = this.getCarForRegion(search.getCarType(), search.getCityId(), userId);
        currentCarBooking = this.getBookings(search.getDateFrom(), search.getDateTo());

        if (currentCarBooking.size() == 0) {
            availableCars = allCars;
        } else {
            for (int i = 0; i < allCars.size(); i++) {
                int carId = allCars.get(i).getCarId();
                boolean add = true;
                for (int j = 0; j < currentCarBooking.size(); j++) {
                    int bookedCarId = currentCarBooking.get(j).getCarId();
                    if(carId == bookedCarId){
                        add = false;
                    }
                }
                if(add){
                    availableCars.add(allCars.get(i));
                }
            }
        }

        return availableCars;
    }

    @Override
    public ArrayList<CarType> getCarTypeList() {
        return carRentService.getCarTypeList();
    }

    @Override
    public ArrayList<City> getCityList() {
        return userSignUpService.getCityList();
    }
}
