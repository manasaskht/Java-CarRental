package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ILoginDAO;
import com.group4.carrental.model.User;

import com.group4.carrental.service.IUserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import com.group4.carrental.service.ILoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service("LoginService")
public class LoginService implements ILoginService {

	private ILoginDAO loginDAO;
	private IUserSignUpService signUpService;
	private LoggerInstance LoggerInstance;

	@Autowired
	public LoginService(@Qualifier("LoginDAO") ILoginDAO loginDAO,@Qualifier("UserSignUpService") IUserSignUpService signUpService,LoggerInstance LoggerInstance) {

		this.loginDAO = loginDAO;
		this.signUpService=signUpService;
		this.LoggerInstance=LoggerInstance;
	}
	


	@Override
	public boolean isUserValid(User user) throws UnsupportedEncodingException {
		
		if (null == user.getEmail() || null == user.getPassword()) {
			LoggerInstance.log(1,"User with empty email/password "+user.getEmail()+ " trying to login:Login Service called");
			return false;
		} else if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
			return false;
		} else {
			String passwordparam = signUpService.getEncodedString(user.getPassword());
			String passwordDB = loginDAO.getPassword(user.getEmail());
			LoggerInstance.log(0,"User with email "+user.getEmail()+ " has logged in successfully");
			return passwordparam.equals(passwordDB);
		}
	}

	@Override
	public boolean isValidUserEmail(String emailID) {
		if (null != emailID) {
			if (!emailID.trim().isEmpty()) {
				return emailID.contains("@");
			}
		}

		return false;
	}

	@Override
	public boolean isEmptyPassword(String password) {
		if (null != password) {
			return !password.trim().isEmpty();
		}

		return false;
	}

	@Override
	public int getUserId(User user) {
		
		return loginDAO.getUserId(user);
	}
}