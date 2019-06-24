package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.model.Password;
import com.group4.carrental.service.IUpdatePasswordService;
import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service("UpdatePasswordService")
public class UpdatePasswordService implements IUpdatePasswordService {


    private IUpdatePasswordDAO updatePasswordDAO;
    private IUserSignUpService userSignUpService;

    @Autowired
    public UpdatePasswordService(@Qualifier("UpdatePasswordDAO") IUpdatePasswordDAO updatePasswordDAO,
                                 @Qualifier("UserSignUpService") IUserSignUpService userSignUpService){
        this.updatePasswordDAO = updatePasswordDAO;
        this.userSignUpService = userSignUpService;
    }


    @Override
    public void updatePassword(int userId, Password password) {
            updatePasswordDAO.updatePassword(userId,password);
    }

    @Override
    public boolean isPasswordNull(String password) {

        return  userSignUpService.ispwdNull(password);
    }


    @Override
    public boolean isPasswordMatch(String password, String confirmPassword){

        return  userSignUpService.isPasswordMatch(password,confirmPassword);

    }

    @Override
    public String getEncodedString(String originalString) throws UnsupportedEncodingException {

        return userSignUpService.getEncodedString(originalString);
    }

    @Override
    public boolean isOldPasswordValid(int userId, Password password) throws UnsupportedEncodingException {

           String passwordFromDb =  updatePasswordDAO.getUserOldPassword(userId);
           System.out.println("old password" + passwordFromDb);
           String getUserOldPassword = password.getOldPassword();
           String encodeUserOldPassword = userSignUpService.getEncodedString(getUserOldPassword);
           System.out.println("encode user pass " + encodeUserOldPassword);

           if(encodeUserOldPassword.equals(passwordFromDb)){
               return true;
           }

           return false;
    }

    @Override
    public boolean validatePassword(String password) {

        return userSignUpService.validPwd(password);

    }


}
