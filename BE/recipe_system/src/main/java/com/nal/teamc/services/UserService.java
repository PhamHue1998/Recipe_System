package com.nal.teamc.services;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nal.teamc.enties.Session;
import com.nal.teamc.enties.User;
import com.nal.teamc.reponsives.SessionResponsitory;
import com.nal.teamc.reponsives.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionResponsitory sessionResponsitory;

	@Autowired
	private EmailService emailService;

	public boolean isUserExists(String email) {
		// Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
		return userRepository.existsByEmail(email);
	}

	public void registerUser(User user) {
		// Lưu thông tin người dùng mới vào cơ sở dữ liệu
		userRepository.save(user);

		// Cập nhật giá trị createdAt
		user.setCreatedAt(new Date());
	}

	@Override
	public boolean login(String email, String password) {
		User user = userRepository.findByEmail(email);

		// Nếu user tồn tại và pw khi login bằng với pw trong db
		if (user != null && new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			// Tạo session ID
			String sessionId = String.valueOf(user.getId() + System.currentTimeMillis());

			// Hash code sessionID
			sessionId = new BCryptPasswordEncoder().encode(sessionId);

			// tim session dua theo userId
			Session session = sessionResponsitory.findByUser(user);
			if (session != null) {
				session.setSessionId(sessionId);
				session.setUser(user);
				session.setCreatedAt(new Date());
				// Cài đặt thời gian hết hạn của session là 24 giờ sau khi tạo
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.HOUR_OF_DAY, 24);
				Date expireAt = calendar.getTime();
				session.setExpireAt(expireAt);
				sessionResponsitory.save(session);
			} else {
				Session newSession = new Session();
				newSession.setSessionId(sessionId);
				newSession.setUser(user);
				newSession.setCreatedAt(new Date());

				// Cài đặt thời gian hết hạn của session là 24 giờ sau khi tạo
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.HOUR_OF_DAY, 24);
				Date expireAt = calendar.getTime();
				newSession.setExpireAt(expireAt);

				// lưu vào db
				sessionResponsitory.save(newSession);
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean fogotPassword(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			// Tạo mã xác thực ngẫu nhiên
			String verificationCode = generateVerificationCode();

			// Lưu mã xác thực vào CSDL
			user.setVerificationCode(verificationCode);
			userRepository.save(user);

			// Gửi email chứa đường link tới người dùng
			// String resetPasswordLink = "http://your-app.com/reset-password?code=" +
			// verificationCode;
			String resetPasswordLink = "https://www.google.com/";
			String emailContent = "Xin chào,\n\nBạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình. Vui lòng truy cập đường link sau để tiếp tục:\n\n"
					+ resetPasswordLink + "\n\nNếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.";
			emailService.sendEmail(email, "Yêu cầu đặt lại mật khẩu", emailContent);

			return true;
		}
		return false;
	}

	// Tạo mã xác thực random
	private String generateVerificationCode() {
		// Các ký tự có thể có trong mã xác thực
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

		// Độ dài của mã xác thực
		int length = 8;

		StringBuilder verificationCode = new StringBuilder();
		SecureRandom random = new SecureRandom();

		// Tạo mã xác thực bằng cách chọn ngẫu nhiên các ký tự từ chuỗi characters
		for (int i = 0; i < length; i++) {
			// random từng ký tự
			int randomIndex = random.nextInt(characters.length());
			// lưu vào chuỗi
			verificationCode.append(characters.charAt(randomIndex));
		}

		return verificationCode.toString();
	}

	@Override
	public boolean resetPassword(String verificationCode, String newPassword) {
		// Kiểm tra mã xác thực và lấy thông tin người dùng dựa trên mã xác thực
		User user = userRepository.findByVerificationCode(verificationCode);

		if (user != null) {
			// Cập nhật mật khẩu mới
			String newPasswordHashCode = new BCryptPasswordEncoder().encode(newPassword);
			user.setPassword(newPasswordHashCode);
			// xóa mã xác thực
			user.setVerificationCode("");
			// cập nhật ngày sửa
			user.setModifyAt(new Date());
			userRepository.save(user);
			return true;
		}
		
		return false;
		
	}

}

