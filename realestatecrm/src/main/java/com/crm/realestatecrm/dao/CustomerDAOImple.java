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
	
	public CustomerDAOImple(EntityManager entityManager) {
		this.entityManager = entityManager;
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
		
		if(userRole.equals("ROLE_SALES")) {
			TypedQuery<Customer> query = entityManager.createQuery("select s from Customer s where s.salesExecId = :email",Customer.class);
			query.setParameter("email", email);
			List<Customer> customers = query.getResultList();
			count = customers.size();
		}
		
		if(userRole.equals("ROLE_MANAGER")) {
			TypedQuery<Customer> query = entityManager.createQuery("select s from Customer s where s.managerId = :email",Customer.class);
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
	
	

	@Transactional
	public void updateCustomer(Customer customer) {
		
	    TypedQuery<Customer> query = entityManager.createQuery(
	        "SELECT c FROM Customer c WHERE c.email = :email", Customer.class);
	    query.setParameter("email", customer.getEmail());

	    Customer results = query.getSingleResult(); // Avoid exception

	    Customer existingCustomer = results;

	    if (existingCustomer == null) {
	        throw new RuntimeException("Customer not found for ID: " + customer.getCustomerId());
	    }

	    // Update fields only if they are provided in the request
	    if (customer.getStatus() != null) {
	        existingCustomer.setStatus(customer.getStatus());
	    }
	    if (customer.getBudgetRange() != null) {
	        existingCustomer.setBudgetRange(customer.getBudgetRange());
	    }
	    if (customer.getLocationPreference() != null) {
	        existingCustomer.setLocationPreference(customer.getLocationPreference());
	    }
	    if (customer.getPropertyId() != null) {
	        existingCustomer.setPropertyId(customer.getPropertyId());
	    }
	    if (customer.getFirstName() != null) {
	        existingCustomer.setFirstName(customer.getFirstName());
	    }
	    
	    if (customer.getLastName() != null) {
	        existingCustomer.setLastName(customer.getLastName());
	    }

	    entityManager.merge(existingCustomer);
	}


	@Override
	public Customer getCustomerByEmail(String email) {
		TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c where c.email = :email" , Customer.class);
		query.setParameter("email", email);
		Customer customer = query.getSingleResult();
		return customer;
	}

	@Override
	public List<String> getCreatedTime(String role, String email) {
		List<String> createdAtList = new ArrayList<>();
		if(role == "ROLE_MANAGER") {
			TypedQuery<String> query = entityManager.createQuery(
				    "SELECT FUNCTION('DATE_FORMAT', c.createdAt, '%Y-%m-%d %H:%i:%s') FROM Customer c where c.manager_id = :email", String.class).setParameter("email", email);
			createdAtList = query.getResultList();
		}
		
		if(role == "ROLE_SALES") {
			TypedQuery<String> query = entityManager.createQuery(
				    "SELECT FUNCTION('DATE_FORMAT', c.createdAt, '%Y-%m-%d %H:%i:%s') FROM Customer c where c.sales_exec_id = :email", String.class).setParameter("email", email);
			createdAtList = query.getResultList();
		}
		
		return createdAtList;
	}
	

}
