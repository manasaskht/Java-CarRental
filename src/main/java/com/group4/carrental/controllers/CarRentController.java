package com.group4.carrental.controllers;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.service.ICarRentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CarRentController {

    private ICarRentService carRentService;

    public CarRentController(@Qualifier("CarRentService") ICarRentService carRentService){
        this.carRentService = carRentService;
    }

    @GetMapping("/carrent")
    public String carToRent(Model model, HttpSession session){
        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }
        ArrayList<CarType> carTypeArrayList = carRentService.getCarTypeList();
        model.addAttribute("carType",carTypeArrayList);
        ArrayList<City> cityArrayList = carRentService.getCityList();
        model.addAttribute("city",cityArrayList);
        return "carrent";
    }

    @PostMapping("/carrent")
    public String carRentDetails(Model model, @ModelAttribute("car") Car car, @RequestParam("carImage")MultipartFile carImage, HttpSession session){
        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }
        boolean error = false;
        if(!carRentService.validCarModel(car.getModel())){
            model.addAttribute("modelError","Please enter valid car model");
            error = true;
        }
        if(!carRentService.validCarDescription(car.getDescription())){
            model.addAttribute("descriptionError","Please enter more description about your car");
            error = true;
        }
        if(!carRentService.validCarCity(car.getCity())){
            model.addAttribute("cityError","Please select a valid City");
            error = true;
        }
        if(!carRentService.validCarPrice(car.getCarRate())){
            model.addAttribute("rateError","Please select a valid Car Rate");
            error = true;
        }
        if(!carRentService.validCarType(car.getCarTypeId())){
            model.addAttribute("carTypeError","Please select a valid Car Type");
            error = true;
        }
        if(!carRentService.validCarImageSize(carImage)){
            model.addAttribute("imageError","Please select an Image size smaller than 2MB");
            error = true;
        }
        if(error){
            ArrayList<CarType> carTypeArrayList = carRentService.getCarTypeList();
            model.addAttribute("carType",carTypeArrayList);
            ArrayList<City> cityArrayList = carRentService.getCityList();
            model.addAttribute("city",cityArrayList);
            return "carrent";
        }else{
            carRentService.addCar(car,carImage,userId);
            return "redirect:homePage";
        }
    }

}
