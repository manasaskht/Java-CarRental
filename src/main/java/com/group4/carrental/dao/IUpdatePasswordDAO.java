package com.group4.carrental.dao;

import com.group4.carrental.model.Password;

public interface IUpdatePasswordDAO {

    public String getUserOldPassword(int userId);


    public void updatePassword(int userId, Password password);
}
