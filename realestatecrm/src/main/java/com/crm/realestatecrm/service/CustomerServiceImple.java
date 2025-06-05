package com.crm.realestatecrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.CustomerDAO;
import com.crm.realestatecrm.entity.Customer;

@Service
public class CustomerServiceImple implements CustomerService{
	
	private CustomerDAO customerDAO;
	
	public CustomerServiceImple(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public boolean save(Customer customer) {
		return customerDAO.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers(String userRole, String email) {
		return customerDAO.getAllCustomers(userRole, email);
	}

}
