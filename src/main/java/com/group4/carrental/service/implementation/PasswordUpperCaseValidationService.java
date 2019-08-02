package com.group4.carrental.service.implementation;

import com.group4.carrental.service.IPasswordValidationService;

public class PasswordUpperCaseValidationService implements IPasswordValidationService {

    @Override
    public String isValidPassword(String pwd, Integer ruleValue) {

        String specialCharacter = ".*[A-Z].*";
        boolean isValid =pwd.matches(specialCharacter);
        if(!isValid)
        {
            return "password should contain atLeast one upperCase character,";
        }
        return "";
    }
}
