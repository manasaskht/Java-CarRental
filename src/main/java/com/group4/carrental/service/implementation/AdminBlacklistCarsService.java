package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IAdminBlacklistCarsDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.Email;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IAdminBlacklistCarsService;
import com.group4.carrental.service.ISendMailService;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service("AdminBlacklistCarsService")


public class AdminBlacklistCarsService implements IAdminBlacklistCarsService {

    private IAdminBlacklistCarsDAO  iAdminBlacklistCarsDAO;
    private ISendMailService  iSendMailService;
    private IUserSignUpService  iUserSignUpService;
    public AdminBlacklistCarsService(@Qualifier("AdminBlacklistCarsDAO") IAdminBlacklistCarsDAO adminBlacklistCarsDAO,
                                     @Qualifier("SendMailService") ISendMailService sendMailService,
                                     @Qualifier("UserSignUpService")IUserSignUpService userSignUpService){
        this.iAdminBlacklistCarsDAO = adminBlacklistCarsDAO;
        this.iSendMailService = sendMailService;
        this.iUserSignUpService=userSignUpService;
    }

    @Override
    public ArrayList<AdminCar> getBlacklistCars() {

        return iAdminBlacklistCarsDAO.getBlacklistCars();
    }

    @Override
    public void sendEmail(Email email) {

        email.setSubject("Removed from Blacklist");
        email.setEmailText("Hello,\n" +
                "\n" +
                "we would like to inform you that,We have removed your car from blacklist in our website.Your car is now eligible for rent in future.\n" +
                "\n" +
                "Thanks and Regards\n" +
                "carrental037@gmail.com");
        iSendMailService.sendEmail(email);

    }

    @Override
    public void updateCarStatus(int carId,int carStatus) {

        iAdminBlacklistCarsDAO.updateCarStatus(carId,carStatus);

    }

    @Override
    public User getUserDetails(int userId) {
        return iUserSignUpService.getUserDetails(userId);
    }
}
