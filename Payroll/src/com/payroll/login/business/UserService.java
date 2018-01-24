package com.payroll.login.business;

import com.payroll.login.dataobjects.User;
import com.payroll.login.dataobjects.UserDAO;
import com.payroll.utils.PasswordUtils;

public class UserService {
	
	public static boolean addUser(User user){
		user.setPassword(PasswordUtils.getEncryptedPassword(user.getPassword()));
		return new UserDAO().addUser(user);
	}
	
	public static User validateUser(User userVO) {
		User user = new UserDAO().getUserByUserId(userVO);
		if (user != null && user.getEmployee() != null) {
			boolean isValid = PasswordUtils.isValidPassword(userVO.getPassword(), user.getPassword());
			return (isValid? user : null);
		} else return null;
	}
}
