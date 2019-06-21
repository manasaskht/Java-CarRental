package com.group4.carrental.service;

import com.group4.carrental.model.Password;

public interface IUpdatePasswordService {

    public String validateOldPassword(String userId, Password password);
    public void updatePassword(Password password);

}
