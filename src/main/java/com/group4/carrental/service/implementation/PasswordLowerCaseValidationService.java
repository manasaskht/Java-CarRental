package com.group4.carrental.service.implementation;

import com.group4.carrental.service.IPasswordValidationService;

public class PasswordLowerCaseValidationService implements IPasswordValidationService {


    @Override
    public String isValidPassword(String pwd, Integer ruleValue) {

        String lowerCase = ".*[a-z].*";
        boolean isValid =pwd.matches(lowerCase);
        if(!isValid)
        {
           return "password should contain atLeast one LowerCase character,";
        }
        return  "";

    }
}
