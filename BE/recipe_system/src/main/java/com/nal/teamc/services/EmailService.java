package com.nal.teamc.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailService implements IEmailService{

	// contructor
	public EmailService() {

	}

	@Override
	public void sendEmail(String toEmail, String subject, String content) {
		final String EMAIL_NAME = "youremail.com";
		final String EMAIL_PASS = "yourpass";
		
        // Cấu hình thông tin SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Tạo session
        Session sessionMail = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_NAME, EMAIL_PASS);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(sessionMail);

            // Thiết lập thông tin người gửi
            message.setFrom(new InternetAddress(EMAIL_NAME));

            // Thiết lập thông tin người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Thiết lập tiêu đề và nội dung email
            message.setSubject(subject);
            message.setText(content);

            // Gửi email
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		
	}

}
