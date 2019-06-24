package com.group4.carrental.service;

import com.group4.carrental.model.User;

import java.io.UnsupportedEncodingException;

public interface ILoginService {

	public boolean isEmptyPassword(String string);

	public boolean isValidUserEmail(String string);

	public int getUserId(User user);

	boolean isUserValid(User user) throws UnsupportedEncodingException;



}
