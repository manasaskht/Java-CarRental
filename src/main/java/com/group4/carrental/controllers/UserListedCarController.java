package com.group4.carrental.controllers;

import com.group4.carrental.model.CarList;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.service.IUserListedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserListedCarController {

    private IUserListedCarService userListedCarService;

    @Autowired
    public UserListedCarController(@Qualifier("UserListedCarService") IUserListedCarService userListedCarService){
        this.userListedCarService = userListedCarService;

    }


    @GetMapping("/user-listed-cars")
    public String userListedCars(Model model, HttpSession session){

        int userId = 0;

        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }



        ArrayList<CarList> listedCars = userListedCarService.getUserListedCars(userId);

        model.addAttribute("listedCars",listedCars);
        return  "userListedcar";

    }

    @PostMapping("/user-listed-cars")
    public String removeCarFromListedCar(@RequestParam("carId") int carId, Model model, HttpSession session){

        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }

        System.out.println("car id = "+carId);
        userListedCarService.removeCarById(carId);
        ArrayList<CarList> listedCars = userListedCarService.getUserListedCars(userId);

        model.addAttribute("listedCars",listedCars);
        return "userListedcar";
    }

    @GetMapping("edit-car-details")
    public String editCarDetails(@RequestParam("carIdEdit") int carId,HttpSession session, Model model){

        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }

        CarList carDetails = userListedCarService.getCarDetailsById(carId);

        ArrayList<CarType> carTypeArrayList = userListedCarService.getCarTypeList();
        model.addAttribute("carType",carTypeArrayList);
        ArrayList<City> cityArrayList = userListedCarService.getCityList();
        model.addAttribute("city",cityArrayList);


        model.addAttribute("carDetails",carDetails);


        return "editCarDetails";
    }
}
