package com.learnSphere.services;

import com.learnSphere.entity.Users;

public interface UserService {
	//adds new user to database
	String addUser(Users u) ;
	//checks email is already present in database or not
	boolean checkEmail(String email);
	//validates user's credentials
	boolean vaildate(String email,String password);
	//get user's role by providing email
	String getUserRole(String email);
	//get user's name by providing email
	Users getUser(String email);

	// Validates user's credentials
	boolean validate(String email, String password);  // Corrected method name
}
