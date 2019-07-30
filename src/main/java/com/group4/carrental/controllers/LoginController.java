package com.group4.carrental.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.model.User;
import com.group4.carrental.service.ILoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {

	private ILoginService LoginService;
	private LoggerInstance loggerInstance;

	@Autowired
	public LoginController(@Qualifier("LoginService") ILoginService LoginService , LoggerInstance loggerInstance){
		this.LoginService = LoginService;
		this.loggerInstance = loggerInstance;
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(Model model,HttpSession session) {

		if (null == session.getAttribute("user_id")){
			loggerInstance.log(0,"User accessed Login page: Called");
			return "login";
		}
		return "redirect:homePage";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(Model model, @ModelAttribute("user") User user, HttpSession session) throws UnsupportedEncodingException {

		if (LoginService.isUserValid(user)) {
			loggerInstance.log(0,"User login validation service: Called");
			int userId = LoginService.getUserId(user);
			session.setAttribute("user_id", userId);
			return "redirect:homePage";
		} else {
			if (!LoginService.isValidUserEmail(user.getEmail())&& !LoginService.isEmptyPassword(user.getPassword())) {
				loggerInstance.log(1, "User trying to log in with invalid email: Called");
				model.addAttribute("Invalid_Email", "Please enter a valid email");

			}
			if (!LoginService.isEmptyPassword(user.getPassword())&& LoginService.isValidUserEmail(user.getEmail()) ) {
				loggerInstance.log(1, "User trying to log in with password as empty: Called");
				model.addAttribute("Invalid_Password", "Please fill password");

			}
			if (LoginService.isEmptyPassword(user.getPassword())&& LoginService.isValidUserEmail(user.getEmail()) ) {
				loggerInstance.log(1, "User trying to log in with invalid credentials: Called");
				model.addAttribute("Invalid_Credentials", "Username/Password incorrect!!");

			}
			return "login";
		}

	}
}
