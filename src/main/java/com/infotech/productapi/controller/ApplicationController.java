package com.infotech.productapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

	@RequestMapping("/MyAdmin")
	public String getHome() {
		return "Home page";
		
	}
	
	@GetMapping("/")
	public String getAdmin() {
		return "admin page";
		
	}
}
