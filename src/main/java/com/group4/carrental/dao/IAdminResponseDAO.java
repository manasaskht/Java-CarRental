package com.group4.carrental.dao;

import java.util.ArrayList;

import com.group4.carrental.model.AdminCar;

public interface IAdminResponseDAO {
	public void carApproval (int id);
	public void carReject (int id);
	public String getEmail(int carId);
	public ArrayList<AdminCar> getAllPendingRequests(int status);

}
