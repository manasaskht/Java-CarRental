package com.group4.carrental.service;
import com.group4.carrental.dao.AdminBlacklistCarsDAOMock;
import com.group4.carrental.dao.IAdminBlacklistCarsDAO;
import com.group4.carrental.service.implementation.AdminBlacklistCarsService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class AdminBlacklistCarsServiceTest {

    private IAdminBlacklistCarsDAO iAdminBlacklistCarsDAO;
    private ISendMailService  iSendMailService;
    private IUserSignUpService  iUserSignUpService;
    private IAdminBlacklistCarsService iAdminBlacklistCarsService;

    @Before
    public void setUp(){
        this.iAdminBlacklistCarsDAO = new AdminBlacklistCarsDAOMock();
        this.iAdminBlacklistCarsService=new AdminBlacklistCarsService(iAdminBlacklistCarsDAO,iSendMailService,iUserSignUpService);
    }

    @Test
    public void getBlacklistCarsTest(){

        boolean answer = this.iAdminBlacklistCarsDAO.getBlacklistCars().size()==2;
        assertTrue(answer);


        answer = this.iAdminBlacklistCarsDAO.getBlacklistCars().size()!=2;
        assertFalse(answer);
    }

    @Test
    public void updateCarStatus()
    {
       this.iAdminBlacklistCarsDAO.updateCarStatus(1,4);
       boolean answer=this.iAdminBlacklistCarsDAO.getBlacklistCars().get(0).getCarStatus()==4;
       assertTrue(answer);

       answer = this.iAdminBlacklistCarsDAO.getBlacklistCars().get(0).getCarStatus()!=4;
       assertFalse(answer);

    }

}
