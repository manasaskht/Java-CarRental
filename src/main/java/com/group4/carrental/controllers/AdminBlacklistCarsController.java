package com.group4.carrental.controllers;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Email;
import com.group4.carrental.service.IAdminBlacklistCarsService;
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
        }catch(NullPointerException exception){
            return "redirect:/admin/login";
        }
        return "adminHomePage";
    }

    @GetMapping("/adminBlackListCars")
    public String getAdminBlacklistCarsList(Email email, Model model,HttpSession session)
    {

        ArrayList<AdminCar> carArrayList=iAdminBlacklistCarsService.getBlacklistCars();
        model.addAttribute("carArrayList",carArrayList);
        return "blackListedCars";
    }

    @PostMapping("/removeFromBlacklist")
    public String removeFromBlacklist(@ModelAttribute("AdminCar") AdminCar adminCar,@ModelAttribute("Email") Email email,Model model)
    {
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
