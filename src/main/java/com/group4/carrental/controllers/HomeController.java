package com.group4.carrental.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("")
    public String getHome(){
        return "redirect:homePage";
    }

    @GetMapping("/homePage")
    public String getHomePage(HttpSession session){
        int userId = 0;
        try {
            userId = (int) session.getAttribute("user_id");
            return "homePage";
        }catch (NullPointerException exception){
            return "redirect:login";
        }
    }

}
