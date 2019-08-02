package com.group4.carrental.service;

import com.group4.carrental.service.implementation.PasswordLengthValidationService;
import com.group4.carrental.service.implementation.PasswordNumberValidationService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PasswordNumberValidationServiceTest {

    IPasswordValidationService passwordValidationService;
    @Before
    public void setUp(){
        this.passwordValidationService= new PasswordNumberValidationService();
    }

    @Test
    public void isValidPasswordTest()
    {
        String pwd = "abc@123456";
        boolean answer = this.passwordValidationService.isValidPassword(pwd,1)=="";
        assertTrue(answer);

        pwd = "abcABC";
        answer = this.passwordValidationService.isValidPassword(pwd,1)=="";
        assertFalse(answer);

        pwd = "abc@";
        answer = this.passwordValidationService.isValidPassword(pwd,1)=="password should contain atLeast one Number,";
        assertTrue(answer);

    }
}
