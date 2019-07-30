package com.group4.carrental.dao;



public interface IForgotPasswordDAO {
	public String findUserByEmail(String email);
	public void addToken(String email,String Token);
	public String findUserByResetToken(String Email);
	public String validate(String Token);
	public void updatePassword(String email, String password);
	
}
