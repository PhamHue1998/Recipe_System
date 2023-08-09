package com.nal.teamc.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nal.teamc.enties.Session;
import com.nal.teamc.reponsives.SessionResponsitory;

@Service
@Transactional
public class SessionService implements ISessionService {
	
	@Autowired
	SessionResponsitory sessionResponsitory;

	@Override
	public Session getSessionBySessionId(String sessionId) {
		// TODO Auto-generated method stub
		return sessionResponsitory.findBySessionId(sessionId);
	}

	@Override
	public int isSessionExpired(String sessionId) {
		Session activeSessions = new Session();
		activeSessions = sessionResponsitory.findBySessionId(sessionId);
		if (Objects.isNull(activeSessions)) {
			return -1; // Không có phiên đăng nhập nào cho userId này
		}

		// Kiểm tra hạn sử dụng của phiên đăng nhập cuối cùng
		long lastSessionExpirationTime = activeSessions.getExpireAt().getTime();
		long currentTime = System.currentTimeMillis();
		if(lastSessionExpirationTime <= currentTime) {
			return -1;
		}else {
			return activeSessions.getUser().getId();
		}
	}

}
