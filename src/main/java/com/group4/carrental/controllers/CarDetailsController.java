package com.group4.carrental.controllers;

import com.group4.carrental.model.CarList;
import com.group4.carrental.service.ICarDetailsService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CarDetailsController {

    private ICarDetailsService carDetailsService;
    private LoggerInstance loggerInstance;

    @Autowired
    public CarDetailsController(@Qualifier("CarDetailsService") ICarDetailsService carDetailsService,LoggerInstance loggerInstance){
        this.carDetailsService = carDetailsService;
        this.loggerInstance = loggerInstance;
    }

    @GetMapping("car-details")
    public String carDetails(@RequestParam("carDetails")int carId, HttpSession session, Model model){
        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }

        CarList carDetails = new CarList();
        carDetails = carDetailsService.getCarById(carId);
        model.addAttribute("carDetails",carDetails);

        return "carDetails";
    }

}

