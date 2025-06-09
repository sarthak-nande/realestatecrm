package com.crm.realestatecrm.dao;

import java.util.List;

import com.crm.realestatecrm.entity.CustomerSupportTickets;

public interface CustomerSupportTicketDAO {

	void saveTicket(CustomerSupportTickets customerSupportTickets);
	
	List<CustomerSupportTickets> getAllTickets(String email, String role);
	
	void updateTicket(CustomerSupportTickets customerSupportTickets);
	
	CustomerSupportTickets findTicketById(String ticketId);
}
