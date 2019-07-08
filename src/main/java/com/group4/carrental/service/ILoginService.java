package com.group4.carrental.service;

import com.group4.carrental.model.User;

import java.io.UnsupportedEncodingException;

public interface ILoginService {

	boolean isEmptyPassword(String string);

	boolean isValidUserEmail(String string);

	int getUserId(User user);

	boolean isUserValid(User user) throws UnsupportedEncodingException;



}
