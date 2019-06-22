package com.group4.carrental.controllers;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.group4.carrental.model.Password;
import com.group4.carrental.service.IUpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UpdatePasswordController {

    @Autowired
    IUpdatePasswordService  updatePasswordService;

    public  UpdatePasswordController(@Qualifier("UpdatePasswordService") IUpdatePasswordService updatePasswordService){
        this.updatePasswordService = updatePasswordService;
    }


    @GetMapping("/update_password")
    public String updatePassword(){
        return  "updatepassword";
    }


    @PostMapping(value ="/update_password" )
    public String saveUserPassword(@ModelAttribute("password")Password password, HttpSession session,Model model){

        ModelAndView modelAndView;

        boolean isPasswordValid = updatePasswordService.validateOldPassword("1",password);

        //String isPasswordValid = updatePasswordService.validateOldPassword(String.valueOf(session.getAttribute("username")),password);
        System.out.println("message" +isPasswordValid);

        if (isPasswordValid) {
            updatePasswordService.updatePassword(password);
            return  "redirect:/carrent";

        }else {
            model.addAttribute("error", "Invalid old Password");
            return "updatepassword";
        }
    }
}
