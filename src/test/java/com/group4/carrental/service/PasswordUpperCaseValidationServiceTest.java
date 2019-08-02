package com.group4.carrental.service;

import com.group4.carrental.service.implementation.PasswordLengthValidationService;
import com.group4.carrental.service.implementation.PasswordUpperCaseValidationService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PasswordUpperCaseValidationServiceTest {

    IPasswordValidationService passwordValidationService;
    @Before
    public void setUp(){
        this.passwordValidationService= new PasswordUpperCaseValidationService();
    }

    @Test
    public void isValidPasswordTest()
    {
        String pwd = "abcXYZ@123456";
        boolean answer = this.passwordValidationService.isValidPassword(pwd,1)=="";
        assertTrue(answer);

        pwd = "abc@1234";
        answer = this.passwordValidationService.isValidPassword(pwd,1)=="";
        assertFalse(answer);

        pwd = "abc@1234";
        answer = this.passwordValidationService.isValidPassword(pwd,1)=="password should contain atLeast one upperCase character,";
        assertTrue(answer);

    }
}
