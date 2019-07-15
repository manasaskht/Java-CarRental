package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.IAdminLoginDAO;
import com.group4.carrental.model.Admin;
import com.group4.carrental.service.IAdminLoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AdminLoginService")
public class AdminLoginService implements IAdminLoginService {

    private IAdminLoginDAO adminLoginDAO;
    private LoggerInstance loggerInstance;

    public AdminLoginService(@Qualifier("AdminLoginDao") IAdminLoginDAO adminLoginDAO, LoggerInstance loggerInstance){
        this.loggerInstance = loggerInstance;
        this.adminLoginDAO = adminLoginDAO;
    }

    @Override
    public Admin validateLogin(Admin admin)
    {
        loggerInstance.log(0,"Admin Service Validate Login: Called");
        return this.adminLoginDAO.validateLogin(admin);
    }
}
