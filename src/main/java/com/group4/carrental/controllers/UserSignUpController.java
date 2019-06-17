package com.group4.carrental.controllers;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class UserSignUpController {

    private IUserSignUpService iUserSignUpService;

    public UserSignUpController(@Qualifier("UserSignUpService") IUserSignUpService userSignUpService){
        this.iUserSignUpService = userSignUpService;
    }

    @RequestMapping("/userSignUp")
    public String userSignUp(Model model)
    {
        return "userSignUp";
    }

    @RequestMapping("/saveUserDetails")
    public String saveUserDetails(@ModelAttribute("user") User user)
    {
        iUserSignUpService.SaveUserSignUpDetails(user);
        return "HomePage";
    }


}
