package com.group4.carrental.controllers;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IUserSignUpService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;



@Controller
public class UserSignUpController
{

    @Autowired
    private LoggerInstance log;

    private IUserSignUpService iUserSignUpService;
    public UserSignUpController(@Qualifier("UserSignUpService") IUserSignUpService userSignUpService){
        this.iUserSignUpService = userSignUpService;
    }

    @GetMapping("/userSignUp")
    public String userSignUpPage(Model model)
    {
        log.log(0,"In controller:load userSign up page");
        ArrayList<City> cityArrayList=iUserSignUpService.getCityList();
        model.addAttribute("cityArrayList",cityArrayList);
        return "userSignUp";
    }

    @PostMapping("/userSignUp")
    public String saveUserDetails(@ModelAttribute("user") User user,Model model) throws UnsupportedEncodingException
    {
        boolean isDataValid=true;
        log.log(0,"In controller:saveUserDetails method");

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
        else if (iUserSignUpService.validPwd(user.getPassword()).length()>0)
        {
            model.addAttribute("pwdError",iUserSignUpService.validPwd(user.getPassword()));
            isDataValid=false;
        }
        if(iUserSignUpService.isConfirmPwdNull(user.getConfirmPassword()))
        {
            model.addAttribute("confirmPwdError","please enter confirm password");
            isDataValid=false;
        }
        else if(!iUserSignUpService.isPasswordMatch(user.getPassword(),user.getConfirmPassword()))
        {
            model.addAttribute("confirmPwdError","password does not match");
            isDataValid=false;
        }

        if(isDataValid)
        {
            log.log(0,"In controller:check all user data in valid in saveUserDetails method and redirect to home page");
            String password=user.getPassword();
            String encodedPassWord = iUserSignUpService.getEncodedString(password);
            user.setPassword(encodedPassWord);
            iUserSignUpService.saveUserSignUpDetails(user);
            return "redirect:/homePage";
        }
        else
        {
            log.log(0,"In controller:invalid user details redirect user to signUp page");
            ArrayList<City> cityArrayList=iUserSignUpService.getCityList();
            model.addAttribute("cityArrayList",cityArrayList);
            model.addAttribute("userData",user);
            return "userSignUp";
        }

    }

    @GetMapping("/userUpdateProfile")
    public String userUpdateProfile(Model model, HttpSession httpSession)
    {
        log.log(0,"In controller:saveUserDetails method");
        int user_id = 0;
        try {
            user_id = (int) httpSession.getAttribute("user_id");
        }catch (NullPointerException exception){
            log.log(2,"In controller:user is not logged in and redirect to login page");
            return "redirect:login";
        }
        ArrayList<City> cityArrayList=iUserSignUpService.getCityList();
        model.addAttribute("cityArrayList",cityArrayList);
        User userData=iUserSignUpService.getUserDetails(user_id);
        userData.setUserId(user_id);
        model.addAttribute("userData",userData);
        return "userUpdateProfile";
    }

    @PostMapping("/userUpdateProfile")
    public String userUpdateProfile(@ModelAttribute("user") User user, Model model,HttpSession httpSession)
    {

        int user_id = 0;
        try {
            user_id = (int) httpSession.getAttribute("user_id");
        }catch (NullPointerException exception){
            log.log(2,"In controller:user is not logged in and redirect to login page");
            return "redirect:login";
        }
        boolean isValid=true;
        if(!iUserSignUpService.validUserName(user.getName()))
        {
            model.addAttribute("nameUpdateError","please enter name");
            isValid=false;
        }
        if(!iUserSignUpService.validUserCity(user.getCity_id()))
        {
            model.addAttribute("cityUpdateError","please select city");
            isValid=false;
        }
        user.setUserId(user_id);
        if(isValid)
        {

            iUserSignUpService.updateUserProfileDetails(user);
            iUserSignUpService.updateUserProfileDetails(user);
            model.addAttribute("userData",user);
            return "redirect:/homePage";
        }
        else
        {
            log.log(0,"In controller:redirect to update profile page due to invalid information");
            ArrayList<City> cityArrayList=iUserSignUpService.getCityList();
            model.addAttribute("cityArrayList",cityArrayList);
            model.addAttribute("userData",user);
            return "userUpdateProfile";
        }

    }


}
