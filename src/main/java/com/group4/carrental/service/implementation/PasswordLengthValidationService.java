package com.group4.carrental.service.implementation;

import com.group4.carrental.service.IPasswordValidationService;
import org.springframework.stereotype.Service;

@Service("PasswordValidationService")
public class PasswordLengthValidationService implements IPasswordValidationService {

    @Override
    public String isValidPassword(String pwd,Integer ruleValue) {

        boolean isValid=pwd.length()>=ruleValue;
        if(!isValid)
        {
            return "password length should atLeast 8 characters,";
        }
        return "";
    }
}
