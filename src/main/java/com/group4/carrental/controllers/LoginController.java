package com.group4.carrental.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.group4.carrental.model.User;
import com.group4.carrental.service.ILoginService;

@Controller
public class LoginController {
	@Autowired
	private ILoginService LoginService;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String loginProcess(Model model,@ModelAttribute("user") User user, HttpSession session)
	 {		
	 
	 if (LoginService.isUserValid(user))
		   	   {
	   session.setAttribute("user_id", user.getUserID());
	   
		   return "carrent"; 
	   }
	 else
	 {
			if(!LoginService.isValidUserEmail(user.getEmailID())){
			model.addAttribute("Invalid_Email", "Please enter a valid email");
			 return "login";
		}
			else 
			{
				LoginService.isValidPassword(user.getPassword());
				model.addAttribute("Invalid_Password", "Username/Password incorrect!!");
				return "login";
			}
	 }	
		
	 }
	 }

		   
			 
		   
	

