package com.nal.teamc.services;

import com.nal.teamc.enties.User;

public interface IUserService {
	// check user exists by email
	boolean isUserExists(String email);
	// register
	void registerUser(User user);
	// login
	boolean login(String email, String password);
	// forgot password
	boolean fogotPassword(String email);
	// reset password
	boolean resetPassword(String verificationCode, String newPassword);
}
