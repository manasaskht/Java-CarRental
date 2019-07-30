package com.group4.carrental.service.implementation;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.group4.carrental.dao.IAdminResponseDAO;
import com.group4.carrental.model.AdminCar;
import com.group4.carrental.service.IAdminCarApproveService;
import com.group4.carrental.service.IEmailApproveReject;

@Service("AdminCarApproveService")
public class AdminCarApproveService implements IAdminCarApproveService{
		private IAdminResponseDAO adminresponsedao ;
		 private IEmailApproveReject sendMail;
	private LoggerInstance loggerInstance;
		 @Autowired
		    public AdminCarApproveService(@Qualifier("AdminResponseDAO")IAdminResponseDAO adminresponsedao, @Qualifier("EmailApproveReject") IEmailApproveReject sendMail,LoggerInstance loggerInstance ){
		        this.adminresponsedao = adminresponsedao;
		        this.sendMail = sendMail;
			 this.loggerInstance = loggerInstance;
		    }
		 @Override
		    public ArrayList<AdminCar> getAllPendingRequests() {
			 loggerInstance.log(0,"Admin Service get all pending status cars: Called");
		 	return adminresponsedao.getAllPendingRequests(4);
		    }

		    @Override
		    public void carApproval(int id) {
				loggerInstance.log(0,"Admin email service with status as approved: Called");
		    	adminresponsedao.carApproval(id);
		    	this.sendMail.sendApproveEmail(id);
		        
		    }
		
			@Override
			public void carReject(int id) {
				loggerInstance.log(0,"Admin email service with status as rejected: Called");
				adminresponsedao.carReject(id);
				this.sendMail.sendRejectEmail(id);
			}
			
			
}
