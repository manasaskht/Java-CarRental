package com.group4.carrental.dao;

import com.group4.carrental.model.User;

public interface ILoginDAO {
	public String getPassword(String email);
	public int getUserId(User user);
}
