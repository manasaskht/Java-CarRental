package com.group4.carrental.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.group4.carrental.model.AdminCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.IAdminCarApproveService;
import org.springframework.beans.factory.annotation.Qualifier;
import com.group4.carrental.service.implementation.LoggerInstance;

@Controller
public class AdminApproveController {

	private IAdminCarApproveService admincarapproveservice;
	private LoggerInstance loggerInstance;
	@Autowired
	public AdminApproveController(@Qualifier("AdminCarApproveService")IAdminCarApproveService admincarapproveservice, LoggerInstance loggerInstance){
		this.admincarapproveservice = admincarapproveservice;
		this.loggerInstance = loggerInstance;
	}
	@RequestMapping(value = "/admin/ListPendingRequests", method = RequestMethod.GET)
	    public String listAllCar(Model model,HttpSession httpSession){
		loggerInstance.log(0,"Admin accessed list of pending requests page: Called");
		int adminId = 0;
        try {
        	adminId = (int) httpSession.getAttribute("admin");
        }catch (NullPointerException exception){
        	loggerInstance.log(0,"AdminApproveController Session error: Called"+exception.toString());
            return "adminLogin";
        }
		  ArrayList<AdminCar> carArrayList = this.admincarapproveservice.getAllPendingRequests();
	        model.addAttribute("pendingCarRequests",carArrayList);
	        return "adminPendingRequests";
	    }
	
	@RequestMapping(value = "/admin/Approve/{id}", method = RequestMethod.GET)
	 public String approveResponse(Model model,@PathVariable int id,HttpSession httpSession)
	{
		loggerInstance.log(0,"Admin accessed approve requests service: Called");
		int adminId = 0;
        try {
        	adminId = (int) httpSession.getAttribute("admin");
        }catch (NullPointerException exception){
        	loggerInstance.log(2,"AdminApproveController Session error: "+exception.toString());
            return "adminLogin";
        }
		this.admincarapproveservice.carApproval(id);
		return "redirect:/admin/ListPendingRequests";
	}
	@RequestMapping(value = "/admin/Reject/{id}", method = RequestMethod.GET)
	 public String rejectresponse(Model model,@PathVariable int id,HttpSession httpSession)
	{
		loggerInstance.log(0,"Admin accessed reject requests service: Called");
		int adminId = 0;
        try {
        	adminId = (int) httpSession.getAttribute("admin");
        }catch (NullPointerException exception){
        	loggerInstance.log(2,"AdminApproveController Session error: "+exception.toString());
            return "adminLogin";
        }
	
		this.admincarapproveservice.carReject(id);
       return "redirect:/admin/ListPendingRequests";
	}
	

}
