package com.group4.carrental.dao;

import com.group4.carrental.model.Admin;

public class AdminLoginDAOMock implements IAdminLoginDAO{

    @Override
    public Admin validateLogin(Admin adminData) {
        if(adminData.getUsername().equals("admin") && adminData.getPassword().equals("adminPassword")){
            Admin admin = new Admin();
            admin.setAdminId(1);
            admin.setUsername("admin");
            return admin;
        }else {
            return null;
        }
    }
}
