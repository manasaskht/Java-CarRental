package com.group4.carrental.controllers;

import com.group4.carrental.service.ICarRentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarRentController {

    private ICarRentService carRentService;

    public CarRentController(@Qualifier("CarRentService") ICarRentService carRentService){
        this.carRentService = carRentService;
    }

    @GetMapping("/carrent")
    public String carToRent(Model model){
        return "carrent";
    }

}
