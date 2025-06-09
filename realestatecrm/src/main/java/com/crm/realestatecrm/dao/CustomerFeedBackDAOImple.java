package com.crm.realestatecrm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.CustomerFeedback;
import com.crm.realestatecrm.entity.CustomerSupportTickets;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerFeedBackDAOImple implements CustomerFeedbackDAO{
	
	private EntityManager entityManager;
	
	public CustomerFeedBackDAOImple(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void saveFeedback(CustomerFeedback customerFeedback) {
		entityManager.persist(customerFeedback);
	}

	@Override
	public List<CustomerFeedback> getCusomerFeedbacksByTicketId(String ticketId) {
		TypedQuery<CustomerFeedback> query = entityManager.createQuery("select f from CustomerFeedback f where f.ticketId = :ticketId", CustomerFeedback.class);
		query.setParameter("ticketId", ticketId);
		List<CustomerFeedback> customerFeedback = query.getResultList();
		return customerFeedback;
	}

	@Override
	public List<CustomerFeedback> getCustomerFeedbackByCustomerId(String customerId) {
		TypedQuery<CustomerFeedback> query = entityManager.createQuery("select f from CustomerFeedback f where f.customerId = :customerId", CustomerFeedback.class);
		query.setParameter("customerId", customerId);
		List<CustomerFeedback> customerFeedback = query.getResultList();
		return customerFeedback;
	}

	@Override
	@Transactional
	public void updateCustomerFeedback(CustomerFeedback customerFeedback) {
		
		TypedQuery<CustomerFeedback> query = entityManager.createQuery("SELECT c FROM CustomerFeedback c WHERE c.feedbackId = :feedbackId", CustomerFeedback.class);
	    query.setParameter("feedbackId", customerFeedback.getFeedbackId());

	    CustomerFeedback results = query.getSingleResult();

	    CustomerFeedback existingCustomer = results;

	    if (existingCustomer == null) {
	        throw new RuntimeException("Customer not found for ID: " + customerFeedback.getFeedbackId());
	    }
		
		entityManager.merge(customerFeedback);
	}

	@Override
	public CustomerFeedback getCustomerByFeedBackId(String feedbackId) {
		TypedQuery<CustomerFeedback> query = entityManager.createQuery("select f from CustomerFeedback f where f.feedbackId = :feedbackId", CustomerFeedback.class);
		query.setParameter("feedbackId", feedbackId);
		CustomerFeedback customerFeedback = query.getSingleResult();
		return customerFeedback;
	}

}
