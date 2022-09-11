package com.demo.usermanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.usermanagementapp.bindings.UnlockAccountForm;
import com.demo.usermanagementapp.services.UserServiceI;
import com.demo.usermanagementapp.utils.AppConstants;
import com.demo.usermanagementapp.utils.AppProps;

@RestController
public class UnlockAccountController {
	
	@Autowired
	private UserServiceI userServiceI;
	
	@Autowired
	private AppProps appProps;
	
	@PostMapping("/unlockAcc")
	public String unLockAccount(@RequestBody UnlockAccountForm unlockAccountForm) {
		boolean unLockAccount = userServiceI.unLockAccount(unlockAccountForm);
		if(unLockAccount) {
			return AppConstants.ACCOUNT_UNLOCKED;
		}else {
			return AppConstants.UNLOCKE_FAILED;
		}
	}

}
