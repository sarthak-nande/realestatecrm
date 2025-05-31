package com.crm.realestatecrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping("/dashboard")
	public String loadDashborad() {
		return "dashboard";
	}
}
