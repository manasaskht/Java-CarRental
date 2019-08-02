package com.group4.carrental.dao;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarBooking;

import java.util.ArrayList;

public class HomeDAOMock implements IHomeDAO{

    private ArrayList<Car> carArrayList;
    private ArrayList<CarBooking> carBookingArrayList;
    public HomeDAOMock(){
        this.carArrayList = new ArrayList<>();
        this.carBookingArrayList = new ArrayList<>();

        Car car1 = new Car();
        car1.setImageURL("Abcd");
        car1.setDescription("Description");
        car1.setCarOwner(1);
        car1.setCity(1);
        car1.setCarId(1);
        car1.setCarRate(12);
        car1.setCarTypeId(1);
        car1.setModel("Abcd");

        Car car2 = new Car();
        car2.setImageURL("Abcd");
        car2.setDescription("Description");
        car2.setCarOwner(2);
        car2.setCity(1);
        car2.setCarId(2);
        car2.setCarRate(12);
        car2.setCarTypeId(1);
        car2.setModel("Abcd");

        CarBooking carBooking1 = new CarBooking();
        carBooking1.setUserId(3);
        carBooking1.setBookingId(1);
        carBooking1.setCarId(1);
        carBooking1.setFromDate("2019/08/01");
        carBooking1.setToDate("2019/08/03");

        CarBooking carBooking2 = new CarBooking();
        carBooking2.setUserId(3);
        carBooking2.setBookingId(1);
        carBooking2.setCarId(1);
        carBooking2.setFromDate("2019/08/04");
        carBooking2.setToDate("2019/08/05");

        this.carArrayList.add(car1);
        this.carArrayList.add(car2);

        this.carBookingArrayList.add(carBooking1);
        this.carBookingArrayList.add(carBooking2);

    }

    @Override
    public ArrayList<Car> getCarForRegion(int carType, int city, int userId) {
        ArrayList<Car> cars = new ArrayList<>();
        for(int i=0;i<carArrayList.size();i++){
            Car car = carArrayList.get(i);
            if(car.getCarTypeId() == carType && car.getCity() == city && car.getCarOwner() != userId){
                cars.add(car);
            }
        }
        return cars ;
    }

    @Override
    public ArrayList<CarBooking> getBookings(String from, String to) {
        return this.carBookingArrayList;
    }
}
