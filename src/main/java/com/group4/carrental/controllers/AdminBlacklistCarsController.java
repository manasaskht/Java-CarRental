package com.group4.carrental.controllers;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Email;
import com.group4.carrental.service.IAdminBlacklistCarsService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class AdminBlacklistCarsController {

    @Autowired
    private LoggerInstance log;

    private IAdminBlacklistCarsService iAdminBlacklistCarsService;

    public AdminBlacklistCarsController(@Qualifier("AdminBlacklistCarsService") IAdminBlacklistCarsService adminBlacklistCarsService){
        this.iAdminBlacklistCarsService = adminBlacklistCarsService;
    }

    @GetMapping("/admin/adminPage")
    public String adminBlacklistCarsPage(HttpSession session)
    {
        int adminId = 0;
        try{
            adminId = (int) session.getAttribute("admin");
            log.log(0,"In controller:admin select blacklist cars");
        }
        catch(NullPointerException exception){
            log.log(0,"In controller:admin does not logged in so redirect to login page");
            return "redirect:/admin/login";
        }
        return "adminHomePage";
    }

    @GetMapping("/adminBlackListCars")
    public String getAdminBlacklistCarsList(Email email, Model model,HttpSession session)
    {
        log.log(0,"In controller: fetch blacklisted cars from database");
        ArrayList<AdminCar> carArrayList=iAdminBlacklistCarsService.getBlacklistCars();
        model.addAttribute("carArrayList",carArrayList);
        return "blackListedCars";
    }

    @PostMapping("/removeFromBlacklist")
    public String removeFromBlacklist(@ModelAttribute("AdminCar") AdminCar adminCar,@ModelAttribute("Email") Email email,Model model)
    {
        log.log(0,"In controller: Remove car from blacklist and send message to the owner");
        int carStatus=2;
        int carId=adminCar.getCarId();
        iAdminBlacklistCarsService.updateCarStatus(carId,carStatus);
        email.setReceiver(adminCar.getCarOwnerMail());
        iAdminBlacklistCarsService.sendEmail(email);
        ArrayList<AdminCar> carArrayList=iAdminBlacklistCarsService.getBlacklistCars();
        model.addAttribute("carArrayList",carArrayList);
        return "blackListedCars";
    }

}
