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
		 
		 @Autowired
		    public AdminCarApproveService(@Qualifier("AdminResponseDAO")IAdminResponseDAO adminresponsedao, @Qualifier("EmailApproveReject") IEmailApproveReject sendMail ){
		        this.adminresponsedao = adminresponsedao;
		        this.sendMail = sendMail;
		    
		    }
		 @Override
		    public ArrayList<AdminCar> getAllPendingRequests() {
		        return adminresponsedao.getAllPendingRequests(4);
		    }

		    @Override
		    public void carApproval(int id) {
		    	adminresponsedao.carApproval(id);
		    	this.sendMail.sendApproveEmail(id);
		        
		    }
		
			@Override
			public void carReject(int id) {
				adminresponsedao.carReject(id);
				this.sendMail.sendRejectEmail(id);
			}
			
			
}
