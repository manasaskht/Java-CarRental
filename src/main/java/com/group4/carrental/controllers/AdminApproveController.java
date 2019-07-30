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

import com.group4.carrental.service.IAdminCarApproveService;

@Controller
public class AdminApproveController {
	
	@Autowired
	private IAdminCarApproveService admincarapproveservice;
	
	@RequestMapping(value = "/adminListPendingRequests", method = RequestMethod.GET)
	    public String listAllCar(Model model,HttpSession httpSession){
		int adminId = 0;
        try {
        	adminId = (int) httpSession.getAttribute("admin");
        }catch (NullPointerException exception){
            return "adminLogin";
        }
		  ArrayList<AdminCar> carArrayList = this.admincarapproveservice.getAllPendingRequests();
	        model.addAttribute("pendingCarRequests",carArrayList);
	        return "adminPendingRequests";
	    }
	
	@RequestMapping(value = "/admin/Approve/{id}", method = RequestMethod.GET)
	 public String approveResponse(Model model,@PathVariable int id,HttpSession httpSession)
	{
		int adminId = 0;
        try {
        	adminId = (int) httpSession.getAttribute("admin");
        }catch (NullPointerException exception){
            return "adminLogin";
        }
		this.admincarapproveservice.carApproval(id);
		return "redirect:/admin/ListPendingRequests";
	}
	@RequestMapping(value = "/admin/Reject/{id}", method = RequestMethod.GET)
	 public String rejectresponse(Model model,@PathVariable int id,HttpSession httpSession)
	{
		int adminId = 0;
        try {
        	adminId = (int) httpSession.getAttribute("admin");
        }catch (NullPointerException exception){
            return "adminLogin";
        }
	
		this.admincarapproveservice.carReject(id);
       return "redirect:/admin/ListPendingRequests";
	}
	

}
