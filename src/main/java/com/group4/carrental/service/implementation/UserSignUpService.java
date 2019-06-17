package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUserSignUpDAO;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("UserSignUpService")
public class UserSignUpService implements IUserSignUpService {
    private IUserSignUpDAO iUserSignUpDAO;

    public UserSignUpService(@Qualifier("UserSignUpDAO") IUserSignUpDAO userSignUpDAO){
        this.iUserSignUpDAO = userSignUpDAO;
    }


    @Override
    public void SaveUserSignUpDetails(User user) {

        iUserSignUpDAO.SaveUserSignUpDetails(user);

    }
}
