package com.crm.realestatecrm.service;

import java.util.List;

import com.crm.realestatecrm.entity.CustomerSupportTickets;

public interface CustomerSupportTicketService {
	void saveTicket(CustomerSupportTickets customerSupportTickets);
	
	List<CustomerSupportTickets> getAllTickets(String email, String role);
	
	void updateTicket(CustomerSupportTickets customerSupportTickets);
	
	CustomerSupportTickets findTicketById(String ticketId);
}
