package com.group4.carrental.service;

import com.group4.carrental.service.implementation.PasswordLengthValidationService;
import com.group4.carrental.service.implementation.PasswordLowerCaseValidationService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PasswordLowerCaseValidationServiceTest {

    IPasswordValidationService passwordValidationService;
    @Before
    public void setUp(){
        this.passwordValidationService= new PasswordLowerCaseValidationService();
    }

    @Test
    public void isValidPasswordTest()
    {
        String pwd = "abc@123456";
        boolean answer = this.passwordValidationService.isValidPassword(pwd,1)=="";
        assertTrue(answer);

        pwd = "ABC@1234";
        answer = this.passwordValidationService.isValidPassword(pwd,1)=="";
        assertFalse(answer);

        pwd = "ABC@1234";
        answer = this.passwordValidationService.isValidPassword(pwd,1)=="password should contain atLeast one LowerCase character,";
        assertTrue(answer);

    }
}
