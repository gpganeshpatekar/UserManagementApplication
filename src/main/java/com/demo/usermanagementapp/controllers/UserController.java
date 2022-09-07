package com.demo.usermanagementapp.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.usermanagementapp.bindings.UserRegForm;
import com.demo.usermanagementapp.services.UserServiceI;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceI userServiceI;
	
	@GetMapping("/countries")
	public ResponseEntity<Map<Integer, String>> countries(){
		Map<Integer, String> countries = userServiceI.getCountries();
		return new ResponseEntity<Map<Integer,String>>(countries,HttpStatus.OK);
	}
	
	@GetMapping("/states/{countryId}")
	public ResponseEntity<Map<Integer, String>> states(@PathVariable Integer countrId){
		Map<Integer, String> states = userServiceI.getStates(countrId);
		return new ResponseEntity<Map<Integer,String>>(states,HttpStatus.OK);
	}
	
	@GetMapping("/cities/{stateId}")
	public ResponseEntity<Map<Integer, String>> cities(@PathVariable Integer stateId){
		Map<Integer, String> cities = userServiceI.getCities(stateId);
		return new ResponseEntity<Map<Integer,String>>(cities,HttpStatus.OK);
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<String> saveUser(@RequestBody UserRegForm userRegFrom){
		
		boolean saveUser = userServiceI.saveUser(userRegFrom);
		if(saveUser) {
			String saveSuccess = "User Saved Successfully.. Check Your Email For Unlock Account..";
			return new ResponseEntity<String>(saveSuccess,HttpStatus.CREATED);
		}else {
			String saveUnsuccess = "User Not Saved Successfully";
			return new ResponseEntity<String>(saveUnsuccess,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	

}
