package com.demo.usermanagementapp.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_ACCOUNTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "FIRST_NAME")
	private String fname;
	@Column(name = "LAST_NAME")
	private String lname;
	@Column(name = "USER_EMAIL")
	private String email;
	@Column(name = "USER_PHNO")
	private long phno;
	@Column(name = "USER_DOB")
	private LocalDate dob;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "COUNTRY")
	private Integer countryid;
	@Column(name = "STATE")
	private Integer stateId;
	@Column(name = "CITY")
	private Integer cityId;
	@Column(name = "ACC_STATUS")
	private String accStatus;
	@CreationTimestamp
	@Column(name = "CREATED_DATE",updatable = false)
	private LocalDateTime createDate;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE",insertable = false)
	private LocalDateTime updateDate;
	@Column(name = "USER_PASSWORD")
	private String password;
}
