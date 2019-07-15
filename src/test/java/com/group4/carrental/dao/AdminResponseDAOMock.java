package com.group4.carrental.dao;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.User;

import java.util.ArrayList;
public class AdminResponseDAOMock implements IAdminResponseDAO {
    private ArrayList<AdminCar> admincarArrayList;
    private ArrayList<User> userArrayList;

    public AdminResponseDAOMock() {
        this.userArrayList = new ArrayList<User>();
        this.admincarArrayList = new ArrayList<AdminCar>();
        User user1 = new User();
        user1.setUserId(1);
        AdminCar car1 = new AdminCar();
        car1.setCarDescription("Description");
        car1.setOwner_id(1);
        car1.setCarId(2);
        car1.setCarRate(12);
        car1.setCarModel("Abcd");
        car1.setCarStatus(4);
        car1.setCarOwnerName("abc");
        car1.setCarOwnerMail("manasa@dal.ca");

        User user2 = new User();
        user2.setUserId(2);
        AdminCar car2 = new AdminCar();
        car2.setCarDescription("Description");
        car2.setOwner_id(2);
        car2.setCarId(1);
        car2.setCarRate(12);
        car2.setCarModel("Abcd");
        car2.setCarStatus(4);
        car1.setCarOwnerName("bcd");
        car1.setCarOwnerMail("manasa@dal.ca");
        this.admincarArrayList.add(car1);
        this.admincarArrayList.add(car2);
        this.userArrayList.add(user1);
        this.userArrayList.add(user1);

    }

    @Override
    public void carApproval(int id) {
        for (AdminCar car : admincarArrayList) {
            if (car.getCarId() == id) {
                car.setCarStatus(2);
            }
        }

    }

    @Override
    public void carReject(int id) {
        for (AdminCar car : admincarArrayList) {
            if (car.getCarId() == id) {
                car.setCarStatus(5);
            }
        }

    }

    @Override
    public ArrayList<AdminCar> getAllPendingRequests(int status) {
        for (AdminCar car : this.admincarArrayList) {
            for (User user : this.userArrayList) {
                if (user.getUserId() == car.getOwner_id() && (car.getCarStatus() == status)) {
                    return this.admincarArrayList;
                }


            }
        }
        return null;
    }
    public String getEmail(int carId)
    {
        for (AdminCar car : this.admincarArrayList) {
            for (User user : this.userArrayList) {
                if (user.getUserId() == car.getOwner_id() && (car.getCarId() == carId)) {
                    return car.getCarOwnerMail();
                }


            }
        }
        return null;
    }
}