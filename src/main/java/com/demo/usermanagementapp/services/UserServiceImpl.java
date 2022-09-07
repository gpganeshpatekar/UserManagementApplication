package com.demo.usermanagementapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.usermanagementapp.bindings.LoginForm;
import com.demo.usermanagementapp.entities.UserAccountEntity;
import com.demo.usermanagementapp.repositories.UserAccountRepository;



@Service
public class UserServiceImpl implements UserServiceI {
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	

	@Override
	public String loginCheck(LoginForm loginForm) {
		UserAccountEntity userAccountEntity = userAccountRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		if(userAccountEntity != null) {
			String accStatus = userAccountEntity.getAccStatus();
			if(accStatus.equals("LOCKED")) {
				return "Your Account Is Locked";
			}else {
				return "Login successful Welcome to Bikkad IT";
			}
		}else {
			return "Invalid Creditionals.. ";
		}
	}

}
