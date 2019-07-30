package com.group4.carrental.controllers;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.service.ICarEditService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CarEditController {

    private ICarEditService carEditService;
    private LoggerInstance loggerInstance;


    @Autowired
    public CarEditController(@Qualifier("CarEditService") ICarEditService carEditService,LoggerInstance loggerInstance){
        this.carEditService = carEditService;
        this.loggerInstance = loggerInstance;
    }

    @PostMapping("editCarDetails")
    public String editCarDetails(Model model, @ModelAttribute("car") Car car,
                                 @RequestParam("carImage") MultipartFile carImage,
                                 @RequestParam("carId") int carId, HttpSession session){

        loggerInstance.log(0,"User Edit Car Details: Called");
        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException exception){
            return "redirect:login";
        }

        boolean error = false;
        if(!carEditService.validCarModel(car.getModel())){
            model.addAttribute("modelError","Please enter valid car model");
            error = true;
        }
        if(!carEditService.validCarDescription(car.getDescription())){
            model.addAttribute("descriptionError","Please enter more description about your car");
            error = true;
        }
        if(!carEditService.validCarCity(car.getCity())){
            model.addAttribute("cityError","Please select a valid City");
            error = true;
        }
        if(!carEditService.validCarPrice(car.getCarRate())){
            model.addAttribute("rateError","Please select a valid Car Rate");
            error = true;
        }
        if(!carEditService.validCarType(car.getCarTypeId())){
            model.addAttribute("carTypeError","Please select a valid Car Type");
            error = true;
        }
        if(!carEditService.validCarImageSize(carImage)){
            model.addAttribute("imageError","Please select an Image size smaller than 2MB");
            error = true;
        }
        if(error){
            ArrayList<CarType> carTypeArrayList = carEditService.getCarTypeList();
            model.addAttribute("carType",carTypeArrayList);
            ArrayList<City> cityArrayList = carEditService.getCityList();
            model.addAttribute("city",cityArrayList);
            return "carEdit";
        }else{
            System.out.println("car details-"+car.getDescription()+car.getCarId());
            car.setCarId(carId);
            carEditService.updateCar(car);
            carEditService.updateCarImage(carId,carImage);

            return "redirect:userListedCars";
        }
    }

}
