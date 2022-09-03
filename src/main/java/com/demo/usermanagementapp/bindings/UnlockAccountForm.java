package com.demo.usermanagementapp.bindings;

import lombok.Data;

@Data
public class UnlockAccountForm {

	private String email;
	private String tempPassword;
	private String newPassword;
	private String confPassowrd;
}
