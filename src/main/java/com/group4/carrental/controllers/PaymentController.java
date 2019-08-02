package com.group4.carrental.controllers;

import com.group4.carrental.authentication.Authentication;
import com.group4.carrental.model.*;
import com.group4.carrental.service.IBookCarService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class PaymentController {

    private IBookCarService iBookCarService;
    private Authentication authentication = Authentication.getInstance();

    @Autowired
    private LoggerInstance log;
    @Autowired
    public PaymentController(@Qualifier("BookCarService") IBookCarService bookCarService){
        this.iBookCarService = bookCarService;

    }

    @PostMapping("/paymentPage")
    public String paymentPage(HttpSession httpSession, Model model,@ModelAttribute("CarBooking") CarBooking carBooking)
    {

        if(authentication.isValidUserSession(httpSession)) {
            int user_id = (int) httpSession.getAttribute("user_id");
            User userData = iBookCarService.getUserDetails(user_id);
            Car carData = iBookCarService.getCarDetailsbyId(carBooking.getCarId());
            double totalRent = iBookCarService.calculateTotalRent(carBooking.getFromDate(), carBooking.getToDate(), carData.getCarRate());
            carBooking.setUserId(user_id);
            model.addAttribute("totalRent", totalRent);
            model.addAttribute("userData", userData);
            model.addAttribute("carData", carData);
            model.addAttribute("bookingData", carBooking);
            return "paymentPage";
        }
        else
        {
            return "redirect:login";
        }
    }

    @PostMapping("/bookCar")
    public String bookCarDetails(@ModelAttribute("PaymentClass") PaymentClass payment, @ModelAttribute("CarBooking") CarBooking carBooking, HttpSession httpSession, Model model, Email email)
    {


        boolean isCardNumberValid= iBookCarService.isValidCreditCardNumber(payment.getCardNumber());
        User userData= iBookCarService.getUserDetails(carBooking.getUserId());
        Car carData= iBookCarService.getCarDetailsbyId(carBooking.getCarId());
        model.addAttribute("carData",carData);
        model.addAttribute("bookingData",carBooking);
        model.addAttribute("userData",userData);
        model.addAttribute("paymentDetails",payment);
        model.addAttribute("totalRent",payment.getTotalRent());
        if(isCardNumberValid)
        {
            log.log(0,"In controller:check paymentPage details and save booking details in database");
            email.setReceiver(userData.getEmail());
            iBookCarService.saveCarBookingDetails(carBooking);
            iBookCarService.sendEmailNotification(email);
            return "redirect:homePage";
        }
        else
        {
            log.log(0,"In controller:check paymentPage details and redirect to again paymentPage");
            model.addAttribute("cardNumberError","please enter valid card Number");
            return "paymentPage";

        }


    }

}
