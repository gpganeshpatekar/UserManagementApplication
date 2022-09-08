package com.demo.usermanagementapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.usermanagementapp.bindings.LoginForm;
import com.demo.usermanagementapp.services.UserServiceI;

@RestController
public class LoginController {
	
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping(value="/login")
	public ResponseEntity<String> loginCheck(@RequestBody LoginForm loginForm){
		String loginCheck = userServiceI.loginCheck(loginForm);
		return new ResponseEntity<String>(loginCheck,HttpStatus.OK);
	}

}
