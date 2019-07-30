package com.group4.carrental.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }


    @PostMapping("/admin/logout")
    public String adminLogout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/login";
    }
}