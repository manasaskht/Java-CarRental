package com.group4.carrental.service;
import javax.servlet.http.HttpServletRequest;
public interface IEmailApproveReject {
	
	public void sendApproveEmail(int carId);
	public void sendRejectEmail(int carId);
public void sendToken(String email,String string, HttpServletRequest request);
}
