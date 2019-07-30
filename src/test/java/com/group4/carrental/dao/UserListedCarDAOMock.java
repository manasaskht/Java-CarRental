package com.group4.carrental.dao;

import com.group4.carrental.model.CarList;
import com.group4.carrental.service.IUserListedCarService;

import java.util.ArrayList;

public class UserListedCarDAOMock implements IUserListedCarsDAO {

    ArrayList<CarList> carArrayList;

    public UserListedCarDAOMock(){
        carArrayList = new ArrayList<>();

        CarList car1 = new CarList();
        car1.setCarId(1);
        car1.setCityId(1);
        car1.setCarTypeId(1);
        car1.setCarOwner(10);
        car1.setCarRate(15);
        car1.setCityId(4);

        carArrayList.add(car1);

        CarList car2 = new CarList();
        car2.setCarId(2);
        car2.setCityId(2);
        car2.setCarTypeId(2);
        car2.setCarOwner(11);
        car2.setCarRate(10);
        car2.setCityId(3);

        carArrayList.add(car2);

        CarList car3 = new CarList();
        car3.setCarId(3);
        car3.setCityId(3);
        car3.setCarTypeId(3);
        car3.setCarOwner(10);
        car3.setCarRate(7);
        car3.setCityId(2);

        carArrayList.add(car3);


    }


    @Override
    public ArrayList<CarList> getListedCars(int userId) {

        ArrayList<CarList> listOfCars = new ArrayList<>();
        for(int i=0; i <carArrayList.size();i++){
            CarList car ;
            car = carArrayList.get(i);
            if(car.getCarOwner() == userId){

                listOfCars.add(car);
            }
        }
        return listOfCars;
    }

    @Override
    public void removeCarById(int carId) {

        for(int i=0; i <carArrayList.size(); i++){
            if(carArrayList.get(i).getCarId() == carId){
                carArrayList.remove(i);
            }
        }
    }

    @Override
    public CarList getCarDetailsById(int carId) {
        CarList car = new CarList();
        for(int i=0; i <carArrayList.size(); i++){


            if(carArrayList.get(i).getCarId() == carId){
                car = carArrayList.get(i);
            }
        }
        return car;
    }

    @Override
    public boolean isCarBooked(int carId) {
        return false;
    }


}
