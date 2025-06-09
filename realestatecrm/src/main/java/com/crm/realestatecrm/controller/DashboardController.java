package com.crm.realestatecrm.controller;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.realestatecrm.dao.SalesExecutiveDAO;
import com.crm.realestatecrm.entity.Customer;
import com.crm.realestatecrm.entity.CustomerFeedback;
import com.crm.realestatecrm.entity.CustomerSupportTickets;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.Properties;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.entity.SalesExecutiveTask;
import com.crm.realestatecrm.service.CustomerFeedbackService;
import com.crm.realestatecrm.service.CustomerService;
import com.crm.realestatecrm.service.CustomerSupportTicketService;
import com.crm.realestatecrm.service.PropertiesService;
import com.crm.realestatecrm.service.SalesExecutiveService;
import com.crm.realestatecrm.service.SalesExecutiveTaskService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;


@Controller
public class DashboardController {
	
	private SalesExecutiveService salesExecutiveService;
	private CustomerService customerService;
	private PropertiesService propertiesService;
	private SalesExecutiveTaskService salesExecutiveTaskService;
	private CustomerSupportTicketService customerSupportTicketService;
	private CustomerFeedbackService customerFeedbackService;
	
	@Autowired
	public DashboardController(SalesExecutiveService salesExecutiveService, CustomerService customerService, PropertiesService propertiesService, SalesExecutiveTaskService salesExecutiveTaskService, CustomerSupportTicketService customerSupportTicketService, CustomerFeedbackService customerFeedbackService) {
		this.salesExecutiveService = salesExecutiveService;
		this.customerService = customerService;
		this.propertiesService = propertiesService;
		this.salesExecutiveTaskService = salesExecutiveTaskService;
		this.customerSupportTicketService = customerSupportTicketService;
		this.customerFeedbackService = customerFeedbackService;
	}
	
	@GetMapping("/access-denied")
	public String notFound() {
		return "dashboard/notfound";
	}

	@GetMapping("/dashboard")
	public String loadDashborad(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
        String role = userDetails.getAuthorities().stream()
                                 .map(GrantedAuthority::getAuthority)
                                 .findFirst()
                                 .orElse(null);
		
		int customerCount = customerService.getCustomerCount(role, email);
		int salesExecutiveCount = salesExecutiveService.getSalesExecutiveCount(email);
		int pendingTasks = salesExecutiveTaskService.getSalesExecutiveTask(email, role);
		
		List<Integer> tasks = salesExecutiveTaskService.getTaskCount(email, role);
		
		List<String> rawRegistrationTimestamps = customerService.getCreatedTime(role, email);

        // Define the formatter for your timestamp string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Get current year and month for filtering
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        int currentMonthValue = now.getMonthValue();
        YearMonth currentYearMonth = YearMonth.of(currentYear, currentMonthValue);
        int daysInMonth = currentYearMonth.lengthOfMonth(); // Get total days in current month

        // Initialize a map to store daily counts, ensuring all days of the month are present with 0
        Map<Integer, Long> dailyCustomerRegistrationsMap = new TreeMap<>();
        IntStream.rangeClosed(1, daysInMonth).forEach(day -> dailyCustomerRegistrationsMap.put(day, 0L));

        // Process each raw timestamp string
        for (String timestampString : rawRegistrationTimestamps) {
            try {
                LocalDateTime parsedDateTime = LocalDateTime.parse(timestampString, formatter);

                // Check if the timestamp falls within the current year and month
                if (parsedDateTime.getYear() == currentYear && parsedDateTime.getMonthValue() == currentMonthValue) {
                    int dayOfMonth = parsedDateTime.getDayOfMonth();
                    // Increment the count for that day
                    dailyCustomerRegistrationsMap.merge(dayOfMonth, 1L, Long::sum);
                }
            } catch (Exception e) {
                // Log parsing errors if a string is not in the expected format
                System.err.println("Failed to parse timestamp string: " + timestampString + " - " + e.getMessage());
                // You might choose to throw an exception, skip, or use a default
            }
        }

        // Prepare lists for Chart.js
        List<String> customerRegLabels = IntStream.rangeClosed(1, daysInMonth)
                                                  .mapToObj(day -> "Day " + day)
                                                  .collect(Collectors.toList());

        List<Long> customerRegData = IntStream.rangeClosed(1, daysInMonth)
                                          .mapToObj(day -> dailyCustomerRegistrationsMap.get(day))
                                          .collect(Collectors.toList());
		
	    model.addAttribute("customerCount" , customerCount);
	    model.addAttribute("salesExecutiveCount" , salesExecutiveCount);
	    model.addAttribute("pendingTasks", pendingTasks);
	    model.addAttribute("tasks" , tasks);
	    model.addAttribute("customerRegLabels", customerRegLabels); // For line graph
        model.addAttribute("customerRegData", customerRegData); 
	    
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
	public String showCustomerAddForm(@ModelAttribute Customer customer,Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    
	    List<Properties> properties = propertiesService.findAllProperties();
	    
	    model.addAttribute("properties", properties);

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
        
        
        List<Customer> customers = customerService.getAllCustomers(role, email);

        model.addAttribute("customers", customers);
        return "dashboard/customerdetails";
    }
	
	 @GetMapping("/dashboard/customerDetails/edit")
	    public String getCustomerByEmail(@RequestParam String email, Model model) {
	        Customer customer = customerService.getCustomerByEmail(email);
	        List<Properties> properties = propertiesService.findAllProperties();
	        if(customer.getPropertyId() == "null") {
	        	Properties property = propertiesService.findPropertyById(customer.getPropertyId());
		        String currentProperty = property.getTitle();
		        model.addAttribute("property" , currentProperty);
	        }
	        
	        model.addAttribute("properties", properties);
	        model.addAttribute("customer", customer);
	        return "dashboard/editcustomer";
	    }

	    @PostMapping("/dashboard/customerDetails/update")
	    public String updateCustomer(@ModelAttribute Customer customer) {
	        customerService.updateCustomer(customer);
	        return "redirect:/dashboard/customerDetails";
	    }
	    
	    @GetMapping("/dashboard/createTask")
	    public String showCreateTaskForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    	 String email = userDetails.getUsername();
	         String role = userDetails.getAuthorities().stream()
	                                  .map(GrantedAuthority::getAuthority)
	                                  .findFirst()
	                                  .orElse(null);
		    
	    	List<SalesExecutive> salesExecutives = salesExecutiveService.getAllSalesExecutives(email);
	    	
	    	List<Customer> customers = customerService.getAllCustomers(role, email);
	    	
	    	model.addAttribute("customers", customers);
	    	model.addAttribute("salesExecutives", salesExecutives);
	        model.addAttribute("task", new SalesExecutiveTask());
	        return "dashboard/createtask";
	    }

