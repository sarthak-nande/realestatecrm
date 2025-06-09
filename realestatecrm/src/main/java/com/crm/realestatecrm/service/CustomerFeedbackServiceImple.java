package com.crm.realestatecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.CustomerFeedbackDAO;
import com.crm.realestatecrm.entity.CustomerFeedback;

@Service
public class CustomerFeedbackServiceImple implements CustomerFeedbackService{
	
	private CustomerFeedbackDAO customerFeedbackDAO;
	
	@Autowired
	public CustomerFeedbackServiceImple(CustomerFeedbackDAO customerFeedbackDAO) {
		this.customerFeedbackDAO = customerFeedbackDAO;
	}

	@Override
	public void saveFeedback(CustomerFeedback customerFeedback) {
		customerFeedbackDAO.saveFeedback(customerFeedback);
	}

	@Override
	public List<CustomerFeedback> getCusomerFeedbacksByTicketId(String ticketId) {
		return customerFeedbackDAO.getCusomerFeedbacksByTicketId(ticketId);
	}

	@Override
	public List<CustomerFeedback> getCustomerFeedbackByCustomerId(String customerId) {
		return customerFeedbackDAO.getCustomerFeedbackByCustomerId(customerId);
	}

	@Override
	public CustomerFeedback getCustomerByFeedBackId(String feedbackId) {
		return customerFeedbackDAO.getCustomerByFeedBackId(feedbackId);
	}

	@Override
	public void updateCustomerFeedback(CustomerFeedback customerFeedback) {
		customerFeedbackDAO.updateCustomerFeedback(customerFeedback);
	}

}
