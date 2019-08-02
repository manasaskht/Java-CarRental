package com.group4.carrental.service;

import com.group4.carrental.dao.AdminLoginDAOMock;
import com.group4.carrental.dao.IAdminLoginDAO;
import com.group4.carrental.model.Admin;
import com.group4.carrental.service.implementation.AdminLoginService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AdminLoginServiceTest {

    private IAdminLoginService adminLoginService;
    private IAdminLoginDAO adminLoginDAO;

    @Before
    public void setUp(){
        this.adminLoginDAO = new AdminLoginDAOMock();
        this.adminLoginService = new AdminLoginService(adminLoginDAO,mock(LoggerInstance.class));
    }

    @Test
    public void validateLoginTest(){
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("adminPassword");
        int adminIdToTest = 1;
        Admin adminToTest = this.adminLoginService.validateLogin(admin);
        assertEquals(adminToTest.getAdminId(),adminIdToTest);

    }

}
