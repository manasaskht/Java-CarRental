package com.group4.carrental.service;


import com.group4.carrental.service.implementation.PaymentValidationService;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class PaymentValidationServiceTest  {


    private IPaymentValidationService iPaymentValidationService;

    @Before
    public void setUp() {

        this.iPaymentValidationService = new PaymentValidationService();
    }

    @Test
    public void isValidCreditCardNumberTest()
    {
        //Card number length=13, first digit=4
        String number = "456789101111";
        boolean answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertTrue(answer);

        //Card number length=16, first digit=4
        number = "456789101111212";
        answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertTrue(answer);

        //Card number length=14, first digit=5
        number = "5467891011112";
        answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertTrue(answer);

        //Card number length=14, first two digit=37
        number = "37567891011112";
        answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertTrue(answer);

        //Card number length=14, first digit=6
        number = "67567891011112";
        answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertTrue(answer);

        //Card number length=14, first digit=1 (invalid card Number)
        number = "1728929202093837";
        answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertFalse(answer);

        //Card number length=10, first digit=1 (invalid card Number)
        number = "1728929202";
        answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertFalse(answer);

        //Card number length=20, first digit=1 (invalid card Number)
        number = "1728929202093837123456";
        answer = this.iPaymentValidationService.isValidCreditCardNumber(number);
        assertFalse(answer);


    }
}
