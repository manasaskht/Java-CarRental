package com.group4.carrental.service;

import com.group4.carrental.dao.ISignUpFormRuleDAO;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.dao.SignUpFormRuleDAOMock;
import com.group4.carrental.dao.UserSignUpDAOMock;
import com.group4.carrental.service.implementation.LoggerInstance;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import com.group4.carrental.service.implementation.SignUpformRuleService;
import org.junit.Before;
import org.junit.Test;

public class SignUpformRuleServiceTest {

    private ISignUpFormRuleDAO iSignUpFormRuleDAO;
    private LoggerInstance log;
    private IUserSignUpDAO iUserSignUpDAO;
    private IPasswordValidationService iPasswordValidationService;
    private ISignUpformRuleService iSignUpformRuleService;


    @Before
    public void setUp(){
        this.iUserSignUpDAO = new UserSignUpDAOMock();
        this.iSignUpFormRuleDAO= new SignUpFormRuleDAOMock();
        this.iSignUpformRuleService=new SignUpformRuleService(iSignUpFormRuleDAO,iUserSignUpDAO,mock(LoggerInstance.class));
    }

    @Test
    public void validUserNameTest()
    {
        String name = "testName";
        boolean answer = this.iSignUpformRuleService.validUserName(name);
        assertTrue(answer);

        name = "";
        answer = this.iSignUpformRuleService.validUserName(name);
        assertFalse(answer);
    }

    @Test
    public void validUserCityTest()
    {
        int cityId =1;
        boolean answer = this.iSignUpformRuleService.validUserCity(cityId);
        assertTrue(answer);

        cityId =6;
        answer = this.iSignUpformRuleService.validUserCity(cityId);
        assertFalse(answer);
    }
    @Test
    public void validUserEmailTest()
    {
        String email ="abc@dal.ca";
        boolean answer = this.iSignUpformRuleService.validUserEmail(email);
        assertTrue(answer);

        email ="abc";
        answer = this.iSignUpformRuleService.validUserEmail(email);
        assertFalse(answer);

        email ="abc@dal";
        answer = this.iSignUpformRuleService.validUserEmail(email);
        assertFalse(answer);

        email ="abc@dal.c";
        answer = this.iSignUpformRuleService.validUserEmail(email);
        assertFalse(answer);

    }
    @Test
    public void isEmailExistTest()
    {
        String email = "abc@dal.ca";
        boolean answer = this.iSignUpformRuleService.isEmailExist(email);
        assertTrue(answer);

        email = "mno@dal.ca";
        answer = this.iSignUpformRuleService.isEmailExist(email);
        assertFalse(answer);
    }
    @Test
    public void isEmailNullTest()
    {
        String email = "";
        boolean answer = this.iSignUpformRuleService.isEmailNull(email);
        assertTrue(answer);

        email = "abc@dal.ca";
        answer = this.iSignUpformRuleService.isEmailNull(email);
        assertFalse(answer);
    }
    @Test
    public void ispwdNullTest()
    {
        String pwd = "";
        boolean answer = this.iSignUpformRuleService.ispwdNull(pwd);
        assertTrue(answer);

        pwd = "abc@dal.ca";
        answer = this.iSignUpformRuleService.ispwdNull(pwd);
        assertFalse(answer);

    }
    @Test
    public void isPasswordMatchTest()
    {
        String pwd="abc";
        String confirmPwd="abc";

        boolean answer = this.iSignUpformRuleService.isPasswordMatch(pwd,confirmPwd);
        assertTrue(answer);

        confirmPwd = "abcd";
        answer = this.iSignUpformRuleService.isPasswordMatch(pwd,confirmPwd);
        assertFalse(answer);
    }

    @Test
    public void isConfirmPwdNullTest()
    {
        String confirmPwd = "";
        boolean answer = this.iSignUpformRuleService.isConfirmPwdNull(confirmPwd);
        assertTrue(answer);

        confirmPwd = "abc@dal.ca";
        answer = this.iSignUpformRuleService.isConfirmPwdNull(confirmPwd);
        assertFalse(answer);
    }

}
