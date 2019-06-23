package com.group4.carrental.dao;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;

import java.sql.Blob;
import java.util.ArrayList;

public class CarRentDAOMock implements ICarRentDAO{

    private ArrayList<CarType> carTypeArrayList;
    private ArrayList<Car> carArrayList;

    public CarRentDAOMock(){
        this.carTypeArrayList = new ArrayList<CarType>();
        this.carArrayList = new ArrayList<Car>();

        CarType carType1 = new CarType();
        carType1.setCarTypeName("Car");
        carType1.setCarTypeId(1);

        CarType carType2 = new CarType();
        carType2.setCarTypeName("Truck");
        carType2.setCarTypeId(2);

        this.carTypeArrayList.add(carType1);
        this.carTypeArrayList.add(carType2);

        Car car1 = new Car();
        car1.setImageURL("Abcd");
        car1.setDescription("Description");
        car1.setCarOwner(1);
        car1.setCity(1);
        car1.setCarId(2);
        car1.setCarRate(12);
        car1.setCarTypeId(1);
        car1.setModel("Abcd");

        Car car2 = new Car();
        car2.setImageURL("Abcd");
        car2.setDescription("Description");
        car2.setCarOwner(1);
        car2.setCity(1);
        car2.setCarId(2);
        car2.setCarRate(12);
        car2.setCarTypeId(2);
        car2.setModel("Abcd");

        this.carArrayList.add(car1);
        this.carArrayList.add(car2);

    }


    @Override
    public void addCar(Car car, Blob carImage, int userId) {
        this.carArrayList.add(car);
    }

    @Override
    public Car getCarById(int id) {
        for(Car car: this.carArrayList){
            if(car.getCarId() == id){
                return car;
            }
        }
        return null;
    }

    @Override
    public ArrayList<CarType> getCarType() {
        return this.carTypeArrayList;
    }
}
