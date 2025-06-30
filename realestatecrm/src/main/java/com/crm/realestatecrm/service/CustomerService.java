package com.crm.realestatecrm.service;

import java.util.List;

import com.crm.realestatecrm.entity.Customer;

public interface CustomerService {
	boolean save(Customer customer);
	
	List<Customer> getAllCustomers(String userRole, String email);
	
	void updateCustomer(Customer customer);
	
	Customer getCustomerByEmail(String email);
	
	int getCustomerCount(String userRole, String mail);
	
	List<String> getCreatedTime(String role, String email);
}
