package com.group4.carrental.service;
import com.group4.carrental.dao.BookCarDAOMock;
import com.group4.carrental.dao.IBookCarDAO;
import com.group4.carrental.service.implementation.BookCarService;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class BookCarServiceTest {

    private IBookCarService iBookCarService;
    private IUserSignUpService iUserSignUpService;
    private IPaymentValidationService iPaymentValidationService;
    private IBookCarDAO iBookCarDAO;
    private ICarRentService iCarRentService;
    private ISendMailService iSendMailService;

    @Before
    public void setUp(){
        this.iBookCarDAO= new BookCarDAOMock();
      this.iBookCarService= new BookCarService(iBookCarDAO,iUserSignUpService,iPaymentValidationService,iSendMailService,iCarRentService,mock(LoggerInstance.class));

    }

    @Test
    public void calculateTotalRentTest()
    {

        boolean answer = this.iBookCarService.calculateTotalRent("2019-07-25","2019-07-27",15)==30;
        assertTrue(answer);

        answer = this.iBookCarService.calculateTotalRent("2019-07-25","2019-07-27",15)==45;
        assertFalse(answer);
    }
}
