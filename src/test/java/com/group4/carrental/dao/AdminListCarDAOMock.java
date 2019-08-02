package com.group4.carrental.dao;

import com.group4.carrental.model.AdminCar;

import java.util.ArrayList;

public class AdminListCarDAOMock implements IAdminListCarDAO {

    private ArrayList<AdminCar> adminCars;

    public AdminListCarDAOMock(){
        this.adminCars = new ArrayList<>();

        AdminCar adminCar1 = new AdminCar();
        adminCar1.setCarModel("Model 1");
        adminCar1.setCarDescription("Description 1");
        adminCar1.setCarRate(11.5);
        adminCar1.setCarOwnerName("Abcde");
        adminCar1.setCarOwnerMail("abcde@gmail.com");
        adminCar1.setCarId(1);
        adminCar1.setCarStatus(2);
        adminCar1.setCarImage("abcd");
        adminCar1.setCarCity("Halifax");
        this.adminCars.add(adminCar1);

        AdminCar adminCar2 = new AdminCar();
        adminCar2.setCarModel("Model 2");
        adminCar2.setCarDescription("Description 2");
        adminCar2.setCarRate(11.5);
        adminCar2.setCarOwnerName("Abcd");
        adminCar2.setCarOwnerMail("abcd@gmail.com");
        adminCar2.setCarId(2);
        adminCar2.setCarStatus(2);
        adminCar2.setCarImage("abcd");
        adminCar2.setCarCity("Halifax");
        this.adminCars.add(adminCar2);

    }

    @Override
    public ArrayList<AdminCar> getAllCars() {
        return this.adminCars;
    }

    @Override
    public void blackListCar(int id) {
        for(int i=0;i<adminCars.size();i++){
            AdminCar adminCar = adminCars.get(i);
            if(adminCar.getCarId() == id){
                adminCar.setCarStatus(3);
            }
        }
    }

    @Override
    public String getEmail(int carId) {
        String email = "";
        for(int i=0;i<adminCars.size();i++){
            AdminCar adminCar = adminCars.get(i);
            if(adminCar.getCarId() == carId){
                email = adminCar.getCarOwnerMail();
            }
        }
        return email;
    }
}
