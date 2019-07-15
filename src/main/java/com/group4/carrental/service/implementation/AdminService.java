package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IAdminDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.Email;
import com.group4.carrental.service.IAdminService;
import com.group4.carrental.service.ISendMailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("AdminService")
public class AdminService implements IAdminService {

    private IAdminDAO adminDAO;
    private ISendMailService sendMailService;
    private LoggerInstance loggerInstance;

    public AdminService(@Qualifier("AdminDao") IAdminDAO adminDAO, @Qualifier("SendMailService") ISendMailService sendMailService, LoggerInstance loggerInstance) {
        this.adminDAO = adminDAO;
        this.sendMailService = sendMailService;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public ArrayList<AdminCar> getAllCars()
    {
        loggerInstance.log(0,"Admin Service All Cars: Called");
        return adminDAO.getAllCars(2);
    }

    @Override
    public void blackListCar(int id) {
        loggerInstance.log(0,"Admin Service BlackList Cars: Called");
        adminDAO.blackListCar(id);
        this.sendEmail(id);
    }

    @Override
    public void sendEmail(int carId) {
        loggerInstance.log(0,"Admin Service Email: Called");
        String emailID = this.adminDAO.getEmail(carId);
        Email email = new Email();
        email.setReceiver(emailID);
        email.setEmailText("Sorry your car has been blacklisted by our admin");
        email.setSubject("BlackListed the car");
        this.sendMailService.sendEmail(email);
    }
}
