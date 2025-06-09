package com.crm.realestatecrm.service;

import java.util.List;

import com.crm.realestatecrm.entity.CustomerFeedback;

public interface CustomerFeedbackService {
	void saveFeedback(CustomerFeedback customerFeedback);
	
	List<CustomerFeedback> getCusomerFeedbacksByTicketId(String ticketId);
	
	List<CustomerFeedback> getCustomerFeedbackByCustomerId(String customerId);
	
	CustomerFeedback getCustomerByFeedBackId(String feedbackId);
	
	void updateCustomerFeedback(CustomerFeedback customerFeedback);
}
