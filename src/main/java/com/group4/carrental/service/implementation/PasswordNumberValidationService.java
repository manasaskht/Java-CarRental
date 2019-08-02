package com.group4.carrental.service.implementation;

import com.group4.carrental.service.IPasswordValidationService;

public class PasswordNumberValidationService implements IPasswordValidationService {

    @Override
    public String isValidPassword(String pwd, Integer ruleValue) {

        String numberCharacter = ".*\\d.*";
        boolean isValid =pwd.matches(numberCharacter);
        if(!isValid)
        {
            return "password should contain atLeast one Number,";
        }
        return "";
    }
}
