package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import com.group4.carrental.service.ISignUpformRuleService;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;


@Service("UserSignUpService")
public class UserSignUpService implements IUserSignUpService {

    private LoggerInstance log;
    private IUserSignUpDAO iUserSignUpDAO;
    private ISignUpformRuleService iSignUpformRuleService;


    public UserSignUpService(@Qualifier("UserSignUpDAO") IUserSignUpDAO userSignUpDAO,
                             @Qualifier("SignUpformRuleService") ISignUpformRuleService signUpformRuleService,
                             LoggerInstance loggerInstance){
        this.iUserSignUpDAO = userSignUpDAO;
        this.iSignUpformRuleService=signUpformRuleService;
        this.log = loggerInstance;
    }


    @Override
    public void saveUserSignUpDetails(User user) {
        log.log(0,"In service:Save user details");
        iUserSignUpDAO.saveUserSignUpDetails(user);

    }

    @Override
    public User getUserDetails(Integer userId)
    {
        log.log(0,"In service:get logged in user details");
        return iUserSignUpDAO.getUserDetails(userId);
    }

    @Override
    public void updateUserProfileDetails(User user) {

        log.log(0,"In service:Update userDetails");
        iUserSignUpDAO.updateUserProfileDetails(user);
    }



    @Override
    public ArrayList<City> getCityList()
    {
        log.log(0,"In service:get list of cities from database");
       return iUserSignUpDAO.getCityList();
    }


    @Override
    public String getEncodedString(String originalString) throws UnsupportedEncodingException {

        log.log(0,"In service:get encodedString");
        String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes("UTF-8"));
        return encodedString;
    }

    @Override
    public HashMap<String, String> signUpFormValidation(User userData) {

        HashMap<String,String> errorMsg= new HashMap<String,String>();

        if(!iSignUpformRuleService.validUserName(userData.getName()))
        {
            errorMsg.put("nameError","please enter name");
        }
        if(!iSignUpformRuleService.validUserCity(userData.getCity_id()))
        {
            errorMsg.put("cityError","please select city");

        }
        if(iSignUpformRuleService.isEmailNull(userData.getEmail()))
        {
            errorMsg.put("emailError","please enter email");

        }
        else
        {
            if(iSignUpformRuleService.isEmailExist(userData.getEmail()))
            {
                errorMsg.put("emailError","This email id is already exist");

            }
            else
            {
                if(!iSignUpformRuleService.validUserEmail(userData.getEmail()))
                {
                    errorMsg.put("emailError","please enter valid email");
                }
            }

        }
        if(iSignUpformRuleService.ispwdNull(userData.getPassword()))
        {
            errorMsg.put("pwdError","please enter Password");
        }
        else if (iSignUpformRuleService.passwordValidation(userData.getPassword()).length()>0)
        {
            errorMsg.put("pwdError",iSignUpformRuleService.passwordValidation(userData.getPassword()));
        }
        if(iSignUpformRuleService.isConfirmPwdNull(userData.getConfirmPassword()))
        {
            errorMsg.put("confirmPwdError","please enter confirm password");
        }
        else if(!iSignUpformRuleService.isPasswordMatch(userData.getPassword(),userData.getConfirmPassword()))
        {
            errorMsg.put("confirmPwdError","password does not match");
        }

        return errorMsg;
    }

    @Override
    public HashMap<String, String> updateProfileFormValidation(User userData) {

        HashMap<String,String> errorMsg= new HashMap<String,String>();
        if(!iSignUpformRuleService.validUserName(userData.getName()))
        {
            errorMsg.put("nameUpdateError","please enter name");
        }
        if(!iSignUpformRuleService.validUserCity(userData.getCity_id()))
        {
            errorMsg.put("cityUpdateError","please select city");

        }
        return null;
    }
}
