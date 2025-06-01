package com.crm.realestatecrm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@GetMapping("/login")
	@PreAuthorize("isAuthenticated()")
	public String loadLoginPage(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard"; // Redirect if user is already authenticated
        }
		return "auth/login";
	}

}
