package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IAdminListCarDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Email;
import com.group4.carrental.service.IAdminListCarService;
import com.group4.carrental.service.ISendMailService;
import com.group4.carrental.service.IUserListedCarService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("AdminService")
public class AdminListCarService implements IAdminListCarService {

    private IAdminListCarDAO adminDAO;
    private ISendMailService sendMailService;
    private IUserListedCarService userListedCarService;
    private LoggerInstance loggerInstance;

    public AdminListCarService(@Qualifier("AdminDao") IAdminListCarDAO adminDAO, @Qualifier("SendMailService") ISendMailService sendMailService, LoggerInstance loggerInstance, @Qualifier("UserListedCarService") IUserListedCarService userListedCarService) {
        this.adminDAO = adminDAO;
        this.sendMailService = sendMailService;
        this.loggerInstance = loggerInstance;
        this.userListedCarService = userListedCarService;
    }

    @Override
    public ArrayList<AdminCar> getAllCars()
    {
        loggerInstance.log(0,"Admin Service All Cars: Called");
        return adminDAO.getAllCars();
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

    @Override
    public boolean isCarBooked(int carId) {
        return this.userListedCarService.isCarBooked(carId);
    }
}
