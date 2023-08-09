package com.nal.teamc.services;

import com.nal.teamc.enties.Session;

public interface ISessionService {
	Session getSessionBySessionId(String sessionId);
    int isSessionExpired(String sessionId);
}
