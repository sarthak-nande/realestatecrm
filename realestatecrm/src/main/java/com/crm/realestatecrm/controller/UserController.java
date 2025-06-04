package com.crm.realestatecrm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.realestatecrm.dao.ManagerDAOImple;
import com.crm.realestatecrm.dao.MangerDAO;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.service.ManagerService;

@Controller
public class UserController {
	
	private ManagerService managerService;
	
	public UserController(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	@GetMapping("/login")
	@PreAuthorize("isAuthenticated()")
	public String loadLoginPage(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard"; // Redirect if user is already authenticated
        }
		return "auth/login";
	}
	
	@GetMapping("/signup")
	public String loadSignUp(@ModelAttribute Manager manager) {
		return "auth/signup";
	}
	
	@PostMapping("/signup")
	public String submitSignUp(@ModelAttribute Manager manager) {	
		System.out.println(manager);
		managerService.save(manager);
		return "redirect:auth/login";
	}

}
