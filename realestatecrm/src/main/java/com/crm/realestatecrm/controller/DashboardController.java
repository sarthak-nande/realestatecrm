package com.crm.realestatecrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.crm.realestatecrm.dao.SalesExecutiveDAO;
import com.crm.realestatecrm.entity.Customer;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.service.CustomerService;
import com.crm.realestatecrm.service.SalesExecutiveService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;


@Controller
public class DashboardController {
	
	private SalesExecutiveService salesExecutiveService;
	private CustomerService customerService;
	
	@Autowired
	public DashboardController(SalesExecutiveService salesExecutiveService, CustomerService customerService) {
		this.salesExecutiveService = salesExecutiveService;
		this.customerService = customerService;
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
	
	@GetMapping("/dashboard/addcustomer")
	public String showCustomerAddForm(@ModelAttribute Customer customer) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    return "dashboard/addcustomer"; 
	}
	
	@PostMapping("/dashboard/addcustomer")
	public String addCustomer(@ModelAttribute Customer customer, Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    
	    customer.setSalesExecId(username);
	    
	    if(customerService.save(customer)) {
	    	model.addAttribute("success", "User Successfully Saved!");
	    }else {
	    	model.addAttribute("error", "User Already Exist!");
	    }
	    
	    return "dashboard/addcustomer";
	}
	
	@GetMapping("/dashboard/customerDetails")
    public String showDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        String role = userDetails.getAuthorities().stream()
                                 .map(GrantedAuthority::getAuthority)
                                 .findFirst()
                                 .orElse(null);
        
       System.out.println(email+role);
        
        List<Customer> customers = customerService.getAllCustomers(role, email);
        System.out.println(customers);

        model.addAttribute("customers", customers);
        return "dashboard/customerdetails";
    }

}
