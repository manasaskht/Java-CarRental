package com.group4.carrental.service;

import com.group4.carrental.dao.ForgotPasswordDAOMock;
import com.group4.carrental.dao.IForgotPasswordDAO;
import com.group4.carrental.model.User;
import com.group4.carrental.service.implementation.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class ForgotPasswordServiceTest {

    private IForgotPasswordService ForgotPasswordService;
    private IForgotPasswordDAO ForgotPasswordDAO;

    @Before
    public void setUp(){
        this.ForgotPasswordDAO=new ForgotPasswordDAOMock();
        this.ForgotPasswordService = new ForgotPasswordService(mock(UserSignUpService.class),ForgotPasswordDAO,mock(LoginService.class),mock(LoggerInstance.class),mock(SignUpformRuleService.class));
    }
    @Test

    public void findUserByEmailTest()
    {
        String userEmail="mana@gmail.com";
        User user = new User();
        user.setEmail(userEmail);
        Boolean answer= ForgotPasswordService.findUserByEmail(user);
        assertTrue(answer);
        userEmail="dlf@gmail.com";
        user.setEmail(userEmail);
         answer= ForgotPasswordService.findUserByEmail(user);
        assertFalse(answer);
        userEmail="";
        user.setEmail(userEmail);
        answer= ForgotPasswordService.findUserByEmail(user);
        assertFalse(answer);
    }
    
    @Test
    public void findUserByResetTokenTest()
    {
    	String userEmail="mana@gmail.com";
    	String userToken="d734567qki";
    	 User user = new User();
         user.setEmail(userEmail);
         user.setTokenID(userToken);
        boolean answer=ForgotPasswordService.findUserByResetToken(user);
        assertTrue(answer);
        
        userEmail="mana@gmail.com";
        userToken="123ggyu";
        user.setEmail(userEmail);
        user.setTokenID(userToken);
        answer=ForgotPasswordService.findUserByResetToken(user);
      assertFalse(answer);
       
        userEmail="";
        userToken="123ggyu";
        user.setEmail(userEmail);
        user.setTokenID(userToken);
        answer=ForgotPasswordService.findUserByResetToken(user);
      assertFalse(answer);
        
        userEmail="mana@gmail.com";
        userToken="";
        user.setEmail(userEmail);
        user.setTokenID(userToken);
        answer=ForgotPasswordService.findUserByResetToken(user);
      assertFalse(answer);
    }
    @Test
    public void validateTokenTest()
    {
    	String userToken="d734567qki";
    	User user = new User();
    	user.setTokenID(userToken);
        boolean answer= ForgotPasswordService.validate(user);
        assertTrue(answer);
        
        userToken="dlfzdfijo";
        user.setTokenID(userToken);
         answer= ForgotPasswordService.validate(user);
        assertFalse(answer);
        
        userToken="";
        user.setTokenID(userToken);
         answer= ForgotPasswordService.validate(user);
        assertFalse(answer);
        
    }
}
    
