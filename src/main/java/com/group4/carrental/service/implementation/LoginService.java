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

	@Autowired
	private ILoginDAO loginDAO;
	
	@Autowired
	private IUserSignUpService signUpService;
	
	@Autowired
	private LoggerInstance log;

	public LoginService(@Qualifier("LoginDAO") ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	


	@Override
	public boolean isUserValid(User user) throws UnsupportedEncodingException {
		
		if (null == user.getEmail() || null == user.getPassword()) {
			return false;
		} else if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
			return false;
		} else {
			String passwordparam = signUpService.getEncodedString(user.getPassword());
			String passwordDB = loginDAO.getPassword(user.getEmail());
			log.log(2,"User with email "+user.getEmail()+ " has logged in successfully");
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