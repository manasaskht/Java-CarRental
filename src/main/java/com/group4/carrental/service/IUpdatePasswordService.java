package com.group4.carrental.service;

import com.group4.carrental.model.Password;

import java.io.UnsupportedEncodingException;

public interface IUpdatePasswordService {


    public boolean isOldPasswordValid(int userId, Password password) throws UnsupportedEncodingException;
    public boolean validatePassword(String password);
    public void updatePassword(int userId, Password password);
    public boolean isPasswordNull(String password);
    public boolean isPasswordMatch(String password, String confirmPassword);
    public String getEncodedString(String originalString) throws UnsupportedEncodingException;



}
