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
import java.io.UnsupportedEncodingException;

@Controller
public class UpdatePasswordController {

    private static final String PASSWORD_ERROR = "Password must be atleast 8 characters long,\n" +
            "must include atleast 1 upperCase and 1 LowerCase character,\n" +
            "Password must include atleast one number and special character";
    private static final String PASSWORD_NOTMATCHED_ERROR = "password does not match";
    private static final String OLD_PASSWORD_ERROR = "Old Password does not matched";

    @Autowired
    IUpdatePasswordService  updatePasswordService;


    public  UpdatePasswordController(@Qualifier("UpdatePasswordService") IUpdatePasswordService updatePasswordService){
        this.updatePasswordService = updatePasswordService;
    }


    @GetMapping("/update-password")
    public String updatePassword(HttpSession session){

        int userId = 0;
        try {
           userId = (int) session.getAttribute("user_id");

        }catch (NullPointerException e){
            return  "redirect:/login";
        }

        return  "updatepassword";
    }
    @PostMapping(value ="/update-password" )
    public String saveUserPassword(@ModelAttribute("password")Password password, HttpSession session,Model model) throws UnsupportedEncodingException {

        int userId =0;
        try {
            userId = (int) session.getAttribute("user_id");
        }catch (NullPointerException e){
            return  "redirect:/login";
        }

        boolean isValidData = true;

        if(updatePasswordService.isPasswordNull(password.getNewPassword())){
            isValidData = false;
        }else if(!updatePasswordService.validatePassword(password.getNewPassword())){
            isValidData = false;
            model.addAttribute("newPasswordError", PASSWORD_ERROR);

        }

        if(updatePasswordService.isPasswordNull(password.getConfirmPassword())){
            isValidData = false;
        }else if(!updatePasswordService.isPasswordMatch(password.getNewPassword(),password.getConfirmPassword())){
            isValidData = false;
        }

        boolean isPasswordValid = updatePasswordService.isOldPasswordValid(userId,password);

        if(!isPasswordValid){
            isValidData = false;
            model.addAttribute("oldPassworderror",OLD_PASSWORD_ERROR);
            System.out.println("password does not matched");
        }

        System.out.println("is valid = "+isValidData);
        if(isValidData){

            String newPassword = password.getNewPassword();
            String encodeNewPassword = updatePasswordService.getEncodedString(newPassword);

            String confirmPassword = password.getConfirmPassword();
            String encodeConfirmPassword = updatePasswordService.getEncodedString(confirmPassword);

            password.setNewPassword(encodeNewPassword);
            password.setConfirmPassword(encodeConfirmPassword);
            updatePasswordService.updatePassword(userId,password);
            return  "redirect:homePage";

        }else {
            model.addAttribute("error", "Invalid old Password");
            return "updatepassword";
        }

    }
}
