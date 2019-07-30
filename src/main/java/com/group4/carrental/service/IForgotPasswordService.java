package com.group4.carrental.service;
import java.io.UnsupportedEncodingException;

import com.group4.carrental.model.User;


public interface IForgotPasswordService {
	public boolean findUserByEmail(User user);
	public boolean findUserByResetToken(User user);
	public boolean validate(User user);
	public void addToken(User user);
	boolean isPasswordNull(String password);
	String getEncodedString(String originalString) throws UnsupportedEncodingException;
	boolean validatePassword(String password);
	public void updatePassword(User user);
	public boolean isPasswordMatch(String password,String confirm_password);
	public boolean isConfirmPwdNull(String confirm_password);
	public boolean isValidUserEmail(String email);

	
	

}
