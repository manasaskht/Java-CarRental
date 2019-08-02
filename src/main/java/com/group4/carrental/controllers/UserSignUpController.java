package com.group4.carrental.controllers;
import com.group4.carrental.authentication.Authentication;
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
import java.util.HashMap;


@Controller
public class UserSignUpController
{

    @Autowired
    private LoggerInstance log;
    private IUserSignUpService iUserSignUpService;
    private Authentication authentication = Authentication.getInstance();

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

        log.log(0,"In controller:saveUserDetails method");
        HashMap<String,String> errorMsgMap= iUserSignUpService.signUpFormValidation(user);
        model.addAttribute("errorMsg",errorMsgMap);

        if(errorMsgMap.size()==0)
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
        if(authentication.isValidUserSession(httpSession)) {

            int user_id = (int) httpSession.getAttribute("user_id");
            ArrayList<City> cityArrayList = iUserSignUpService.getCityList();
            User userData = iUserSignUpService.getUserDetails(user_id);
            userData.setUserId(user_id);

            model.addAttribute("cityArrayList", cityArrayList);
            model.addAttribute("userData", userData);

            return "userUpdateProfile";
        }
        else
        {
            return "redirect:login";
        }
    }

    @PostMapping("/userUpdateProfile")
    public String userUpdateProfile(@ModelAttribute("user") User user, Model model,HttpSession httpSession) {

        if (authentication.isValidUserSession(httpSession)) {
            int user_id = (int) httpSession.getAttribute("user_id");
            user.setUserId(user_id);
            HashMap<String, String> errorMsgMap = iUserSignUpService.updateProfileFormValidation(user);
            model.addAttribute("userData", user);

            if (errorMsgMap == null) {
                iUserSignUpService.updateUserProfileDetails(user);
                model.addAttribute("errorMsg", errorMsgMap);
                return "redirect:/homePage";
            } else {
                log.log(0, "In controller:redirect to update profile page due to invalid information");
                ArrayList<City> cityArrayList = iUserSignUpService.getCityList();
                model.addAttribute("cityArrayList", cityArrayList);
                return "userUpdateProfile";
            }
        }
        else {
            return "redirect:login";
        }
    }

}
