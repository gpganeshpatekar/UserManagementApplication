package com.demo.usermanagementapp.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.usermanagementapp.services.UserServiceI;

@RestController
public class ForgotPasswordRestController {
	
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping("/forgotPassowrd/{email}")
	public String forgotPassword(@PathVariable String email){
		
		String forgotPassword = userServiceI.forgotPassword(email);
		return forgotPassword;
		
	}

}
