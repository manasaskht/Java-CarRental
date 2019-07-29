package com.group4.carrental.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import com.group4.carrental.dao.implementation.AdminResponseDAO;
import com.group4.carrental.model.Email;
import com.group4.carrental.service.IEmailApproveReject;
import com.group4.carrental.service.ISendMailService;

@Service("EmailApproveReject")
public class EmailApproveReject implements IEmailApproveReject{
	private ISendMailService sendMailService;
	private AdminResponseDAO adminresponsedao ;
	private LoggerInstance loggerInstance;
	@Autowired
    public EmailApproveReject(@Qualifier("AdminResponseDAO")AdminResponseDAO adminresponsedao, @Qualifier("SendMailService") ISendMailService sendMailService,LoggerInstance loggerInstance ){
        this.sendMailService = sendMailService;
        this.adminresponsedao = adminresponsedao;
		this.loggerInstance = loggerInstance;
    
    }
	
	public void sendApproveEmail(int carId) {
        String emailID = this.adminresponsedao.getEmail(carId);
        Email email = new Email();
        email.setReceiver(emailID);
        email.setEmailText("your car has been approved");
        email.setSubject("Car Approved to rent");
		loggerInstance.log(0,"Admin service to send email with status as approved : Called");
        this.sendMailService.sendEmail(email);
    }
	
	 public void sendRejectEmail(int carId) {
	        String emailID = this.adminresponsedao.getEmail(carId);
	        Email email = new Email();
	        email.setReceiver(emailID);
	        email.setEmailText("your car has been rejected");
	        email.setSubject("Car rejected to rent");
		 loggerInstance.log(0,"Admin service to send email with status as reject : Called");
	        this.sendMailService.sendEmail(email);
	    }

 public void sendToken(String EmailID,String TokenID,HttpServletRequest request)
	 {
		 String Scheme = request.getScheme();
		 String ServerName=request.getServerName();
		 String Port=String.valueOf(request.getServerPort());
		 Email email = new Email();
	        email.setReceiver(EmailID);
	        email.setEmailText("To confirm your account, please click here : " +Scheme+"://"+ServerName+":"+
	        		Port+"/reset?TokenID="+ TokenID);
	        email.setSubject("Password Reset Request");
		 loggerInstance.log(0," service to send email with token to reset password : Called");
	        this.sendMailService.sendEmail(email);
	 }
}
