package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.model.Password;
import com.group4.carrental.service.IUpdatePasswordService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("UpdatePasswordService")
public class UpdatePasswordService implements IUpdatePasswordService {

    private IUpdatePasswordDAO updatePasswordDAO;
    public UpdatePasswordService(@Qualifier("UpdatePasswordDAO") IUpdatePasswordDAO updatePasswordDAO ){
        this.updatePasswordDAO = updatePasswordDAO;
    }


    @Override
    public void updatePassword(Password password) {
            updatePasswordDAO.updatePassword(password);
    }

    @Override
    public boolean validateOldPassword(String userId, Password password){

           String oldPassword =  updatePasswordDAO.getUserOldPassword(userId);
           if(password.getOldPassword().equals(oldPassword)){
               return true;
           }

           return false;
    }

    @Override
    public boolean validateNewPassword(Password password) {
        if(password.getNewPassword().equals(password.getOldPassword())){
            return true;
        }
        return false;
    }


}
