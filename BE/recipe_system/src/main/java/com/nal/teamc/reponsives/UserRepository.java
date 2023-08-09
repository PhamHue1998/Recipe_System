package com.nal.teamc.reponsives;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nal.teamc.enties.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByEmail(String email);
	User findByEmail(String email);
	User findByVerificationCode(String verificationCode);
	User findById(int id);
}
