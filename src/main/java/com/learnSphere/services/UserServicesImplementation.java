package com.learnSphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entity.Users;
import com.learnSphere.repository.UserRepository;

@Service
public class UserServicesImplementation  implements UserService{
    
    
    @Autowired
    private UserRepository urepo;
    
    
//also use
//	public UserServicesImplementation(UserRepository urepo) {
//		super();
//		this.urepo = urepo;
//	}

    @Override
	public String addUser(Users u) {
		urepo.save(u);
		return "Student added";
	}


	@Override
	public boolean checkEmail(String email) { 
		return urepo.existsByEmail(email);
	}


	@Override
	public boolean vaildate(String email, String password) {
		if(urepo.existsByEmail(email)) {
			Users u=urepo.getByEmail(email);
			String dbPassword=u.getPassword();
			if(password.equals(dbPassword)) {
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false; 
		}
	}


	@Override
	public String getUserRole(String email) {
		Users u=urepo.getByEmail(email);
		
		return u.getRole();
	}


	@Override
	public Users getUser(String email) {
		Users u=urepo.getByEmail(email);
		return u;
	}


	
}
