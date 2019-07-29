package com.group4.carrental.service;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import org.springframework.ui.Model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface IUserSignUpService {
    public void saveUserSignUpDetails(User user);
    public User getUserDetails(Integer userId);
    public void updateUserProfileDetails(User user);
    public ArrayList<City> getCityList();
    public boolean validUserName(String userName);
    public boolean validUserCity(Integer city);
    public boolean validUserEmail(String email);
    public boolean isEmailExist(String email);
    public boolean isEmailNull(String email);
    public boolean ispwdNull(String pwd);
    public boolean isPasswordMatch(String pwd,String confirmPwd);
    public String validPwd(String pwd);
    public boolean isConfirmPwdNull(String confirmPwd);
    public String getEncodedString(String originalString) throws UnsupportedEncodingException;
}
