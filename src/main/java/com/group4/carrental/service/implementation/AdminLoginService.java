package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IAdminLoginDAO;
import com.group4.carrental.model.Admin;
import com.group4.carrental.service.IAdminLoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AdminLoginService")
public class AdminLoginService implements IAdminLoginService {

    private IAdminLoginDAO adminLoginDAO;

    public AdminLoginService(@Qualifier("AdminLoginDao") IAdminLoginDAO adminLoginDAO){
        this.adminLoginDAO = adminLoginDAO;
    }

    @Override
    public Admin validateLogin(Admin admin) {
        return this.adminLoginDAO.validateLogin(admin);
    }
}
