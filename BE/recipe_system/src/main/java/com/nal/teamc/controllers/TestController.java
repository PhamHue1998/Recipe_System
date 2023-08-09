package com.nal.teamc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	public TestController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/wellcome")
	public ResponseEntity<?> index() {
		return new ResponseEntity<String>("successfully!", HttpStatus.OK);
	}
}
