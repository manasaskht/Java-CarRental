package com.group4.carrental.dao;

import com.group4.carrental.model.User;

public interface ILoginDAO {
	String getPassword(String email);
	int getUserId(User user);
}
