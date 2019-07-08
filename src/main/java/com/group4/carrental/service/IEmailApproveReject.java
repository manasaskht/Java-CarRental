package com.group4.carrental.service;

public interface IEmailApproveReject {
	
	public void sendApproveEmail(int carId);
	public void sendRejectEmail(int carId);

}