	    @PostMapping("/dashboard/createTask")
	    public String createTask(@ModelAttribute SalesExecutiveTask task) {
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String username = authentication.getName();
		    task.setManagerId(username);
		    System.out.println(task.getTaskType());
	        salesExecutiveTaskService.save(task);
	        return "redirect:/dashboard/createTask";
	    }
	    
	    @GetMapping("/dashboard/dailytask")
	    public String getSalesExecutiveTask(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    	String email = userDetails.getUsername();
	        String role = userDetails.getAuthorities().stream()
	                                  .map(GrantedAuthority::getAuthority)
	                                  .findFirst()
	                                  .orElse(null);
	        
	        List<SalesExecutiveTask> salesExecutiveTasks = salesExecutiveTaskService.getAllTask(email, role);
	        
	        System.out.println(salesExecutiveTasks);
	        
	        model.addAttribute("salesExecutiveTasks", salesExecutiveTasks);
	        
			return "dashboard/dailytask";
	    }
	    
	    @GetMapping("/dashboard/task/edit")
	    public String loadUpdateTask(@RequestParam String email, Model model) {
	    	SalesExecutiveTask task = salesExecutiveTaskService.getTaskByCustomerId(email);
	    	model.addAttribute("salesExecutiveTask" , task);
			return "dashboard/edittask";
	    }
	    
	    @PostMapping("/dashboard/task/edit")
	    public String updateTask(@ModelAttribute SalesExecutiveTask salesExecutiveTask) {
	    	salesExecutiveTaskService.updateTask(salesExecutiveTask);
			return "redirect:/dashboard/dailytask";
	    }
	    
	    @GetMapping("/dashboard/createTicket")
	    public String showCreateTicketForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    	 String email = userDetails.getUsername();
	         String role = userDetails.getAuthorities().stream()
	                                  .map(GrantedAuthority::getAuthority)
	                                  .findFirst()
	                                  .orElse(null);
		    
	    	List<SalesExecutive> salesExecutives = salesExecutiveService.getAllSalesExecutives(email);
	    	
	    	List<Customer> customers = customerService.getAllCustomers(role, email);
	    	
