package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.model.City;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service("UserSignUpService")
public class UserSignUpService implements IUserSignUpService {
    private IUserSignUpDAO iUserSignUpDAO;

    public UserSignUpService(@Qualifier("UserSignUpDAO") IUserSignUpDAO userSignUpDAO){
        this.iUserSignUpDAO = userSignUpDAO;
    }


    @Override
    public void saveUserSignUpDetails(User user) {

        iUserSignUpDAO.saveUserSignUpDetails(user);

    }

    @Override
    public User getUserDetails(Integer userId) {
        return iUserSignUpDAO.getUserDetails(userId);
    }

    @Override
    public void updateUserProfileDetails(User user) {

        iUserSignUpDAO.updateUserProfileDetails(user);
    }



    @Override
    public ArrayList<City> getCityList()
    {
       return iUserSignUpDAO.getCityList();
    }

    @Override
    public boolean validUserName(String userName) {

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


}
