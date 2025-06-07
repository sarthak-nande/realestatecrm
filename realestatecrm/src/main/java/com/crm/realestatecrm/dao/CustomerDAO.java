package com.crm.realestatecrm.dao;

import java.util.List;

import com.crm.realestatecrm.entity.Customer;

public interface CustomerDAO {
	boolean save(Customer customer);
	
	int getCustomerCount(String userRole, String mail);
	
	List<Customer> getAllCustomers(String userRole, String email);
	
	void updateCustomer(Customer customer);
	
	Customer getCustomerByEmail(String email);
}
