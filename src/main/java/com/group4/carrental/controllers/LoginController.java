package com.group4.carrental.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group4.carrental.model.User;
import com.group4.carrental.service.ILoginService;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {
	@Autowired
	private ILoginService LoginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(Model model, @ModelAttribute("user") User user, HttpSession session) throws UnsupportedEncodingException {

		if (LoginService.isUserValid(user)) {
			int userId = LoginService.getUserId(user);
			session.setAttribute("user_id", userId);			
			return "redirect:homePage";
		} else {
			if (!LoginService.isValidUserEmail(user.getEmail())) {
				model.addAttribute("Invalid_Email", "Please enter a valid email");
				return "login";
			} else {
				LoginService.isValidPassword(user.getPassword());
				model.addAttribute("Invalid_Password", "Username/Password incorrect!!");
				return "login";
			}
		}

	}
}
