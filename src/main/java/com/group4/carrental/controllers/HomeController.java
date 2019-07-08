package com.group4.carrental.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController implements ErrorController {

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
}