	    	model.addAttribute("customers", customers);
	    	model.addAttribute("salesExecutives", salesExecutives);
	        model.addAttribute("customerSupportTicket", new CustomerSupportTickets());
	        return "dashboard/createticket";
	    }

	    @PostMapping("/dashboard/createTicket")
	    public String createTicket(@ModelAttribute CustomerSupportTickets ticket, @AuthenticationPrincipal UserDetails userDetails) {
	    	String email = userDetails.getUsername();
	        String role = userDetails.getAuthorities().stream()
	                                  .map(GrantedAuthority::getAuthority)
	                                  .findFirst()
	                                  .orElse(null);
	        if(role.equals("ROLE_SALES")) {
	        	ticket.setSalesExectiveId(email);
	        	SalesExecutive salesExecutive = salesExecutiveService.findSalesExecutiveByEmail(email);
	        	ticket.setManagerId(salesExecutive.getManagerEmail());
	        }
	        
	        ticket.setManagerId(email);
	        
	        customerSupportTicketService.saveTicket(ticket);
	        return "redirect:/dashboard/createTicket";
	    }
	    
	    @GetMapping("/dashboard/tickets")
	    public String getCustomerSupportTicket(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    	String email = userDetails.getUsername();
	        String role = userDetails.getAuthorities().stream()
	                                  .map(GrantedAuthority::getAuthority)
	                                  .findFirst()
	                                  .orElse(null);
	        
	        List<CustomerSupportTickets> customerSupportTickets = customerSupportTicketService.getAllTickets(email, role);
	        
	        
	        model.addAttribute("customerSupportTickets", customerSupportTickets);
	        
			return "dashboard/tickets";
	    }
	    
	    @GetMapping("/dashboard/ticket/edit")
	    public String loadUpdateTicket(@RequestParam String ticket, Model model) {
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String email = authentication.getName();
	    	
	    	List<SalesExecutive> salesExecutives = salesExecutiveService.getAllSalesExecutives(email);
	    	model.addAttribute("salesExecutives", salesExecutives);
	    	CustomerSupportTickets customerSupportTicket = customerSupportTicketService.findTicketById(ticket);
	    	model.addAttribute("customerSupportTicket" , customerSupportTicket);
			return "dashboard/editticket";
	    }
	    
	    @PostMapping("/dashboard/ticket/edit")
	    public String updateTicket(@ModelAttribute CustomerSupportTickets customerSupportTickets) {
	    	customerSupportTicketService.updateTicket(customerSupportTickets);
			return "redirect:/dashboard/tickets";
	    }
	    
	    @GetMapping("dashboard/customerfeedback")
	    public String loadFeedBackForm(@RequestParam(required = false) String ticketId, @RequestParam String customerId, Model model) {
	    	CustomerFeedback customerFeedback = new CustomerFeedback();
	    
	    	if(ticketId != null && customerId != null) {
	    		customerFeedback.setCustomerId(customerId);
	    		customerFeedback.setTicketId(ticketId);
	    	}
	    	
	    	if(customerId != null && ticketId == null) {
	    		customerFeedback.setCustomerId(customerId);
	    	}
	    	
	    	model.addAttribute("customerFeedback" , customerFeedback);
	    	
	    	return "dashboard/feedbackform";
	    	
	    }
	    
	    @PostMapping("dashboard/customerfeedback")
	    public String saveCustomerFeedback(@ModelAttribute CustomerFeedback customerFeedback, Model model) {
	    	customerFeedbackService.saveFeedback(customerFeedback);
	    	if(customerFeedback.getTicketId() != null) {
	    		return "redirect:/dashboard/tickets";
	    	}
	    	return "redirect:/dashboard/customerDetails";
	    }
	    
	    @GetMapping("dashboard/feedbacks")
	    public String getfeedbacks(Model model, @RequestParam(required = false) String ticketId, @RequestParam String customerId) {
	    	List<CustomerFeedback> customerFeedbacks = new ArrayList<>();
	    	
	    	if(ticketId != null && customerId != null)
	    	{
	    		customerFeedbacks = customerFeedbackService.getCusomerFeedbacksByTicketId(ticketId);
	    	}
	    	
	    	if(customerId != null && ticketId == null)
	    	{
	    		customerFeedbacks = customerFeedbackService.getCustomerFeedbackByCustomerId(customerId);
	    	}
	    	
	    	model.addAttribute("customerFeedbacks", customerFeedbacks);
	    	
	    	return "dashboard/feedbacks";
	    }
	    
	    @GetMapping("dashboard/feedback/edit")
	    public String updateFeedback(@RequestParam(required = false)String feedbackId, Model model) {
	    	CustomerFeedback customerFeedback = customerFeedbackService.getCustomerByFeedBackId(feedbackId);
	    	model.addAttribute("customerFeedback",customerFeedback);
	    	return "dashboard/editfeedback";
	    }
	    
	    @PostMapping("dashboard/feedback/edit")
	    public String updateFeedback(@ModelAttribute CustomerFeedback customerFeedback) {
	    	customerFeedbackService.updateCustomerFeedback(customerFeedback);
	    	System.out.println(customerFeedback.getTicketId());
	    	if(customerFeedback.getTicketId() != null) {
	    		return "redirect:/dashboard/tickets";
	    	}
	    	return "redirect:/dashboard/customerDetails";
	    }
	    

}
