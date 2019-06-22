package com.group4.carrental.service;

import com.group4.carrental.model.User;

public interface ILoginService {

    public boolean isValidPassword(String string);

	public boolean isValidUserEmail(String string);
  

	

	//boolean isUserValid(String emailID, String password);

	boolean isUserValid(User user);



}
