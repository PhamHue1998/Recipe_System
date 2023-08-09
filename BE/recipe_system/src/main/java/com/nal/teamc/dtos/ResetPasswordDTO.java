package com.nal.teamc.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ResetPasswordDTO {

	private String verificationCode;
	
	@Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!]).*", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
	private String newPassword;
	
	public ResetPasswordDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
