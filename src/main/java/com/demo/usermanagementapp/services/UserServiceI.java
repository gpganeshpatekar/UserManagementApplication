package com.demo.usermanagementapp.services;


import java.util.Map;

import com.demo.usermanagementapp.bindings.LoginForm;
import com.demo.usermanagementapp.bindings.UnlockAccountForm;
import com.demo.usermanagementapp.bindings.UserRegForm;

public interface UserServiceI {
	
	public String loginCheck(LoginForm loginForm);
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public boolean saveUser(UserRegForm userRegForm);
	
	public boolean unLockAccount(UnlockAccountForm unlockAccountForm);
	
	public String forgotPassword(String email);

}
