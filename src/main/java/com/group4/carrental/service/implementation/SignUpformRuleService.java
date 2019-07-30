package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ISignUpFormRuleDAO;
import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.dao.implementation.SignUpFormRuleDAO;
import com.group4.carrental.service.ISignUpformRuleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("SignUpformRuleService")
public class SignUpformRuleService implements ISignUpformRuleService {


    private ISignUpFormRuleDAO iSignUpFormRuleDAO;

    public SignUpformRuleService(@Qualifier("SignUpFormRuleDAO") SignUpFormRuleDAO signUpFormRuleDAO, LoggerInstance loggerInstance){
        this.iSignUpFormRuleDAO = signUpFormRuleDAO;

    }

    @Override
    public HashMap<String, Integer> getRules() {

     return iSignUpFormRuleDAO.getRules();
    }
}
