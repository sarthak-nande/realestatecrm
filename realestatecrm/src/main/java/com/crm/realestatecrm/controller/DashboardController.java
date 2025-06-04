package com.crm.realestatecrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.crm.realestatecrm.dao.SalesExecutiveDAO;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.service.SalesExecutiveService;


@Controller
public class DashboardController {
	
	private SalesExecutiveService salesExecutiveService;
	
	@Autowired
	public DashboardController(SalesExecutiveService salesExecutiveService) {
		this.salesExecutiveService = salesExecutiveService;
	}

	@GetMapping("/dashboard")
	public String loadDashborad(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    // Get the username
	    String username = authentication.getName();
		return "dashboard/home";
	}
	
	@GetMapping("/dashboard/addSalesExec")
	public String showSalesExecutiveForm(@ModelAttribute SalesExecutive salesExecutive) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

//	    SalesExecutive salesExecutive = new SalesExecutive(); // Create a new object
//	    model.addAttribute("salesExecutive", salesExecutive); // Add it to the model

	    return "dashboard/salesexec"; // Ensure this matches the actual HTML file name
	}

	
	@PostMapping("/dashboard/addSalesExec")
	public String addSalesExecutive(@ModelAttribute SalesExecutive salesExecutive, Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    
	    salesExecutive.setManagerEmail(username);
	    
	    if(salesExecutiveService.save(salesExecutive)) {
	    	model.addAttribute("success", "User Successfully Saved!");
	    }else {
	    	model.addAttribute("error", "Failed To Save User!");
	    }
	    
	    return "dashboard/salesexec"; // Use redirect to prevent duplicate form submission
	}

}
