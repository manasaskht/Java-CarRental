package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IBookCarDAO;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarBooking;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IBookCarService;
import com.group4.carrental.service.ICarRentService;
import com.group4.carrental.service.IPaymentValidationService;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("BookCarService")
public class BookCarService implements IBookCarService {

    private IUserSignUpService  iUserSignUpService;
    private IPaymentValidationService iPaymentValidationService;
    private IBookCarDAO iBookCarDAO;
    private ICarRentService iCarRentService;

    public BookCarService(@Qualifier("BookCarDAO") IBookCarDAO bookCarDAO,
                          @Qualifier("UserSignUpService") IUserSignUpService userSignUpService,
                          @Qualifier("PaymentValidationService")IPaymentValidationService paymentValidationService,
                          @Qualifier("CarRentService") ICarRentService carRentService)
    {
        this.iUserSignUpService=userSignUpService;
        this.iPaymentValidationService=paymentValidationService;
        this.iBookCarDAO=bookCarDAO;
        this.iCarRentService=carRentService;
    }

    @Override
    public User getUserDetails(Integer userId) {

        return iUserSignUpService.getUserDetails(userId);
    }

    @Override
    public boolean isValidCreditCardNumber(String number) {

        return iPaymentValidationService.isValidCreditCardNumber(number);
    }

    @Override
    public void saveCarBookingDetails(CarBooking carBooking) {

        iBookCarDAO.saveCarBookingDetails(carBooking);
    }

    @Override
    public Car getCarDetailsbyId(Integer carId) {

         return iCarRentService.getCarById(carId);

    }
}
