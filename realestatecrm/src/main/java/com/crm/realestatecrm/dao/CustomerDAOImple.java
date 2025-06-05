package com.crm.realestatecrm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Customer;
import com.crm.realestatecrm.entity.SalesExecutive;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerDAOImple implements CustomerDAO{
	
	private EntityManager entityManager;
	private BCryptPasswordEncoder byBCryptPasswordEncoder;
	
	public CustomerDAOImple(EntityManager entityManager, BCryptPasswordEncoder byBCryptPasswordEncoder) {
		this.entityManager = entityManager;
		this.byBCryptPasswordEncoder = byBCryptPasswordEncoder;
	}

	@Override
	@Transactional
	public boolean save(Customer customer) {
		String email = customer.getEmail();
		Customer existingUser = new Customer();
		System.out.println(email);
		try {
		    existingUser = entityManager.createQuery(
		        "SELECT c FROM Customer c WHERE c.email = :email", Customer.class)
		        .setParameter("email", customer.getEmail())
		        .getSingleResult();
		} catch (NoResultException e) {
		    existingUser = null; // Handle case where no record exists
		}

		if (existingUser != null) {
		    return false; 
		}
		
		System.out.println(customer.getSalesExecId());
		SalesExecutive salesExecutive = entityManager.createQuery(
			    "SELECT s FROM SalesExecutive s WHERE s.email = :email", SalesExecutive.class)
			    .setParameter("email", customer.getSalesExecId())
			    .getSingleResult();

		System.out.println(salesExecutive);
		customer.setManagerId(salesExecutive.getManagerEmail());
		
		entityManager.persist(customer);
		
		return true;
	}

	@Override
	public int getCustomerCount(String userRole, String email) {
		int count = 0;
		
		if(userRole == "ROLE_SALES") {
			TypedQuery<Customer> query = entityManager.createQuery("select s from Customer s where s.sales_exec_id = :email",Customer.class);
			query.setParameter("email", email);
			List<Customer> customers = query.getResultList();
			count = customers.size();
		}
		
		if(userRole == "ROLE_MANAGER") {
			TypedQuery<Customer> query = entityManager.createQuery("select s from Customer s where s.manager_id = :email",Customer.class);
			query.setParameter("email", email);
			List<Customer> customers = query.getResultList();
			count = customers.size();
		}
		
		return count;
	}

	@Override
	public List<Customer> getAllCustomers(String userRole, String email) {
		
		List<Customer> customers = new ArrayList<>();
		
		if(userRole.equals("ROLE_SALES")) {
			System.out.println("Hi");
			TypedQuery<Customer> query = entityManager.createQuery("select s from Customer s where s.salesExecId = :email",Customer.class);
			query.setParameter("email", email);
			customers = query.getResultList();
			
		}
		
		if(userRole.endsWith("ROLE_MANAGER")) {
			TypedQuery<Customer> query = entityManager.createQuery("select s from Customer s where s.managerId = :email",Customer.class);
			query.setParameter("email", email);
			customers = query.getResultList();
		}
		
		return customers;
	}
	
	
	
	
	
	

}
