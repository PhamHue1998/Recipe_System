package com.nal.teamc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nal.teamc.dtos.ForgotPassworDTO;
import com.nal.teamc.dtos.ResetPasswordDTO;
import com.nal.teamc.dtos.UserLoginDTO;
import com.nal.teamc.dtos.UserSignupDTO;
import com.nal.teamc.enties.User;
import com.nal.teamc.services.EmailService;
import com.nal.teamc.services.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserSignupDTO userSignupDTO, BindingResult bindingResult) {
        // Kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessage = "";
            for (FieldError fieldError : fieldErrors) {
                errorMessage += fieldError.getField() + ": " + fieldError.getDefaultMessage() + "\n";
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    	
        // Kiểm tra xem email đã tồn tại hay chưa
        if (userService.isUserExists(userSignupDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email exists");
        }
        
        // Tạo đối tượng User từ dữ liệu trong UserSignupDTO
        User user = new User();
        user.setEmail(userSignupDTO.getEmail());
        // Mã hóa mật khẩu
        String hashedPassword = new BCryptPasswordEncoder().encode(userSignupDTO.getPassword());
        user.setPassword(hashedPassword);
        user.setRole(User.Role.USER); // Đặt giá trị cố định cho role
        // Lưu thông tin người dùng mới vào cơ sở dữ liệu
        userService.registerUser(user);
        
        return ResponseEntity.ok("susscess");
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        
        boolean checkLogin = userService.login(email, password);
        
        if (checkLogin) {          
            return ResponseEntity.ok("susscess");

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid  ForgotPassworDTO forgotPassworDTO) {
        String email = forgotPassworDTO.getEmail();
        //System.out.println(email);
        boolean checkFogotPass = userService.fogotPassword(email);
        if (!checkFogotPass) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }else {
            return ResponseEntity.ok("Email sent");
        }
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO) {
        String verificationCode = resetPasswordDTO.getVerificationCode();
        String newPassword = resetPasswordDTO.getNewPassword();

//        System.out.println(verificationCode);
//        System.out.println(newPassword); 
        
        boolean checkResetPass = userService.resetPassword(verificationCode, newPassword);
        if (checkResetPass) {
        	return ResponseEntity.ok("Password reset successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid verification code");
    }

}
