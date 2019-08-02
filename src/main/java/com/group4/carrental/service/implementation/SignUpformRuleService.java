package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ISignUpFormRuleDAO;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.dao.implementation.SignUpFormRuleDAO;
import com.group4.carrental.model.City;
import com.group4.carrental.service.IPasswordValidationService;
import com.group4.carrental.service.ISignUpformRuleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("SignUpformRuleService")
public class SignUpformRuleService implements ISignUpformRuleService {

    private ISignUpFormRuleDAO iSignUpFormRuleDAO;
    private LoggerInstance log;
    private IUserSignUpDAO iUserSignUpDAO;
    private IPasswordValidationService iPasswordValidationService;

    public SignUpformRuleService(@Qualifier("SignUpFormRuleDAO") ISignUpFormRuleDAO signUpFormRuleDAO,
                                 @Qualifier("UserSignUpDAO") IUserSignUpDAO userSignUpDAO,
                                 LoggerInstance loggerInstance){
        this.iSignUpFormRuleDAO = signUpFormRuleDAO;
        this.iUserSignUpDAO = userSignUpDAO;
        this.log = loggerInstance;
    }

    @Override
    public HashMap<String, Integer> getRules() {
     return iSignUpFormRuleDAO.getRules();
    }
    @Override
    public boolean validUserName(String userName) {

        log.log(0,"In service:validate userName");
        if(userName!=null && !userName.trim().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public boolean validUserCity(Integer cityId) {

        log.log(0,"In service:validate city");

        ArrayList<City> cityArrayList = iUserSignUpDAO.getCityList();
        for (City city : cityArrayList) {
            if (city.getCityId()== cityId) {
                return true;
            }
        }
        return false;

    }
    @Override
    public boolean isEmailNull(String email) {
        log.log(0,"In service:validate email address");
        if(email!=null && !email.trim().isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    @Override
    public boolean validUserEmail(String email)
    {
        String regexString = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regexString);
    }

    @Override
    public boolean isEmailExist(String email) {

        if(iUserSignUpDAO.isEmailExist(email))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public boolean ispwdNull(String pwd)
    {
        if(pwd!=null && !pwd.trim().isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    @Override
    public String passwordValidation(String pwd) {

        String errorMessage="";
        boolean isValid;
        HashMap<String,Integer> rulesList= iSignUpFormRuleDAO.getRules();

        for (Map.Entry<String, Integer> entry : rulesList.entrySet()) {
            if(entry.getKey().equals("password_length"))
            {
                iPasswordValidationService= new PasswordLengthValidationService();
                errorMessage=errorMessage+iPasswordValidationService.isValidPassword(pwd,entry.getValue());
            }
            if (entry.getKey().equals("special_character"))
            {
                if(entry.getValue()==1)
                {
                    iPasswordValidationService= new PasswordSpecialCharacterService();
                    errorMessage=errorMessage+iPasswordValidationService.isValidPassword(pwd,1);
                }
            }
            if (entry.getKey().equals("capital_letter"))
            {
                if(entry.getValue()==1)
                {
                    iPasswordValidationService= new PasswordUpperCaseValidationService();
                    errorMessage=errorMessage+iPasswordValidationService.isValidPassword(pwd,1);
                }

            }
            if (entry.getKey().equals("small_letter"))
            {
                if(entry.getValue()==1)
                {
                    iPasswordValidationService= new PasswordLowerCaseValidationService();
                    errorMessage=errorMessage+iPasswordValidationService.isValidPassword(pwd,1);
                }
            }
        }
        return errorMessage;

    }


    @Override
    public boolean isConfirmPwdNull(String confirmPwd) {
        if(confirmPwd!=null && !confirmPwd.trim().isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    @Override
    public boolean isPasswordMatch(String pwd, String confirmPwd) {

        if(pwd.equals(confirmPwd))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean validPwd(String pwd) {

        log.log(0,"In service:validate Password");
        String password_patter = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
        return pwd.matches(password_patter);
    }


}
