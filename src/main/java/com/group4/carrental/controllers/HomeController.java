package com.group4.carrental.controllers;

import com.group4.carrental.authentication.Authentication;
import com.group4.carrental.model.Car;
import com.group4.carrental.model.CarType;
import com.group4.carrental.model.City;
import com.group4.carrental.model.Search;
import com.group4.carrental.service.IHomeService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class HomeController implements ErrorController {

    private IHomeService homeService;
    private LoggerInstance loggerInstance;

    public HomeController(@Qualifier("HomeService")IHomeService homeService, LoggerInstance loggerInstance){
        this.homeService = homeService;
        this.loggerInstance = loggerInstance;
    }

    @GetMapping("")
    public String getHome(){
        return "redirect:homePage";
    }

    @GetMapping("/homePage")
    public String getHomePage(HttpSession session, Model model){
        Authentication authentication = Authentication.getInstance();
        if(authentication.isValidUserSession(session)){
            int userId = authentication.getUserId();
            ArrayList<CarType> carTypeArrayList = homeService.getCarTypeList();
            model.addAttribute("carType",carTypeArrayList);
            ArrayList<City> cityArrayList = homeService.getCityList();
            model.addAttribute("city",cityArrayList);
            return "homePage";
        }else {
            return "redirect:login";
        }

    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request)
    {
        Object ErrorStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (ErrorStatus != null) {
            Integer ErrorstatusCode = Integer.valueOf(ErrorStatus.toString());

            if(ErrorstatusCode == HttpStatus.NOT_FOUND.value()) {
                return "error404";
            }
            else if(ErrorstatusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error500";
            }
        }

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @PostMapping("/homePage")
    public String search(HttpSession session, @ModelAttribute("search") Search searchData, Model model){
        Authentication authentication = Authentication.getInstance();
        if(authentication.isValidUserSession(session)){
            int userId = authentication.getUserId();
            boolean error = false;
            if(! this.homeService.validCarCity(searchData.getCityId())){
                model.addAttribute("cityError","Please select a valid City");
                error = true;
            }

            if(!this.homeService.validCarType(searchData.getCarType())){
                model.addAttribute("carError","Please select a valid Car");
                error = true;
            }

            if(! (this.homeService.validDate(searchData.getDateFrom()) && this.homeService.validDate(searchData.getDateTo()))){
                model.addAttribute("dateError","Please select a valid Date");
                error = true;
            }

            if(error){
                ArrayList<CarType> carTypeArrayList = homeService.getCarTypeList();
                ArrayList<City> cityArrayList = homeService.getCityList();
                model.addAttribute("carType", carTypeArrayList);
                model.addAttribute("city", cityArrayList);
                model.addAttribute("fromDate", searchData.getDateFrom());
                model.addAttribute("toDate", searchData.getDateTo());
                return "homePage";
            }else{
                ArrayList<Car> carArrayList = this.homeService.getAvailableCars(searchData, userId);
                System.out.println(carArrayList.size());
                ArrayList<CarType> carTypeArrayList = homeService.getCarTypeList();
                ArrayList<City> cityArrayList = homeService.getCityList();
                model.addAttribute("carType", carTypeArrayList);
                model.addAttribute("city", cityArrayList);
                model.addAttribute("cars", carArrayList);
                model.addAttribute("fromDate", searchData.getDateFrom());
                model.addAttribute("toDate", searchData.getDateTo());
                return "homePage";
            }
        }else{
            return "redirect:login";
        }

    }
}
