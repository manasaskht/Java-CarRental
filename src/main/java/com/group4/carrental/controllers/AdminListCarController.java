package com.group4.carrental.controllers;

import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.IAdminService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class AdminListCarController {

    private IAdminService adminService;

    public AdminListCarController(@Qualifier("AdminService")IAdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/admin/list-all-car")
    public String listAllCar(Model model){
        ArrayList<AdminCar> carArrayList = this.adminService.getAllCars();
        model.addAttribute("carList",carArrayList);
        return "adminListAllCar";
    }

    @GetMapping("/admin/blacklist/{id}")
    public String blackList(Model model, @PathVariable int id){
        this.adminService.blackListCar(id);
        return "redirect:/admin/list-all-car";
    }

}
