package com.group4.carrental.dao;

import com.group4.carrental.model.Password;

public interface IUpdatePasswordDAO {

    public String getUserOldPassword(String userId);


    public void updatePassword(Password password);
}
