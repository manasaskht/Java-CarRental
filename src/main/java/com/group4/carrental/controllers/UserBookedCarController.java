package com.group4.carrental.controllers;


import com.group4.carrental.model.CarList;
import com.group4.carrental.service.IUserBookedCarService;
import com.group4.carrental.service.IUserListedCarService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserBookedCarController {

    private IUserBookedCarService  userBookedCarService;
    private LoggerInstance loggerInstance;

    public UserBookedCarController(@Qualifier("UserBookedCarService") IUserBookedCarService  userBookedCarService,
                                   LoggerInstance loggerInstance){

        this.userBookedCarService = userBookedCarService;
        this.loggerInstance = loggerInstance;
    }


    @GetMapping("/user-booked-cars")
    public String userBookedCars(Model model, HttpSession session){
        loggerInstance.log(0,"User Booked car: Called");

        int userId = 0;

        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }

        ArrayList<CarList> bookedCars = userBookedCarService.getUserBookedCars(userId);
        model.addAttribute("bookedCars",bookedCars);

        return "userBookedcar";
    }

    @PostMapping("/user-booked-cars")
    public String removeCarFromBookedCar(@RequestParam("carId") int carId, Model model, HttpSession session){
        loggerInstance.log(0,"User Removed Car From Booked Car: Called");
        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }
        userBookedCarService.removeBookedCar(carId);
        ArrayList<CarList> bookedCars = userBookedCarService.getUserBookedCars(userId);
        model.addAttribute("bookedCars",bookedCars);

        return "userBookedcar";
    }




}
