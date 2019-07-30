package com.group4.carrental.controllers;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.group4.carrental.model.User;
import com.group4.carrental.service.IEmailApproveReject;
import com.group4.carrental.service.IForgotPasswordService;


@Controller
public class ForgotPasswordController {

private IForgotPasswordService ForgotPasswordService;

private IEmailApproveReject EmailApproveReject;
	private LoggerInstance log;
@Autowired
public ForgotPasswordController(
                             @Qualifier("ForgotPasswordService") IForgotPasswordService ForgotPasswordService,@Qualifier("EmailApproveReject") IEmailApproveReject EmailApproveReject,LoggerInstance log){

    this.ForgotPasswordService = ForgotPasswordService;
    this.EmailApproveReject=EmailApproveReject;
    this.log=log;
}

String encodeNewPassword;
    
 @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
public String ForgotPassword(Model model)
 {
	 log.log(0,"User forgotPassword page: Called");
 return "forgotPassword" ;
 }
	 
 @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
 public String ForgotPasswordprocess(Model model, @ModelAttribute("") User user, HttpSession session,HttpServletRequest request)
 {
	 if (!ForgotPasswordService.isValidUserEmail(user.getEmail())) {
			model.addAttribute("Invalid_Email", "Please enter a valid email");
		 log.log(1,"User trying to access forgot password with invalid email: Called");
	 }
	 else if( !ForgotPasswordService.findUserByEmail(user))
		{
		 model.addAttribute("Unregistered_Email", "We didn't find an account for that e-mail address.");
			log.log(1,"User trying to access forgot password with unregistered email: Called");
		}
	 else
	 {
		 if(ForgotPasswordService.findUserByEmail(user))
		 {
		 user.setTokenID(UUID.randomUUID().toString());
		 ForgotPasswordService.addToken(user);
		 EmailApproveReject.sendToken(user.getEmail(),user.getTokenID(),request);
		 model.addAttribute("Success","Password reset link has been sent to " +user.getEmail());
			 log.log(0,"Password reset link sent to registered email: Called");
		 }
		
	 }
 
	 return "forgotPassword";
 }
 @RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String displayResetPasswordPage(Model model,@ModelAttribute("User") User user) {
		if (ForgotPasswordService.validate(user)) {
			// Token found in DB
			log.log(0,"User resetPasword page: Called");
			return "resetPassword";
		} else { // Token not found in DB
			model.addAttribute("errorMessage"," This is an invalid password reset link.");
			log.log(1,"User accessing with invalid password reset link: Called");
			return "forgotPassword";
		}


	} 
 @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public String setNewPassword(Model model,@ModelAttribute("User") User user) throws UnsupportedEncodingException {
	 if (!ForgotPasswordService.isValidUserEmail(user.getEmail())) {
		 log.log(1,"User trying to access forgot password with Invalid email: Called");
		 model.addAttribute("Invalid_Email", "Please enter a valid email");


	 } else if (!ForgotPasswordService.findUserByEmail(user)) {
		 log.log(1,"User trying to access forgot password with Invalid email: Called");
		 model.addAttribute("Unregistered_Email", "We didn't find an account for that e-mail address.");

	 } else {
		 // Find the user associated with the reset token
		 if ((!ForgotPasswordService.isPasswordNull(user.getPassword())) && (ForgotPasswordService.validatePassword(user.getPassword()))) {
			 if ((!ForgotPasswordService.isConfirmPwdNull(user.getConfirmPassword())) &&
					 (ForgotPasswordService.isPasswordMatch(user.getPassword(), user.getConfirmPassword()))) {
				 encodeNewPassword = ForgotPasswordService.getEncodedString(user.getPassword());
				 user.setPassword(encodeNewPassword);
				 ForgotPasswordService.updatePassword(user);

				 user.setTokenID(null);
				 ForgotPasswordService.addToken(user);
				 log.log(0,"User with mail" +user.getEmail()+" Successfully reset their password:");
				 model.addAttribute("successMessage", "You have successfully reset your password.  You may now login.");
				 return "login";

			 }
			 log.log(1,"User trying to access forgot password with password mismatch: Called");
			 model.addAttribute("error_confirm_password", "Password and confirm password doesn't match.  You may try again.");

		 } else {
			 log.log(1,"User trying to access forgot password with not validpwd combinations: Called");
			 model.addAttribute("error_password", "Oops!  This is an invalid password ");

		 }



	 }

 /* Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParams(MissingServletRequestParameterException ex) {
		return "login";
	}*/
	 return "resetPassword"; }}

