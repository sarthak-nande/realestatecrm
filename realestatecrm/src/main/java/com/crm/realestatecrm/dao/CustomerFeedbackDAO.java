package com.crm.realestatecrm.dao;

import java.util.List;

import com.crm.realestatecrm.entity.CustomerFeedback;

public interface CustomerFeedbackDAO {
	
	void saveFeedback(CustomerFeedback customerFeedback);
	
	List<CustomerFeedback> getCusomerFeedbacksByTicketId(String ticketId);
	
	List<CustomerFeedback> getCustomerFeedbackByCustomerId(String customerId);
	
	CustomerFeedback getCustomerByFeedBackId(String feedbackId);
	
	void updateCustomerFeedback(CustomerFeedback customerFeedback);
	
}
