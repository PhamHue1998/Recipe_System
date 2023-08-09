package com.nal.teamc.dtos;

import java.util.Date;

public class SessionDTO {
    private String sessionId;
    private Date expireAt;
	public SessionDTO() {

	}
	
	public SessionDTO(String sessionId, Date expireAt) {
		super();
		this.sessionId = sessionId;
		this.expireAt = expireAt;
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getExpireAt() {
		return expireAt;
	}
	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}
}
