package com.group4.carrental.controllers;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IEmailApproveReject;
import com.group4.carrental.service.IForgotPasswordService;
import com.group4.carrental.service.implementation.ForgotPasswordService;
import com.group4.carrental.service.ILoginService;
import com.group4.carrental.service.IUserSignUpService;

@Controller
public class ForgotPasswordController {
	
private ILoginService LoginService;

private IForgotPasswordService ForgotPasswordService;

private IEmailApproveReject EmailApproveReject;

@Autowired
public ForgotPasswordController(@Qualifier("LoginService") ILoginService LoginService,
                             @Qualifier("ForgotPasswordService") IForgotPasswordService ForgotPasswordService,@Qualifier("EmailApproveReject") IEmailApproveReject EmailApproveReject){
    this.LoginService=LoginService;
    this.ForgotPasswordService = ForgotPasswordService;
    this.EmailApproveReject=EmailApproveReject;
}

String encodeNewPassword;
    
 @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
public String ForgotPassword(Model model) {
 return "forgotPassword" ;
 }
	 
 @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
 public String ForgotPasswordprocess(Model model, @ModelAttribute("") User user, HttpSession session,HttpServletRequest request)
 {
	 if (!LoginService.isValidUserEmail(user.getEmail())) {
			model.addAttribute("Invalid_Email", "Please enter a valid email");
			
	 }
	 else if( !ForgotPasswordService.findUserByEmail(user))
		{
		 model.addAttribute("Unregistered_Email", "We didn't find an account for that e-mail address.");
		
		}
	 else
	 {
		 if(ForgotPasswordService.findUserByEmail(user))
		 {
		 user.setTokenID(UUID.randomUUID().toString());
		 ForgotPasswordService.addToken(user);
		 EmailApproveReject.sendToken(user.getEmail(),user.getTokenID(),request);
		 model.addAttribute("Success","Password reset link has been sent to " +user.getEmail());
		 }
		
	 }
 
	 return "forgotPassword";
 }
 @RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String displayResetPasswordPage(Model model,@ModelAttribute("User") User user) {
		if (ForgotPasswordService.validate(user)) { // Token found in DB
			return "resetPassword";
		} else { // Token not found in DB
			model.addAttribute("errorMessage"," This is an invalid password reset link.");
		}

		return "forgotPassword";
	} 
 @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public String setNewPassword(Model model,@ModelAttribute("User") User user) throws UnsupportedEncodingException {
		// Find the user associated with the reset token
		if( !ForgotPasswordService.isPasswordNull(user.getPassword())&& ForgotPasswordService.validatePassword(user.getPassword()))
		{
		
encodeNewPassword = ForgotPasswordService.getEncodedString(user.getPassword());
user.setPassword(encodeNewPassword);
ForgotPasswordService.updatePassword(user);
	            
			user.setTokenID(null);
			ForgotPasswordService.addToken(user);

			model.addAttribute("successMessage", "You have successfully reset your password.  You may now login.");
			return "login";
			
		} 
		
		else {
			model.addAttribute("error", "Oops!  This is an invalid password reset link.");
			return "resetPassword";
		}
		
		
		
}

 /* Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParams(MissingServletRequestParameterException ex) {
		return "login";
	}*/
}

