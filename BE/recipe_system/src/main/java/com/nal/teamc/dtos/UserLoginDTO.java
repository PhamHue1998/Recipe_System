package com.nal.teamc.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email length exceeds the limit")
	private String email;
    
	@Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%!]).*", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
	private String password;
	public UserLoginDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
