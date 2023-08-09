package com.nal.teamc.reponsives;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nal.teamc.enties.Session;
import com.nal.teamc.enties.User;

@Repository
public interface SessionResponsitory extends JpaRepository<Session, Integer>{
	Session findBySessionId(String sessionId);
	List<Session> getAllSessionsByUserId(int userId);
	Session findByUser(User user);
}
