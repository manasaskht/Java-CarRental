package com.group4.carrental.service.implementation;

import com.group4.carrental.service.IPaymentValidationService;
import org.springframework.stereotype.Service;

@Service("PaymentValidationService")
public class PaymentValidationService implements IPaymentValidationService {

    public  boolean isValidCreditCardNumber(String number)
    {

        int numLength=number.length()+1;
        boolean isvalid=(numLength>=13 && numLength<= 16) && (isValidprefix(number,4,1) || isValidprefix(number,5,1)
                || isValidprefix(number,37,2) || isValidprefix(number,6,1) );

        return isvalid;

    }

    public boolean isValidprefix(String number,long digit,int x ) {

        long prefxNumber=Long.parseLong(number.substring(0, x));
        boolean isvalidNumber=(prefxNumber==digit);
        return isvalidNumber;
    }

}

