package com.group4.carrental.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model, @RequestParam("action") String action,HttpSession session) {
	    System.out.println("abc");
	    if(action.equals("Logout"))
	    {
	    	session.invalidate();
	    	  return "redirect:login";
	    
	    }
		return action;
	  
			
	    
	    
	}

  
}