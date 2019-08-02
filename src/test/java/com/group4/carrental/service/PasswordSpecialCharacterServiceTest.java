package com.group4.carrental.service;

import com.group4.carrental.service.implementation.PasswordLengthValidationService;
import com.group4.carrental.service.implementation.PasswordSpecialCharacterService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class PasswordSpecialCharacterServiceTest {

    IPasswordValidationService passwordValidationService;
    @Before
    public void setUp(){
        this.passwordValidationService= new PasswordSpecialCharacterService();
    }

    @Test
    public void isValidPasswordTest()
    {
        String pwd = "abc@123456";
        boolean answer = this.passwordValidationService.isValidPassword(pwd,8)=="";
        assertTrue(answer);

        pwd = "abcABC123";
        answer = this.passwordValidationService.isValidPassword(pwd,8)=="";
        assertFalse(answer);

        pwd = "abc1234ABC";
        answer = this.passwordValidationService.isValidPassword(pwd,8)=="password should contain atLeast one special character,";
        assertTrue(answer);

    }
}
