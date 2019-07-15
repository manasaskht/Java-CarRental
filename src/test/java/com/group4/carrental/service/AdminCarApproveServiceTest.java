package com.group4.carrental.service;

import com.group4.carrental.dao.IAdminResponseDAO;
import com.group4.carrental.dao.AdminResponseDAOMock;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.model.Car;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.service.implementation.EmailApproveReject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
public class AdminCarApproveServiceTest {

private IAdminCarApproveService AdminCarApproveService;
private IAdminResponseDAO AdminResponseDAO;

@Before
    public void setUp() {
    this.AdminResponseDAO = new AdminResponseDAOMock();
    this.AdminCarApproveService = new AdminCarApproveServiceTest(AdminResponseDAO, mock(EmailApproveReject.class), mock(LoggerInstance.class));
}
@Test
public ArrayList<AdminCar> getAllPendingRequestsTest()
{
    AdminCar car1 = new AdminCar();
    car1.setCarDescription("Description");
    car1.setOwner_id(1);
    car1.setCarId(2);
    car1.setCarRate(12);
    car1.setCarModel("Abcd");
    car1.setCarStatus(4);
    car1.setCarOwnerName("abc");
    car1.setCarOwnerMail("manasa@dal.ca");
    this.AdminResponseDAO.getAllPendingRequests()
}
@Test
    public void carApprovalTest(int id)
    {
        id = 1;
        String answer = AdminResponseDAO.carApproval(id);
        assertTrue(answer);
}
