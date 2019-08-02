package com.group4.carrental.service;

import com.group4.carrental.model.User;

import java.util.HashMap;

public interface ISignUpformRuleService {

    public HashMap<String,Integer> getRules();
    public boolean validUserName(String userName);
    public boolean validUserCity(Integer city);
    public boolean validUserEmail(String email);
    public boolean isEmailExist(String email);
    public boolean isEmailNull(String email);
    public boolean ispwdNull(String pwd);
    public boolean isPasswordMatch(String pwd,String confirmPwd);
    public boolean validPwd(String pwd);
    public String passwordValidation(String pwd);
    public boolean isConfirmPwdNull(String confirmPwd);


}
