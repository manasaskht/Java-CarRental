package com.group4.carrental.service.implementation;

import com.group4.carrental.service.IPasswordValidationService;

public class PasswordSpecialCharacterService implements IPasswordValidationService {

    @Override
    public String isValidPassword(String pwd, Integer ruleValue) {

        String specialCharacter = "[a-zA-Z0-9 ]*";
        boolean isValid =pwd.matches(specialCharacter);
        if(isValid)
        {
            return "password should contain atLeast one special character,";
        }
        return "";

    }
}
