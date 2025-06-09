package com.crm.realestatecrm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Customer;
import com.crm.realestatecrm.entity.CustomerSupportTickets;
import com.crm.realestatecrm.entity.SalesExecutiveTask;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerSupportTicketDAOImple implements CustomerSupportTicketDAO{
	
	private EntityManager entityManager;
	
	public CustomerSupportTicketDAOImple(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void saveTicket(CustomerSupportTickets customerSupportTickets) {
		entityManager.persist(customerSupportTickets);
	}

	@Override
	public List<CustomerSupportTickets> getAllTickets(String email, String userRole) {
		List<CustomerSupportTickets> customerSupportTickets = new ArrayList<>();
		
		if(userRole.equals("ROLE_SALES")) {
			System.out.println("Hi");
			TypedQuery<CustomerSupportTickets> query = entityManager.createQuery("select s from CustomerSupportTickets s where s.salesExectiveId = :email",CustomerSupportTickets.class);
			query.setParameter("email", email);
			customerSupportTickets = query.getResultList();
			
		}
		
		if(userRole.endsWith("ROLE_MANAGER")) {
			TypedQuery<CustomerSupportTickets> query = entityManager.createQuery("select s from CustomerSupportTickets s where s.managerId = :email",CustomerSupportTickets.class);
			query.setParameter("email", email);
			customerSupportTickets = query.getResultList();
		}
		
		return customerSupportTickets;
	}

	@Override
	@Transactional
	public void updateTicket(CustomerSupportTickets customerSupportTickets) {
		
		TypedQuery<CustomerSupportTickets> query = entityManager.createQuery("SELECT c FROM CustomerSupportTickets c WHERE c.ticketId = :ticketId", CustomerSupportTickets.class);
	    query.setParameter("ticketId", customerSupportTickets.getTicketId());

	    CustomerSupportTickets results = query.getSingleResult();

	    CustomerSupportTickets existingCustomer = results;

	    if (existingCustomer == null) {
	        throw new RuntimeException("Customer not found for ID: " + customerSupportTickets.getTicketId());
	    }

	    if (customerSupportTickets.getStatus() != null) {
	        existingCustomer.setStatus(customerSupportTickets.getStatus());
	    }
	    
		entityManager.merge(customerSupportTickets);
	}

	@Override
	public CustomerSupportTickets findTicketById(String ticketId) {
		TypedQuery<CustomerSupportTickets> query = entityManager.createQuery("select c from CustomerSupportTickets c where c.ticketId = :ticketId" , CustomerSupportTickets.class);
		query.setParameter("ticketId", ticketId);
		CustomerSupportTickets customerSupportTickets = query.getSingleResult();
		return customerSupportTickets;
	}

}
