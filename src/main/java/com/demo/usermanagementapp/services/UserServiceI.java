package com.demo.usermanagementapp.services;


import java.util.Map;

import com.demo.usermanagementapp.bindings.LoginForm;

public interface UserServiceI {
	
	public String loginCheck(LoginForm loginForm);
	
	public Map<Integer, String> getCountries();
	
	

}
