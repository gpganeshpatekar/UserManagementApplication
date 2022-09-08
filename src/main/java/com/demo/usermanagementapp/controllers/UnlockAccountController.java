package com.demo.usermanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.usermanagementapp.bindings.UnlockAccountForm;
import com.demo.usermanagementapp.services.UserServiceI;

@RestController
public class UnlockAccountController {
	
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping("/unLockAcc")
	public String unLockAccount(@RequestBody UnlockAccountForm unlockAccountForm) {
		boolean unLockAccount = userServiceI.unLockAccount(unlockAccountForm);
		if(unLockAccount) {
			return "Account Unlock";
		}else {
			return "Failed To Unlcok Account";
		}
	}

}
