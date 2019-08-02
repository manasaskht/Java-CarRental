package com.group4.carrental.service.implementation;

import java.io.UnsupportedEncodingException;

import com.group4.carrental.service.ILoginService;
import com.group4.carrental.service.ISignUpformRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.group4.carrental.dao.IForgotPasswordDAO;
import com.group4.carrental.model.User;
import com.group4.carrental.service.IForgotPasswordService;
import com.group4.carrental.service.IUserSignUpService;

@Service("ForgotPasswordService")
public class ForgotPasswordService implements IForgotPasswordService {
	
	private IUserSignUpService userSignUpService;
	private IForgotPasswordDAO ForgotPasswordDAO;
	private ISignUpformRuleService iSignUpformRuleService;
	private ILoginService loginService;
	private LoggerInstance log;
	
	@Autowired
    public ForgotPasswordService(@Qualifier("UserSignUpService") IUserSignUpService userSignUpService,@Qualifier("ForgotPasswordDAO") IForgotPasswordDAO ForgotPasswordDAO,@Qualifier("LoginService") ILoginService loginService,LoggerInstance log, @Qualifier("SignUpformRuleService") ISignUpformRuleService signUpformRuleService){
        
        this.userSignUpService = userSignUpService;
        this.ForgotPasswordDAO=ForgotPasswordDAO;
        this.loginService=loginService;
        this.log=log;
        this.iSignUpformRuleService=signUpformRuleService;

    }

	@Override
	public boolean isValidUserEmail(String email){
		log.log(0," login service to check email is valid or not : Called");
		return loginService.isValidUserEmail(email);
	}
	
	@Override
	public boolean findUserByEmail(User user) {
		
		if (null == user.getEmail() && user.getEmail().isEmpty()) {
			log.log(1,"User with empty email "+user.getEmail()+ " trying to reset password");
			return false; 
	}else
	{
		
		String emailParam = user.getEmail();
		
		String emailDB = ForgotPasswordDAO.findUserByEmail(user.getEmail());
		log.log(0,"User with email "+user.getEmail()+ " trying to reset password");
		return emailParam.equals(emailDB);
	}
		
	}
	@Override
	public boolean findUserByResetToken(User user) {
		 if (null == user.getTokenID() && user.getTokenID().isEmpty())
			{log.log(1,"User with empty token "+user.getEmail()+ " trying to reset password");
			 return false; 
			}
			
			
		 else if (null == user.getEmail() || user.getEmail().isEmpty()) {
			 log.log(1,"User with empty mail "+user.getEmail()+ " trying to reset password");
				return false;
		 }
		else
		{
			log.log(0,"  service to check token is valid or not : Called");
			String tokenParam=user.getTokenID();
			String tokenDB=ForgotPasswordDAO.findUserByResetToken(user.getEmail());
			return tokenParam.equals(tokenDB);
			
		}
			
		
		
	}

	@Override
	public void addToken(User user) {

		log.log(0,"  service to add token to database : Called");
	 ForgotPasswordDAO.addToken(user.getEmail(),user.getTokenID());	
	}
	@Override
	public boolean validate(User user) {
		
		if (null == user.getTokenID() && user.getTokenID().isEmpty())
		{log.log(1,"User with empty token "+user.getEmail()+ " trying to reset password");
			return false; 
		
		}
	
	else
	{
		log.log(0,"  service to validate user in database : Called");
		String tokenParam=user.getTokenID();
		String tokenDB=ForgotPasswordDAO.validate(user.getTokenID());
		return tokenParam.equals(tokenDB);
		
		
	}
	}
	 @Override
	    public boolean isPasswordNull(String password) {
		 log.log(0,"  Usersignup service to check if passwords is null or not  : Called");
	        return  iSignUpformRuleService.ispwdNull(password);
	    }
	 @Override
	    public String getEncodedString(String originalString) throws UnsupportedEncodingException {
		 log.log(0,"  Usersignup service to encode password : Called");
	        return userSignUpService.getEncodedString(originalString);
	    }
	public void updatePassword(User user) {
		log.log(0,"  service to update password to database : Called");
		ForgotPasswordDAO.updatePassword(user.getEmail(),user.getPassword());
	}
	
	 @Override
	    public boolean validatePassword(String password) {
		 log.log(0,"  Usersignup service to check if passwords is valid  or not  : Called");
	        return iSignUpformRuleService.validPwd(password);

	    }
	@Override
	public boolean isPasswordMatch(String password,String confirm_password) {
		log.log(0,"  Usersignup service to check if passwords matched or not  : Called");
		return iSignUpformRuleService.isPasswordMatch(password,confirm_password);

	}
	@Override
	public boolean isConfirmPwdNull(String confirm_password) {
		log.log(0,"  Usersignup service to check if confirmpwd is null or not  : Called");
		return iSignUpformRuleService.isConfirmPwdNull(confirm_password);

	}
}
