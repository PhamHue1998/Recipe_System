package com.nal.teamc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nal.teamc.dtos.SessionDTO;
import com.nal.teamc.enties.Session;
import com.nal.teamc.services.SessionService;

@CrossOrigin
@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    
    @GetMapping("/{sessionId}")
    public ResponseEntity<SessionDTO> getSession(@PathVariable("sessionId") String sessionId) {
        Session session = sessionService.getSessionBySessionId(sessionId);
        if (session != null) {
        	SessionDTO sessionDTO = new SessionDTO(session.getSessionId(), session.getExpireAt());
            return ResponseEntity.ok(sessionDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


