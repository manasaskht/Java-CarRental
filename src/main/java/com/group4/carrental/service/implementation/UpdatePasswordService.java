package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.model.Password;
import com.group4.carrental.service.ISignUpformRuleService;
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
    private LoggerInstance loggerInstance;
    private ISignUpformRuleService iSignUpformRuleService;

    @Autowired
    public UpdatePasswordService(@Qualifier("UpdatePasswordDAO") IUpdatePasswordDAO updatePasswordDAO,
                                 @Qualifier("UserSignUpService") IUserSignUpService userSignUpService,LoggerInstance loggerInstance,
                                 @Qualifier("SignUpformRuleService") ISignUpformRuleService signUpformRuleService){
        this.updatePasswordDAO = updatePasswordDAO;
        this.userSignUpService = userSignUpService;
        this.iSignUpformRuleService=signUpformRuleService;
        this.loggerInstance = loggerInstance;
    }


    @Override
    public void updatePassword(int userId, Password password) {
        loggerInstance.log(0,"User Service Password Update: Called");
        updatePasswordDAO.updatePassword(userId,password);
    }

    @Override
    public boolean isPasswordNull(String password) {
        loggerInstance.log(0,"User Service Password Null Check: Called");
        return  iSignUpformRuleService.ispwdNull(password);
    }


    @Override
    public boolean isPasswordMatch(String password, String confirmPassword){
        loggerInstance.log(0,"User Service Password Match: Called");
        return  iSignUpformRuleService.isPasswordMatch(password,confirmPassword);

    }

    @Override
    public String getEncodedString(String originalString) throws UnsupportedEncodingException {
        loggerInstance.log(0,"User Service Get Encoded String: Called");
        return userSignUpService.getEncodedString(originalString);
    }

    @Override
    public boolean isOldPasswordValid(int userId, Password password) throws UnsupportedEncodingException {
            loggerInstance.log(0,"User Service Old Password Validator: Called");
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
        loggerInstance.log(0,"User Service Password Validator: Called");
        return iSignUpformRuleService.validPwd(password);

    }


}
