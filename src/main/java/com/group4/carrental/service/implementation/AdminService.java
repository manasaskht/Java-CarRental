package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IAdminDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.IAdminService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("AdminService")
public class AdminService implements IAdminService {

    private IAdminDAO adminDAO;

    public AdminService(@Qualifier("AdminDao") IAdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public ArrayList<Car> getAllCars() {
        return adminDAO.getAllCars();
    }

    @Override
    public void blackListCar(int id) {
        adminDAO.blackListCar(id);
    }
}
