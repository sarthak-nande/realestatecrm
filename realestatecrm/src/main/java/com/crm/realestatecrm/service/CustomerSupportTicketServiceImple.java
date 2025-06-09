package com.crm.realestatecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.CustomerSupportTicketDAO;
import com.crm.realestatecrm.entity.CustomerSupportTickets;

@Service
public class CustomerSupportTicketServiceImple implements CustomerSupportTicketService{
	
	private CustomerSupportTicketDAO customerSupportTicketDAO;
	
	@Autowired
	public CustomerSupportTicketServiceImple(CustomerSupportTicketDAO customerSupportTicketDAO) {
		this.customerSupportTicketDAO = customerSupportTicketDAO;
	}

	@Override
	public void saveTicket(CustomerSupportTickets customerSupportTickets) {
		customerSupportTicketDAO.saveTicket(customerSupportTickets);
	}

	@Override
	public List<CustomerSupportTickets> getAllTickets(String email, String role) {
		return customerSupportTicketDAO.getAllTickets(email, role);
	}

	@Override
	public void updateTicket(CustomerSupportTickets customerSupportTickets) {
		customerSupportTicketDAO.updateTicket(customerSupportTickets);
	}

	@Override
	public CustomerSupportTickets findTicketById(String ticketId) {
		return customerSupportTicketDAO.findTicketById(ticketId);
	}

}
