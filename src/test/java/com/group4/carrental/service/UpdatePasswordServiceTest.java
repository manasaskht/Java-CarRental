package com.group4.carrental.service;

import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.dao.UpdatePasswordDAOMock;
import com.group4.carrental.model.Password;
import com.group4.carrental.model.User;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.SignUpformRuleService;
import com.group4.carrental.service.implementation.UpdatePasswordService;
import com.group4.carrental.service.implementation.UserSignUpService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class UpdatePasswordServiceTest {

    IUpdatePasswordDAO updatePasswordDAO;
    IUpdatePasswordService updatePasswordService;
    ArrayList<User> userArrayList;


    @Before
    public void setUp(){
        this.updatePasswordDAO = new UpdatePasswordDAOMock();
        this.updatePasswordService = new UpdatePasswordService(updatePasswordDAO,
                mock(UserSignUpService.class),mock(LoggerInstance.class),mock(SignUpformRuleService.class));

    }


    @Test
    public void getOldPasswordTest(){
        int userId = 1;
        String userPassword = "abc@123";

        String getPassword = updatePasswordDAO.getUserOldPassword(userId);

        assertTrue(getPassword.equals(userPassword));

        userPassword = "123";

        getPassword = updatePasswordDAO.getUserOldPassword(userId);
        assertFalse(getPassword.equals(userPassword));


    }

    @Test
    public void updatePasswordTest(){
        Password password = new Password();
        int userId = 1;
        String newPassword = "123@abc";
        password.setNewPassword(newPassword);

        //update User password
        updatePasswordDAO.updatePassword(userId,password);

        //get User password
        String getOldPassword = updatePasswordDAO.getUserOldPassword(userId);

        assertTrue(getOldPassword.equals(newPassword));

        newPassword = "456";
        assertFalse(getOldPassword.equals(newPassword));

    }
}
