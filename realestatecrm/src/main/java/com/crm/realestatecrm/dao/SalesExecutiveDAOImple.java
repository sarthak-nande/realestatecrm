package com.crm.realestatecrm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Authorities;
import com.crm.realestatecrm.entity.Customer;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class SalesExecutiveDAOImple implements SalesExecutiveDAO {
	
	private EntityManager entityManager;
	private BCryptPasswordEncoder byBCryptPasswordEncoder;
	
	public SalesExecutiveDAOImple(EntityManager entityManager, BCryptPasswordEncoder byBCryptPasswordEncoder) {
		this.entityManager = entityManager;
		this.byBCryptPasswordEncoder = byBCryptPasswordEncoder;
	}

	@Override
	@Transactional
	public boolean save(SalesExecutive salesExecutive) {
		Users user = new Users();
		Authorities authorities = new Authorities();
		
		String email = salesExecutive.getEmail();
		Users existingUser = entityManager.find(Users.class, email);
		System.out.println(existingUser);
		if (existingUser != null) {
		    return false; 
		}

		salesExecutive.setPassword(byBCryptPasswordEncoder.encode(salesExecutive.getPassword()));
		
		user.setEnabled(1);
		user.setUsername(salesExecutive.getEmail());
		user.setPassword(salesExecutive.getPassword());
		
		authorities.setUsername(salesExecutive.getEmail());
		authorities.setAuthority("ROLE_SALES");
		
		entityManager.persist(user);
		entityManager.persist(authorities);
		entityManager.persist(salesExecutive);
		
		return true;
	}

	@Override
	public List<SalesExecutive> getAllSalesExecutives(String email) {
		List<SalesExecutive> salesExecutive = new ArrayList<>();
		TypedQuery<SalesExecutive> query = entityManager.createQuery("select s from SalesExecutive s where s.managerEmail = :email",SalesExecutive.class);
		query.setParameter("email", email);
		salesExecutive = query.getResultList();
		return salesExecutive;
	}

	@Override
	public SalesExecutive findSalesExecutiveByEmail(String email) {
		SalesExecutive salesExecutive = new SalesExecutive();
		TypedQuery<SalesExecutive> query = entityManager.createQuery("select s from SalesExecutive s where s.email = :email",SalesExecutive.class);
		query.setParameter("email", email);
		salesExecutive = query.getSingleResult();
		return salesExecutive;
	}

	@Override
	public int getSalesExecutiveCount(String managerEmail) {
		List<SalesExecutive> salesExecutives = new ArrayList<>();
		TypedQuery<SalesExecutive> query = entityManager.createQuery("select s from SalesExecutive s where s.managerEmail = :managerEmail",SalesExecutive.class);
		query.setParameter("managerEmail", managerEmail);
		salesExecutives = query.getResultList();
		int count  = salesExecutives.size();
		return count;
	}

}
