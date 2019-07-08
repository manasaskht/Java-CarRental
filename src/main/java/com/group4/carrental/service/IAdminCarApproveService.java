package com.group4.carrental.service;

import java.util.ArrayList;

import com.group4.carrental.model.AdminCar;

public interface IAdminCarApproveService {

	public ArrayList<AdminCar> getAllPendingRequests();
	 public void carApproval(int id);
	 public void carReject(int id);
}
