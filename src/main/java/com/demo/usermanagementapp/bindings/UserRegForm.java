package com.demo.usermanagementapp.bindings;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class UserRegForm {
	
	
	
	private String fname;
	
	private String lname;
	
	private String email;
	
	private long phno;
	
	private LocalDate dob;
	
	private String gender;
	
	private Integer countryid;

	private Integer stateId;
	
	private Integer cityId;

	private String accStatus;
	
	private String password;

}
