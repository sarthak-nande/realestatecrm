package com.crm.realestatecrm.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.service.AdminService;

@Controller
public class AdminController {

    private final AdminService adminService;
    
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @GetMapping("/admin")
    public String adminDashboard(@RequestParam(name = "email", required = false) String email, Model model) {
        List<Manager> managers;
        List<SalesExecutive> salesExecutives;
        
        if (email != null && !email.trim().isEmpty()){
            // First, search managers by the provided email
            managers = adminService.searchManagersByEmail(email);
            // Now, for every matching manager, retrieve its associated sales executives
            salesExecutives = new ArrayList<>();
            for (Manager manager : managers) {
                List<SalesExecutive> execs = adminService.searchSalesExecutivesByManagerEmail(manager.getEmail());
                salesExecutives.addAll(execs);
            }
        } else {
            managers = adminService.getAllManagers();
            salesExecutives = adminService.getAllSalesExecutives();
        }
        model.addAttribute("managers", managers);
        model.addAttribute("salesExecutives", salesExecutives);
        model.addAttribute("searchEmail", email);
        
        // Return the admin.html template (located under templates/admin folder)
        return "admin/admin";
    }
    
    @PostMapping("/updateStatus")
    public String updateUserStatus(@RequestParam("email") String email,
                                   @RequestParam("enabled") int enabled,
                                   @RequestParam(value = "searchEmail", required = false) String searchEmail, RedirectAttributes redirectAttributes) {
        
        try {
        	adminService.updateUserStatus(email, enabled);
			redirectAttributes.addFlashAttribute("successMessage", "Account Status updated successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to update account status.");
		}
        
        if (searchEmail != null && !searchEmail.trim().isEmpty()){
            return "redirect:/admin?email=" + searchEmail;
        }
        
        return "redirect:/admin";
    }
    
    @PostMapping("/updateSalesStatus")
    public String updateSalesStatus(@RequestParam("email") String email,
                                   @RequestParam("enabled") int enabled,
                                   @RequestParam(value = "searchEmail", required = false) String searchEmail, RedirectAttributes redirectAttributes) {
       
        try {
        	 adminService.updateUserStatus(email, enabled);
			redirectAttributes.addFlashAttribute("successMessage", "Account Status updated successfully!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to update account status!");
		}
        
        if (searchEmail != null && !searchEmail.trim().isEmpty()){
            return "redirect:/dashboard/salesexecutivedetails";
        }
        
        return "redirect:/dashboard/salesexecutivedetails";
    }
}
