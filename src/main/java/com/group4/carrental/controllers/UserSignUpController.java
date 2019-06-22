package com.group4.carrental.controllers;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;


@Controller
public class UserSignUpController
{

    private IUserSignUpService iUserSignUpService;
    public UserSignUpController(@Qualifier("UserSignUpService") IUserSignUpService userSignUpService){
        this.iUserSignUpService = userSignUpService;
    }

    @GetMapping("/userSignUp")
    public String userSignUpPage(Model model)
    {
        ArrayList<City> cityArrayList=iUserSignUpService.getCityList();
        model.addAttribute("cityArrayList",cityArrayList);
        return "userSignUp";
    }

    @PostMapping("/userSignUp")
    public String saveUserDetails(@ModelAttribute("user") User user,Model model)
    {
        boolean isDataValid=true;

        if(!iUserSignUpService.validUserName(user.getName()))
        {
            model.addAttribute("nameError","please enter name");
            isDataValid=false;
        }
        if(!iUserSignUpService.validUserCity(user.getCity_id()))
        {
            model.addAttribute("cityError","please select city");
            isDataValid=false;
        }
        if(iUserSignUpService.isEmailNull(user.getEmail()))
        {
            model.addAttribute("emailError","please enter  email");
            isDataValid=false;
        }
        else
        {
            if(iUserSignUpService.isEmailExist(user.getEmail()))
            {
                model.addAttribute("emailError","This email id is already exist");
                isDataValid=false;
            }
            else
            {
               if(!iUserSignUpService.validUserEmail(user.getEmail()))
               {
                   model.addAttribute("emailError","please enter valid email");
                   isDataValid=false;
               }
            }

        }
        if(iUserSignUpService.ispwdNull(user.getPassword()))
        {
            model.addAttribute("pwdError","please enter Password");
            isDataValid=false;
        }
        else if (!iUserSignUpService.validPwd(user.getPassword()))
        {
            model.addAttribute("pwdError","Your password must be have at least\n" +
                    "\n" +
                    "8 characters long\n" +
                    "1 uppercase & 1 lowercase character\n" +
                    "1 number");
            isDataValid=false;
        }
        if(iUserSignUpService.isConfirmPwdNull(user.getConfirmPassword()))
        {
            model.addAttribute("confirmPwdError","please enter confirm password");
            isDataValid=false;
        }
        else if(!iUserSignUpService.isPasswordMatch(user.getPassword(),user.getConfirmPassword()))
        {
            model.addAttribute("confirmPwdError","passWord does not match");
            isDataValid=false;
        }

        if(isDataValid)
        {
            iUserSignUpService.saveUserSignUpDetails(user);
            return "redirect:/homePage";
        }
        else
        {
            ArrayList<City> cityArrayList=iUserSignUpService.getCityList();
            model.addAttribute("cityArrayList",cityArrayList);
            model.addAttribute("userData",user);
            return "userSignUp";
        }

    }

    @GetMapping("/userUpdateProfile")
    public String userUpdateProfilePage(Model model)
    {

        User userData=iUserSignUpService.getUserDetails(1);
        model.addAttribute("userData",userData);
        return "userUpdateProfile";
    }

    @PostMapping("/userUpdateProfile")
    public String userUpdateProfile(@ModelAttribute("user") User user, Model model)
    {

        iUserSignUpService.updateUserProfileDetails(user);
        model.addAttribute("userData",user);
        return "redirect:/HomePage";
    }

    @GetMapping("/homePage")
    public String homePage()
    {
       return "HomePage";
    }


}
