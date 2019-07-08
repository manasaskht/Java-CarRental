package com.group4.carrental.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.group4.carrental.dao.implementation.AdminResponseDAO;
import com.group4.carrental.model.Email;
import com.group4.carrental.service.IEmailApproveReject;
import com.group4.carrental.service.ISendMailService;

@Service("EmailApproveReject")
public class EmailApproveReject implements IEmailApproveReject{
	private ISendMailService sendMailService;
	private AdminResponseDAO adminresponsedao ;
	@Autowired
    public EmailApproveReject(@Qualifier("AdminResponseDAO")AdminResponseDAO adminresponsedao, @Qualifier("SendMailService") ISendMailService sendMailService ){
        this.sendMailService = sendMailService;
        this.adminresponsedao = adminresponsedao;
    
    }
	
	public void sendApproveEmail(int carId) {
        String emailID = this.adminresponsedao.getEmail(carId);
        Email email = new Email();
        email.setReceiver(emailID);
        email.setEmailText("your car has been approved");
        email.setSubject("Car Approved to rent");
        this.sendMailService.sendEmail(email);
    }
	
	 public void sendRejectEmail(int carId) {
	        String emailID = this.adminresponsedao.getEmail(carId);
	        Email email = new Email();
	        email.setReceiver(emailID);
	        email.setEmailText("your car has been rejected");
	        email.setSubject("Car rejected to rent");
	        this.sendMailService.sendEmail(email);
	    }
}
