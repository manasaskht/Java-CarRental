package com.group4.carrental.service;
import com.group4.carrental.service.implementation.PasswordLengthValidationService;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
public class PasswordLengthValidationServiceTest {

    IPasswordValidationService passwordValidationService;
    @Before
    public void setUp(){
        this.passwordValidationService= new PasswordLengthValidationService();
    }

    @Test
    public void isValidPasswordTest()
    {
        String pwd = "abc@123456";
        boolean answer = this.passwordValidationService.isValidPassword(pwd,8)=="";
        assertTrue(answer);

        pwd = "abc@123";
        answer = this.passwordValidationService.isValidPassword(pwd,8)=="";
        assertFalse(answer);

        pwd = "abc@1";
        answer = this.passwordValidationService.isValidPassword(pwd,8)=="password length should atLeast 8 characters,";
        assertTrue(answer);

    }
}
