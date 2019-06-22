package com.group4.carrental.service.implementation;

import com.group4.carrental.dao.ILoginDAO;
import com.group4.carrental.model.User;



import org.springframework.beans.factory.annotation.Autowired;
import com.group4.carrental.service.ILoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginService implements ILoginService {

	@Autowired
	private ILoginDAO loginDAO;

		
	public LoginService(@Qualifier("LoginDAO") ILoginDAO loginDAO) {
	    this.loginDAO = loginDAO;
	}
		 
		 @Override 
		public boolean isUserValid(User user){
			 if (null == user.getEmailID() || null == user.getPassword()) {
					return false;
				} else if (user.getEmailID().isEmpty() || user.getPassword().isEmpty()) {
					return false;
				} else {
			String passwordDB = loginDAO.getPassword(user.getEmailID());
			if (user.getPassword().equals(passwordDB)) {
				return true;
			} else {
				return false;
			}
				}
			}
			
		
		
		@Override
		public boolean isValidUserEmail(String emailID) {
			if (null != emailID) {
				if (!emailID.trim().isEmpty()) {
					if (emailID.contains("@")) {
						return true;
					}
				}
			}
			return false;
		}
		@Override
		public boolean isValidPassword(String password) {
			if (null != password) {
				if (!password.trim().isEmpty()) {
					return true;
				}
			}
			return false;
		}
	}