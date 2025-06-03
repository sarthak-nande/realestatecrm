package com.crm.realestatecrm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String loadDashborad(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    // Get the username
	    String username = authentication.getName();
		return "dashboard";
	}
}
