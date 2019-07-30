package com.group4.carrental.controllers;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.group4.carrental.authentication.Authentication;
import com.group4.carrental.authentication.IAuthentication;
import com.group4.carrental.model.Password;
import com.group4.carrental.service.IUpdatePasswordService;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.LoggerService;
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

    private Authentication authentication = Authentication.getInstance();


    private IUpdatePasswordService  updatePasswordService;
    private LoggerInstance loggerInstance;

    public  UpdatePasswordController(@Qualifier("UpdatePasswordService") IUpdatePasswordService updatePasswordService,
                                     LoggerInstance loggerInstance){
        this.updatePasswordService = updatePasswordService;
        this.loggerInstance = loggerInstance;
    }


    @GetMapping("/updatePassword")
    public String updatePassword(HttpSession session){
        loggerInstance.log(0,"User update password: Called");

        if(authentication.isValidUserSession(session)){
            return  "updatepassword";
        }else {
            return "redirect:/login";
        }

    }
    @PostMapping(value ="/updatePassword" )
    public String saveUserPassword(@ModelAttribute("password")Password password, HttpSession session,Model model) throws UnsupportedEncodingException {
        loggerInstance.log(0,"User new password submit : called");


        if(authentication.isValidUserSession(session)){
            int userId = authentication.getUserId();
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
                return  "redirect:/homePage";

            }else {
                model.addAttribute("error", "Invalid old Password");
                return "updatepassword";
            }
        }else {
            return  "redirect:/login";
        }



    }
}
