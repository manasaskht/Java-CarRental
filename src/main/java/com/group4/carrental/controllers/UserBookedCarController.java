package com.group4.carrental.controllers;


import com.group4.carrental.authentication.Authentication;
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
    private Authentication authentication = Authentication.getInstance();

    public UserBookedCarController(@Qualifier("UserBookedCarService") IUserBookedCarService  userBookedCarService,
                                   LoggerInstance loggerInstance){

        this.userBookedCarService = userBookedCarService;
        this.loggerInstance = loggerInstance;
    }


    @GetMapping("/userBookedCars")
    public String userBookedCars(Model model, HttpSession session){
        loggerInstance.log(0,"User Booked car: Called");

        if(authentication.isValidUserSession(session)){
            int userId = authentication.getUserId();
            ArrayList<CarList> bookedCars = userBookedCarService.getUserBookedCars(userId);
            for(CarList carList : bookedCars){
                System.out.println("book date -"+carList.getBookedDate());
            }
            model.addAttribute("bookedCars",bookedCars);

            return "userBookedcar";
        }else {
            return "redirect:login";
        }

    }

    @PostMapping("/userBookedCars")
    public String removeCarFromBookedCar(@RequestParam("bookingId") int bookingId, Model model, HttpSession session){
        loggerInstance.log(0,"User Removed Car From Booked Car: Called");

        if(authentication.isValidUserSession(session)){
            int userId = authentication.getUserId();
            userBookedCarService.removeBookedCar(bookingId);
            ArrayList<CarList> bookedCars = userBookedCarService.getUserBookedCars(userId);
            model.addAttribute("bookedCars",bookedCars);

            return "userBookedcar";
        }else {
            return "redirect:login";
        }

    }




}
