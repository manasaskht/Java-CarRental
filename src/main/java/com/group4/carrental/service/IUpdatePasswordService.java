package com.group4.carrental.service;

import com.group4.carrental.model.Password;

public interface IUpdatePasswordService {


    public boolean validateOldPassword(String userId, Password password);
    public boolean validateNewPassword(Password password);
    public void updatePassword(Password password);


}
