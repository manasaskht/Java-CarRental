package com.group4.carrental.controllers;

import com.group4.carrental.model.CarBooking;
import com.group4.carrental.model.PaymentClass;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IBookCarService;
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
    @Autowired
    public PaymentController(@Qualifier("BookCarService") IBookCarService bookCarService){
        this.iBookCarService = bookCarService;

    }

    @PostMapping("/paymentPage")
    public String paymentPage(HttpSession httpSession, Model model,@ModelAttribute("CarBooking") CarBooking carBooking)
    {
        int user_id = 0;
        try {
            user_id = (int) httpSession.getAttribute("user_id");
        }catch (NullPointerException exception){

            return "redirect:login";
        }

        User userData= iBookCarService.getUserDetails(user_id);
        //Car carData= iBookCarService.getCarDetailsbyId();
        carBooking.setUserId(user_id);
        model.addAttribute("userData",userData);
        //model.addAttribute("carData",carData);
        model.addAttribute("bookingData",carBooking);
        return "paymentPage";
    }

    @PostMapping("/bookCar")
    public String bookCarDetails(@ModelAttribute("PaymentClass") PaymentClass payment, @ModelAttribute("CarBooking") CarBooking carBooking, HttpSession httpSession,Model model)
    {

        boolean isCardNumberValid= iBookCarService.isValidCreditCardNumber(payment.getCardNumber());
        User userData= iBookCarService.getUserDetails(carBooking.getUserId());
        model.addAttribute("bookingData",carBooking);
        model.addAttribute("userData",userData);
        model.addAttribute("paymentDetails",payment);
        if(isCardNumberValid)
        {
            iBookCarService.saveCarBookingDetails(carBooking);
            return "redirect:homePage";
        }
        else
        {
            model.addAttribute("cardNumberError","please enter valid card Number");
            return "paymentPage";

        }


    }

}
