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

    @RequestMapping(value = "/update_password", method = RequestMethod.GET)
    public String updatePassword(Model model){


        return  "updatepassword";
    }

    /*@RequestMapping("/update_password/{userId}")
    public String updatePasswordwithId(@PathVariable String userId, Model model){

        String pass = updatePasswordService.getUserOldPassword(userId);
        model.addAttribute("pass",pass);


        return  "updatepassword";
    }*/

    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public ModelAndView saveUserPassword(@ModelAttribute("password")Password password, HttpSession session){

        ModelAndView modelAndView;

        String isPasswordValid = updatePasswordService.validateOldPassword("1",password);

        //String isPasswordValid = updatePasswordService.validateOldPassword(String.valueOf(session.getAttribute("username")),password);
        System.out.println("message" +isPasswordValid);

        if (isPasswordValid.equals("SUCCESS")) {
            updatePasswordService.updatePassword(password);
            modelAndView = new ModelAndView("redirect:/carrent");

        }else {
            modelAndView =new ModelAndView("updatepassword");
            modelAndView.addObject("error", "Invalid old Password");
        }

        return modelAndView;
    }
}
