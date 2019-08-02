package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IBookCarDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarBooking;
import com.group4.carrental.model.Email;
import com.group4.carrental.model.User;
import com.group4.carrental.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service("BookCarService")
public class BookCarService implements IBookCarService {

    private IUserSignUpService  iUserSignUpService;
    private IPaymentValidationService iPaymentValidationService;
    private IBookCarDAO iBookCarDAO;
    private ICarRentService iCarRentService;
    private ISendMailService iSendMailService;

    @Autowired
    private LoggerInstance log;

    public BookCarService(@Qualifier("BookCarDAO") IBookCarDAO bookCarDAO,
                          @Qualifier("UserSignUpService") IUserSignUpService userSignUpService,
                          @Qualifier("PaymentValidationService")IPaymentValidationService paymentValidationService,
                          @Qualifier("SendMailService") ISendMailService sendMailService,
                          @Qualifier("CarRentService") ICarRentService carRentService,LoggerInstance loggerInstance)

    {
        this.iUserSignUpService=userSignUpService;
        this.iPaymentValidationService=paymentValidationService;
        this.iBookCarDAO=bookCarDAO;
        this.log=loggerInstance;
        this.iSendMailService = sendMailService;
        this.iCarRentService=carRentService;
    }

    @Override
    public User getUserDetails(Integer userId) {
        log.log(0,"In BookCar service:getUserDetails");
        return iUserSignUpService.getUserDetails(userId);
    }

    @Override
    public boolean isValidCreditCardNumber(String number) {

        log.log(0,"In BookCar service:isValidCreditCardNumber");
        return iPaymentValidationService.isValidCreditCardNumber(number);
    }

    @Override
    public void saveCarBookingDetails(CarBooking carBooking) {

        log.log(0,"In BookCar service:saveCarBookingDetails");
        iBookCarDAO.saveCarBookingDetails(carBooking);
    }

    @Override
    public Car getCarDetailsbyId(Integer carId) {

        log.log(0,"In BookCar service:getCarDetailsbyId");
         return iCarRentService.getCarById(carId);

    }

    @Override
    public double calculateTotalRent(String fromDate, String Todate, double carRate) {

        log.log(0,"In BookCar service:calculateTotalRent");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        double totalRate=0;
        try {
            Date from = myFormat.parse(fromDate);
            Date to = myFormat.parse(Todate);
            long diff = to.getTime() - from.getTime();
            double Days= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            totalRate = Days * carRate;

        } catch (ParseException e) {
            log.log(2,"In BookCar service:calculateTotalRent"+e.getMessage());
            e.printStackTrace();
        }
        return totalRate;
    }

    @Override
    public void sendEmailNotification(Email email) {

        email.setSubject("Car booking confirmation");
        email.setEmailText("Hello,\n" +
                "\n" +
                "Your car booking is confirmed" +
                "\n" +
                "Thanks and Regards\n" +
                "carrental037@gmail.com");
        try{
            iSendMailService.sendEmail(email);

        }catch (MailException m)
        {
            log.log(2,"In service: Please provide all mail details");
        }
        catch(Exception e)
        {
            log.log(2,"In service: Please provide all mail details");
        }


    }
}
