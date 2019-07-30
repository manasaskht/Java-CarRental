package com.group4.carrental.service;


import com.group4.carrental.dao.ILoginDAO;
import com.group4.carrental.dao.LoginDAOMock;

import com.group4.carrental.service.implementation.UserSignUpService;
import org.junit.Before;
import org.junit.Test;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.LoginService;
import static junit.framework.TestCase.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;


public class LoginServiceTest {

    private ILoginService LoginService;
    private ILoginDAO LoginDAO;

    @Before
    public void setUp(){
      this.LoginDAO=new LoginDAOMock();
      this.LoginService = new LoginService(LoginDAO,mock(UserSignUpService.class),mock(LoggerInstance.class));
    }
    @Test
    public void isValidUserEmailTest()
    {
        String email="abc@gmail.com";
        boolean answer = this.LoginService.isValidUserEmail(email);
        assertTrue(answer);
        email ="abc";
        answer = this.LoginService.isValidUserEmail(email);
        assertFalse(answer);
        email ="";
        answer = this.LoginService.isValidUserEmail(email);
        assertFalse(answer);

    }
    @Test
    public void isEmptyPasswordTest()
    {
        String password ="Dec@0307";
       boolean answer = this.LoginService.isEmptyPassword(password);
        assertTrue(answer);
         password ="";
        answer = this.LoginService.isEmptyPassword(password);
        assertFalse(answer);

    }
    @Test
    public void isUserValidTest()
    { 
        String userEmail="abc@gmail.com";
        String userPassword="Dec@0307";
        String getPassword= LoginDAO.getPassword(userEmail);
        assertTrue(getPassword.equals(userPassword));
        userEmail="abc@gmail.com";
        userPassword="Mon@0306";
        getPassword= LoginDAO.getPassword(userEmail);
        assertFalse(getPassword.equals(userPassword));
        

    }


}
