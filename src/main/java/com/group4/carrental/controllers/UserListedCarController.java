package com.group4.carrental.controllers;

import com.group4.carrental.authentication.Authentication;
import com.group4.carrental.authentication.IAuthentication;
import com.group4.carrental.model.CarList;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.service.IUserListedCarService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;

@Controller
public class UserListedCarController {

    private IUserListedCarService userListedCarService;
    private LoggerInstance loggerInstance;
    private Authentication authentication = Authentication.getInstance();

    @Autowired
    public UserListedCarController(@Qualifier("UserListedCarService") IUserListedCarService userListedCarService,
                                   LoggerInstance loggerInstance){
        this.userListedCarService = userListedCarService;
        this.loggerInstance = loggerInstance;
    }


    @GetMapping("/userListedCars")
    public String userListedCars(Model model, HttpSession session) {
        loggerInstance.log(0, "User Listed Cars: Called");

        if(authentication.isValidUserSession(session)){
            int userId = authentication.getUserId();
            ArrayList<CarList> listedCars = userListedCarService.getUserListedCars(userId);
            model.addAttribute("listedCars",listedCars);
            return  "userListedcar";
        }else {
            return "redirect:login";
        }


    }

    @PostMapping("/userListedCars")
    public String removeCarFromListedCar(@RequestParam("carId") int carId, Model model, HttpSession session){
        loggerInstance.log(0,"User Remove Car From Listed Car: Called");
        if(authentication.isValidUserSession(session)){
            int userId = authentication.getUserId();
            boolean isBooked = userListedCarService.isCarBooked(carId);
            if(isBooked){
                model.addAttribute("error","You can not remove Booked Car");
            }else {
                userListedCarService.removeCarById(carId);
            }

            ArrayList<CarList> listedCars = userListedCarService.getUserListedCars(userId);

            model.addAttribute("listedCars",listedCars);
            return "userListedcar";
        }else {
            return "redirect:login";
        }

    }

    @GetMapping("editCarDetails")
    public String editCarDetails(@RequestParam("carIdEdit") int carId,HttpSession session, Model model){
        loggerInstance.log(0,"User Car Edit Details: Called");
        if(authentication.isValidUserSession(session)){
            CarList carDetails = userListedCarService.getCarDetailsById(carId);

            ArrayList<CarType> carTypeArrayList = userListedCarService.getCarTypeList();
            model.addAttribute("carType",carTypeArrayList);
            ArrayList<City> cityArrayList = userListedCarService.getCityList();
            model.addAttribute("city",cityArrayList);


            model.addAttribute("carDetails",carDetails);


            return "carEdit";
        }else {
            return "redirect:login";
        }

    }
}
