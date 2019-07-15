package com.group4.carrental.controllers;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.IAdminService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AdminListCarController {

    private IAdminService adminService;
    private LoggerInstance loggerInstance;

    public AdminListCarController(@Qualifier("AdminService")IAdminService adminService, LoggerInstance loggerInstance){
        this.adminService = adminService;
        this.loggerInstance = loggerInstance;
    }

    @GetMapping("/admin/list-all-car")
    public String listAllCar(Model model, HttpSession session){
        loggerInstance.log(0,"Admin List All Car: Called");
        int adminId = 0;
        try{
            adminId = (int) session.getAttribute("admin");
        }catch(NullPointerException exception){
            return "redirect:/admin/login";
        }
        ArrayList<AdminCar> carArrayList = this.adminService.getAllCars();
        model.addAttribute("carList",carArrayList);
        return "adminListAllCar";
    }

    @GetMapping("/admin/blacklist/{id}")
    public String blackList(Model model, @PathVariable int id,HttpSession session){
        loggerInstance.log(0,"Admin BlackList Car: Called");
        int adminId = 0;
        try{
            adminId = (int) session.getAttribute("admin");
        }catch(NullPointerException exception){
            return "redirect:/admin/login";
        }
        this.adminService.blackListCar(id);
        return "redirect:/admin/list-all-car";
    }

}
