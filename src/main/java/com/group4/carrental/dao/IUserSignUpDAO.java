package com.group4.carrental.dao;

import com.group4.carrental.model.Car;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;

import java.util.ArrayList;

public interface IUserSignUpDAO {

    public void saveUserSignUpDetails(User user);
    public User getUserDetails(Integer userId);
    public void updateUserProfileDetails(User user);
    public boolean isEmailExist(String email);
    public ArrayList<City> getCityList();

}
