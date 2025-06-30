package com.crm.realestatecrm.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.service.AdminService;

@Controller
public class AdminDashboardController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard/searchManagers")
    public String searchManagers(@RequestParam("email") String email, Model model) {
         
         List<Manager> managers = adminService.searchManagersByEmail(email);
         List<SalesExecutive> salesExecutives = new ArrayList<>();
         
         
         for (Manager manager : managers) {
             List<SalesExecutive> execs = adminService.searchSalesExecutivesByManagerEmail(manager.getEmail());
             salesExecutives.addAll(execs);
         }
         model.addAttribute("managers", managers);
         model.addAttribute("salesExecutives", salesExecutives);
         model.addAttribute("searchEmail", email);
         return "admin/admin";
    }
}
