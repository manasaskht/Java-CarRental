package com.group4.carrental.service;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IUserSignUpService {
    public void saveUserSignUpDetails(User user);
    public User getUserDetails(Integer userId);
    public void updateUserProfileDetails(User user);
    public ArrayList<City> getCityList();
    public HashMap<String,String> updateProfileFormValidation(User userData);
    public String getEncodedString(String originalString) throws UnsupportedEncodingException;
    public HashMap<String,String> signUpFormValidation(User userData);
}
