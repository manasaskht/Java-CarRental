package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IAdminBlacklistCarsDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.Email;
import com.group4.carrental.model.User;
import com.group4.carrental.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service("AdminBlacklistCarsService")


public class AdminBlacklistCarsService implements IAdminBlacklistCarsService {

    @Autowired
    private LoggerInstance log;
    private IAdminBlacklistCarsDAO  iAdminBlacklistCarsDAO;
    private IObserver iObserver;
    private IEmailsendingSubjcet iEmailsendingSubjcet;
    private IUserSignUpService  iUserSignUpService;
    public AdminBlacklistCarsService(@Qualifier("AdminBlacklistCarsDAO") IAdminBlacklistCarsDAO adminBlacklistCarsDAO,
                                     @Qualifier("EmailSenderObserver") IObserver observer,
                                     @Qualifier("EmailSenderSubject") IEmailsendingSubjcet emailsendingSubjcet,
                                     @Qualifier("UserSignUpService")IUserSignUpService userSignUpService){
        this.iAdminBlacklistCarsDAO = adminBlacklistCarsDAO;
        this.iObserver = observer;
        this.iUserSignUpService=userSignUpService;
        this.iEmailsendingSubjcet=emailsendingSubjcet;
    }

    @Override
    public ArrayList<AdminCar> getBlacklistCars() {

        log.log(0,"In service: getBlacklistCars ");
        return iAdminBlacklistCarsDAO.getBlacklistCars();
    }

    @Override
    public void sendEmail(Email email) {

        log.log(0,"In service: sendEmail to the owner");

        email.setSubject("Removed from Blacklist");
        email.setEmailText("Hello,\n" +
                "\n" +
                "we would like to inform you that,We have removed your car from blacklist in our website.Your car is now eligible for rent in future.\n" +
                "\n" +
                "Thanks and Regards\n" +
                "carrental037@gmail.com");
        try{
            iEmailsendingSubjcet.attach(iObserver);
            iEmailsendingSubjcet.notifyAll();


        }catch (MailException m)
        {
            log.log(2,"In service: Please provide all mail details");
        }
        catch(Exception e)
        {
            log.log(2,"In service: Please provide all mail details");
        }


    }

    @Override
    public void updateCarStatus(int carId,int carStatus) {

        log.log(0,"In service: updateCarStatus");
        iAdminBlacklistCarsDAO.updateCarStatus(carId,carStatus);

    }

    @Override
    public User getUserDetails(int userId) {

        log.log(0,"In service: getUserDetails");
        return iUserSignUpService.getUserDetails(userId);
    }
}
