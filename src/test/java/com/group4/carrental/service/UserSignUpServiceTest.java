package com.group4.carrental.service;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.dao.UserSignUpDAOMock;
import com.group4.carrental.service.implementation.UserSignUpService;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import com.group4.carrental.service.implementation.LoggerInstance;
import static org.mockito.Mockito.mock;
public class UserSignUpServiceTest {

    private IUserSignUpService userSignUpService;
    private IUserSignUpDAO userSignUpDAO;
    private ISignUpformRuleService iSignUpformRuleService;


    @Before
    public void setUp(){
        this.userSignUpDAO = new UserSignUpDAOMock();
        this.userSignUpService = new UserSignUpService(userSignUpDAO,iSignUpformRuleService,mock(LoggerInstance.class));
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
