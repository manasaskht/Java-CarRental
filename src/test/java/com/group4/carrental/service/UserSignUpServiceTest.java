package com.group4.carrental.service;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.dao.UserSignUpDAOMock;
import com.group4.carrental.service.implementation.UserSignUpService;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class UserSignUpServiceTest {

    private IUserSignUpService userSignUpService;
    private IUserSignUpDAO userSignUpDAO;


    @Before
    public void setUp(){
        this.userSignUpDAO = new UserSignUpDAOMock();
        this.userSignUpService = new UserSignUpService(userSignUpDAO);
    }


    @Test
    public void validUserNameTest()
    {
        String name = "testName";
        boolean answer = this.userSignUpService.validUserName(name);
        assertTrue(answer);

        name = "";
        answer = this.userSignUpService.validUserName(name);
        assertFalse(answer);
    }

    @Test
    public void validUserCityTest()
    {
        int cityId =1;
        boolean answer = this.userSignUpService.validUserCity(cityId);
        assertTrue(answer);

        cityId =6;
        answer = this.userSignUpService.validUserCity(cityId);
        assertFalse(answer);
    }
    @Test
    public void validUserEmailTest()
    {
        String email ="abc@dal.ca";
        boolean answer = this.userSignUpService.validUserEmail(email);
        assertTrue(answer);

        email ="abc";
        answer = this.userSignUpService.validUserEmail(email);
        assertFalse(answer);

        email ="abc@dal";
        answer = this.userSignUpService.validUserEmail(email);
        assertFalse(answer);

        email ="abc@dal.c";
        answer = this.userSignUpService.validUserEmail(email);
        assertFalse(answer);

    }
    @Test
    public void isEmailExistTest()
    {
        String email = "abc@dal.ca";
        boolean answer = this.userSignUpService.isEmailExist(email);
        assertTrue(answer);

        email = "mno@dal.ca";
        answer = this.userSignUpService.isEmailExist(email);
        assertFalse(answer);
    }
    @Test
    public void isEmailNullTest()
    {
        String email = "";
        boolean answer = this.userSignUpService.isEmailNull(email);
        assertTrue(answer);

        email = "abc@dal.ca";
        answer = this.userSignUpService.isEmailNull(email);
        assertFalse(answer);
    }
    @Test
    public void ispwdNullTest()
    {
        String pwd = "";
        boolean answer = this.userSignUpService.ispwdNull(pwd);
        assertTrue(answer);

        pwd = "abc@dal.ca";
        answer = this.userSignUpService.ispwdNull(pwd);
        assertFalse(answer);

    }
    @Test
    public void isPasswordMatchTest()
    {
      String pwd="abc";
      String confirmPwd="abc";

        boolean answer = this.userSignUpService.isPasswordMatch(pwd,confirmPwd);
        assertTrue(answer);

        confirmPwd = "abcd";
        answer = this.userSignUpService.isPasswordMatch(pwd,confirmPwd);
        assertFalse(answer);
    }
    @Test
    public void validPwdTest()
    {
        String pwd="Dec@0307";

        boolean answer = this.userSignUpService.validPwd(pwd);
        assertTrue(answer);

        //pwd without upperCase letter
        pwd = "dec@0307";
        answer = this.userSignUpService.validPwd(pwd);
        assertFalse(answer);

        //pwd without special characters
        pwd = "Dec0307";
        answer = this.userSignUpService.validPwd(pwd);
        assertFalse(answer);

        //pwd without number
        pwd = "December@";
        answer = this.userSignUpService.validPwd(pwd);
        assertFalse(answer);

        //pwd which is less than 8 characters
        pwd = "De@0307";
        answer = this.userSignUpService.validPwd(pwd);
        assertFalse(answer);


    }
    @Test
    public void isConfirmPwdNullTest()
    {
        String confirmPwd = "";
        boolean answer = this.userSignUpService.isConfirmPwdNull(confirmPwd);
        assertTrue(answer);

        confirmPwd = "abc@dal.ca";
        answer = this.userSignUpService.isConfirmPwdNull(confirmPwd);
        assertFalse(answer);
    }
    @Test
    public void getEncodedStringTest() throws UnsupportedEncodingException
    {
    String originalString="Feb20307";
    String encodedString="RmViMjAzMDc=";
    String output="";
    output=this.userSignUpService.getEncodedString(originalString);
    boolean answer=encodedString.equals(output);
    assertTrue(answer);

    encodedString="RmViMjAzMDc";
    output=this.userSignUpService.getEncodedString(originalString);
    answer=encodedString.equals(output);
    assertFalse(answer);
    }

}
