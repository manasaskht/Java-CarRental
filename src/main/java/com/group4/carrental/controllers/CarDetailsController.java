package com.group4.carrental.controllers;

import com.group4.carrental.authentication.Authentication;
import com.group4.carrental.model.CarBooking;
import com.group4.carrental.model.CarList;
import com.group4.carrental.service.ICarDetailsService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CarDetailsController {

    private ICarDetailsService carDetailsService;
    private LoggerInstance loggerInstance;
    private Authentication authentication = Authentication.getInstance();

    @Autowired
    public CarDetailsController(@Qualifier("CarDetailsService") ICarDetailsService carDetailsService,LoggerInstance loggerInstance){
        this.carDetailsService = carDetailsService;
        this.loggerInstance = loggerInstance;
    }


    @PostMapping("carDetails")
    public String carDetails(@ModelAttribute("carBooking") CarBooking carBooking, HttpSession session, Model model){

        if(authentication.isValidUserSession(session)){
            int carId = carBooking.getCarId();
            CarList carDetails = carDetailsService.getCarDetailsById(carId);
            model.addAttribute("carDetails",carDetails);
            model.addAttribute("carBooking",carBooking);
            return "carDetails";
        }else {
            return "redirect:login";
        }
    }


}

