package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;

@Service("UserSignUpService")
public class UserSignUpService implements IUserSignUpService {
    @Autowired
    private LoggerInstance log;
    private IUserSignUpDAO iUserSignUpDAO;

    public UserSignUpService(@Qualifier("UserSignUpDAO") IUserSignUpDAO userSignUpDAO){
        this.iUserSignUpDAO = userSignUpDAO;
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

        ArrayList<City> cityArrayList = this.getCityList();
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
    public boolean validPwd(String pwd) {

        log.log(0,"In service:validate Password");
        String password_patter = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
        return pwd.matches(password_patter);

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
    public String getEncodedString(String originalString) throws UnsupportedEncodingException {

        log.log(0,"In service:get encodedString");
        String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes("UTF-8"));
        return encodedString;
    }
}
