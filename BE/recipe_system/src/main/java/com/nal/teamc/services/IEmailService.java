package com.nal.teamc.services;

public interface IEmailService {
	void sendEmail(String toEmail, String subject, String content);
}
