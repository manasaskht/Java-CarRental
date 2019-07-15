package com.group4.carrental.controllers;

import com.group4.carrental.model.Admin;
import com.group4.carrental.service.IAdminLoginService;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.LoggerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    private IAdminLoginService adminLoginService;
    private LoggerInstance loggerInstance;

    public AdminLoginController(@Qualifier("AdminLoginService") IAdminLoginService adminLoginService , LoggerInstance loggerInstance){
        this.adminLoginService = adminLoginService;
        this.loggerInstance = loggerInstance;
    }

    @GetMapping("/admin/login")
    public String getLogin(Model model, HttpSession session){
        int adminId = 0;
        loggerInstance.log(0,"Admin Login: Called");
        try{
            adminId = (int) session.getAttribute("admin");
        }catch(NullPointerException exception){
            return "adminLogin";
        }
        return "redirect:/admin/list-all-car";
    }

    @PostMapping("/admin/validate")
    public String validateAdmin(Model model, @ModelAttribute("admin") Admin admin, HttpSession session){
        Admin adminData = this.adminLoginService.validateLogin(admin);
        loggerInstance.log(0,"Admin Login Validate: Called");
        if(adminData != null){
            session.setAttribute("admin",adminData.getAdminId());
            return "redirect:/admin/adminPage";
        }else{
            model.addAttribute("loginError","Login Error");
            return "adminLogin";
        }
    }

}
